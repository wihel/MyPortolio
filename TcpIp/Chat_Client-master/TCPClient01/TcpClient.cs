using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Net;
using System.Net.Sockets;
using MaterialSkin.Controls;

namespace TCPClient01
{
    public partial class frmTcpClient : MaterialForm
    {
        public frmTcpClient()
        {
            InitializeComponent();

            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;

            CheckForIllegalCrossThreadCalls = false;

            cbIpSelect.DataSource = Enum.GetValues(typeof(ConnectionIP));
        }

        #region [ Send ]

        private void btnSend_Click(object sender, EventArgs e)
        {
            if (lbClients.SelectedItem == null)
            {
                ErrorMessageHandler("No client selected");
                return;
            }

            if (tbPayload.Text == "")
            {
                PrintLine("[Error] Payload is empty");
                return;
            }

            string client = lbClients.SelectedItem.ToString();
            string message = String.Format("{0}{1}{2}", client, Const.ClientNameDataDeliminator, tbPayload.Text);
            SendData(new ClientSend(OperationType.RouteToServerClient, message));
            DoInvoke(message);
            tbPayload.Text = "";
        }

        private void SendData(ClientSend cs)
        {
            byte[] buffer = new byte[_bufLen];

            string message = cs.ToString(); //testing
            if (string.IsNullOrEmpty(cs.ToString()))
                return;

            try
            {
                if (_tcpClient != null)
                {
                    if (_tcpClient.Client.Connected)
                    {
                        buffer = Encoding.ASCII.GetBytes(cs.ToString());
                        _tcpClient.GetStream().BeginWrite(buffer, 0, buffer.Length, OnCompleteWriteTcpClient, _tcpClient);
                    }
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(ex);
            }
        }

        private void OnCompleteWriteTcpClient(IAsyncResult iar)
        {
            try
            {
                TcpClient client = (TcpClient)iar.AsyncState;
                client.GetStream().EndWrite(iar);
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(ex);
            }
        }

        #endregion

        #region [ Connect ]

        private void btnConnect_Click(object sender, EventArgs e)
        {
            EnableConnectionControls(false);
            ConnectToServer();
        }

        private void ConnectToServer()
        {
            IPAddress ipAddr = null;
            int nPort;

            try
            {
                if (string.IsNullOrEmpty(tbServerIP.Text))
                {

                    PrintLine("No DNS Name or IP entered");
                    EnableConnectionControls(true);
                    return;
                }

                PrintLine("Connecting...");
                switch (_cip)
                {
                    case ConnectionIP.DNS:
                        ipAddr = GetServerIPAddrFromDns(tbServerIP.Text);
                        break;
                    case ConnectionIP.IPv4:
                        //do nothing
                        break;
                    default:
                        break;
                }

                if (ipAddr == null && !IPAddress.TryParse(tbServerIP.Text, out ipAddr))
                {
                    PrintLine("Failed to identify IP address");
                    EnableConnectionControls(true);
                    return;
                }
                if (!int.TryParse(tbServerPort.Text, out nPort))
                {
                    if(tbServerIP.Text.Contains("www")) nPort = 80; //HTTP port 
                    else nPort = 23000;

                    tbServerPort.Text = nPort.ToString();
                    PrintLine("Failed to identify Port number");
                    PrintLine("Default port set");
                }

                _tcpClient = new TcpClient();
                _tcpClient.BeginConnect(ipAddr, nPort, OnCompleteConnect, _tcpClient);
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(ex);
            }
        }

        private void OnCompleteConnect(IAsyncResult iar)
        {
            TcpClient client;

            try
            {
                client = (TcpClient)iar.AsyncState;
                client.EndConnect(iar);
                _buffer = new byte[_bufLen];
                client.GetStream().BeginRead(_buffer, 0, _buffer.Length, OnCompleteReadFromServer, client);
            }
            catch (SocketException)
            {
                PrintLine("Connection refused");
                EnableConnectionControls(true);
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(ex);
                EnableConnectionControls(true);
            }
        }

        private void OnCompleteReadFromServer(IAsyncResult iar)
        {
            TcpClient client = null;
            int readCount = 0;
            string recieved = "";

            try
            {
                client = (TcpClient)iar.AsyncState;
                readCount = client.GetStream().EndRead(iar);

                if (readCount == 0)
                {
                    PrintLine("Connection broken");
                    this.Text = HEADER;
                    btnConnect.Enabled = true;
                    return;
                }
                recieved = Encoding.ASCII.GetString(_buffer, 0, readCount);

                //process reads
                ProcessServerMessage(new ServerRecieve(recieved));

                _buffer = new byte[_bufLen];
                client.GetStream().BeginRead(_buffer, 0, _buffer.Length, OnCompleteReadFromServer, client);
            }
            catch (IOException)
            {
                PrintLine("Connection broken");
                this.Text = HEADER;
                btnConnect.Enabled = true;
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(ex);
            }
        }

        private void cbIpSelect_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                Enum.TryParse<ConnectionIP>(cbIpSelect.SelectedValue.ToString(), out _cip);
                switch (_cip)
                {
                    case ConnectionIP.DNS:
                        lblServerIP.Text = "Server Name:";
                        tbServerIP.Text = "";
                        break;
                    case ConnectionIP.IPv4:
                        lblServerIP.Text = "Server IP:";
                        tbServerIP.Text = "";
                        break;
                    default:
                        break;
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(ex);
            }
        }

        private IPAddress GetServerIPAddrFromDns(string dnsName)
        {
            IPAddress[] allIPAddr = null;
            IPAddress ipAddr = null;

            try
            {
                allIPAddr = Dns.GetHostAddresses(dnsName);

                for (int i = allIPAddr.Length - 1; i >= 0; i--)
                {
                    if (allIPAddr[i].ToString().Contains('.')) //.Contains(':') for IPv6
                    {
                        ipAddr = allIPAddr[i];
                        break;
                    }
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(ex);
            }

            PrintLine(String.Format("Server IP: {0}", ipAddr));
            return ipAddr;
        }

        private void ProcessServerMessage(ServerRecieve sr)
        {
            try
            {
                switch (sr.OpType)
                {
                    case OperationType.ServerClientId:
                        this.Text = String.Format("{0} [{1}]", HEADER, sr.Data);
                        PrintLine("HS: recieved server client id");
                        PrintLine("HS: send client name");
                        
                        SendData(new ClientSend(OperationType.ClientName, Dns.GetHostName()));
                        break;
                    case OperationType.ClientName:
                        break;
                    case OperationType.TotalServerClients:
                        break;
                    case OperationType.ServerClientName:
                        lock (lbClients)
                        {
                            lbClients.Items.Add(sr.Data);
                        }
                        break;
                    case OperationType.NewClientConnected:
                        lock (lbClients)
                        {
                            lbClients.Items.Add(sr.Data);
                            PrintLine(String.Format("[Server] [{0}] Connected", sr.Data));
                        }
                        break;
                    case OperationType.RouteToServerClient:
                        break;
                    case OperationType.ClientDisconnected:
                        for (int i = 0; i < lbClients.Items.Count; i++)
                        {
                            string clientName = lbClients.Items[i].ToString();
                            if (clientName == sr.Data)
                            {
                                lbClients.Items.RemoveAt(i);
                                PrintLine(String.Format("[Server] [{0}] Client disconnected", sr.Data));
                                break;
                            }
                        }
                        break;
                    case OperationType.Message:
                        int index = sr.Data.IndexOf(' ') + 1;
                        string newString = sr.Data.Substring(index, sr.Data.Length - index);

                        PrintLine(newString.Replace('|', ' '));
                        break;
                    default:
                        break;
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(ex);
            }
        }

        private void EnableConnectionControls(bool enable)
        {
            tbServerIP.Enabled = enable;
            tbServerPort.Enabled = enable;
            cbIpSelect.Enabled = enable;
            btnConnect.Enabled = enable;
        }

        #endregion

        private void ErrorMessageHandler(string error)
        {
            PrintLine(String.Format("[Error] {0}", error));
        }

        private void ExceptionMessageHandler(Exception ex)
        {
            PrintLine(String.Format("[Exception] {0}", ex.Message));
        }

        public void PrintLine(string str)
        {
            tbConsoleOutput.Invoke(new Action<string>(DoInvoke), str);
        }

        public void DoInvoke(string str)
        {
            DateTime dt = DateTime.Now;
            string date = String.Format("{0}/{1}/{2}", dt.Month, dt.Day, dt.Year);
            string time = dt.TimeOfDay.ToString();
            tbConsoleOutput.Text = String.Format("[{0}_{1}] {2}{3}{4}", new object[] { date, time, str, Environment.NewLine, tbConsoleOutput.Text });
        }

        private ConnectionIP _cip;
        private byte[] _buffer = new byte[_bufLen];
        private static int _bufLen = 1024;
        private TcpClient _tcpClient;
        private List<string> _serverClients = new List<string>();

        private const string HEADER = "TCP Client";
    }

    enum ConnectionIP
    {
        DNS = 0,
        IPv4 = 1
    }
}
