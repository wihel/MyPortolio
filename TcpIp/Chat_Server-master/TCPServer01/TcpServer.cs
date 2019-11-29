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

namespace TCPServer01
{
    public partial class frmTcpServer : MaterialForm
    {
        public frmTcpServer()
        {
            InitializeComponent();

            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;

            _clientSocks = new List<ClientNode>();//리스트 소캣선언
            CheckForIllegalCrossThreadCalls = false; //allows for adding cliemts to list box

            cbIpSelect.DataSource = Enum.GetValues(typeof(ConnectionIP));//ip를 받아드림

            _ipAddr = IPAddress.Any;
            tbIPAddress.Text = _ipAddr.ToString();
        }

        #region [ IPv4 ]

        //private void btnIPv4_Click(object sender, EventArgs e)
        //{
        //    IPAddress ipAddr = null;

        //    ipAddr = GetIPv4Address();
        //    if (ipAddr != null)
        //    {
        //        PrintLine(null, String.Format("IPv4 Addr: [{0}]", ipAddr));
        //        tbIPAddress.Text = ipAddr.ToString();
        //    }
        //}

        private IPAddress GetIPv4Address()
        {
            string server = "";
            IPHostEntry dnsEntry = null;
            IPAddress[] allIPAddr = null;
            IPAddress ipv4Addr = null;

            try
            {
                server = Dns.GetHostName();
                PrintLine(null, String.Format("Server Name: {0}", server));

                dnsEntry = Dns.GetHostEntry(server);
                allIPAddr = dnsEntry.AddressList;

                for (int i = allIPAddr.Length - 1; i > 0; i--)
                {
                    if (allIPAddr[i].AddressFamily == AddressFamily.InterNetwork)
                    {
                        ipv4Addr = allIPAddr[i];
                        break;
                    }
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(null, ex);
            }

            return ipv4Addr;
        }

        #endregion

        #region [ Send ]

        private void btnSend_Click(object sender, EventArgs e)
        {
            ClientNode cn = null;

            if (lbClients.SelectedItem == null)
            {
                ErrorMessageHandler("No client selected");
                return;
            }

            if (tbPayload.Text == "")
            {
                ErrorMessageHandler("Payload is empty");
                return;
            }

            cn = _clientSocks.Find(x => x.FullId == lbClients.SelectedItem.ToString());//.Split(' ')[0]);
            SendDataToClient(cn.FullId, OperationType.Message, tbPayload.Text);
            DoInvoke(tbPayload.Text);
            tbPayload.Text = "";
        }

        private void SendDataToClient(string clientName, OperationType opType, string data)
        {
            //byte[] buffer = new byte[_bufLen];
            if (lbClients.Items.Count <= 0) return;
            if (string.IsNullOrEmpty(data)) return;

            ClientNode cn = null;

            lock (_clientSocks)
            {
                //cn = _clientSocks.Find(x => x._Id == lbClients.SelectedItem.ToString().Split(' ')[0]);
                cn = _clientSocks.Find(x => x.FullId == clientName);
            }

            cn._Tx = new byte[cn.BufSize];

            try
            {
                if (cn != null)
                {
                    if (cn._Client != null)
                    {
                        if (cn._Client.Client.Connected)
                        {
                            SendServerMessage(cn, new ServerSend(opType, data));
                            //SendData(cn, data);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(cn, ex);
            }
        }

        //private void SendData(ClientNode cn, string data)
        //{
        //    cn._Tx = Encoding.ASCII.GetBytes(data);
        //    cn._Client.GetStream().BeginWrite(cn._Tx, 0, cn._Tx.Length, OnCompleteWriteTcpClient, cn._Client);
        //}

        private void OnCompleteWriteTcpClient(IAsyncResult iar)
        {
            ClientNode cn = null;
            TcpClient client = null;

            try
            {
                lock (_clientSocks)
                {
                    client = (TcpClient)iar.AsyncState;
                    cn = _clientSocks.Find(x => x._Id == client.Client.RemoteEndPoint.ToString());
                    //TcpClient client = (TcpClient)iar.AsyncState;
                    cn._Client.GetStream().EndWrite(iar);
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(null, ex);
            }
        }

        #region [ Server Messages ]

        private void SendClientDisconnected(ClientNode cn)
        {
            ClientNode acn = null;
            try
            {
                for (int i = 0; i < lbClients.Items.Count; i++)
                {
                    acn = _clientSocks.Find(x => x.FullId == lbClients.Items[i].ToString());
                    SendServerMessage(acn, new ServerSend(OperationType.ClientDisconnected, cn.FullId));
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(cn, ex);
            }
        }

        private void UpdateAllConnectedClients(ClientNode cn)
        {
            ClientNode acn = null;

            try
            {
                for (int i = 0; i < lbClients.Items.Count; i++)
                {
                    acn = _clientSocks.Find(x => x.FullId == lbClients.Items[i].ToString());
                    string scid = acn.FullId;
                    if (scid != cn.FullId)
                        SendServerMessage(acn, new ServerSend(OperationType.NewClientConnected, cn.FullId));
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(cn, ex);
            }
        }

        private void SendServerClientID(ClientNode cn)
        {
            SendServerMessage(cn, new ServerSend(OperationType.ServerClientId, cn.ToString()));
            PrintLine(cn, "HS: send server client id to client");
        }

        private void SendServerClientNames(ClientNode cn)
        {
            for (int i = 0; i < lbClients.Items.Count; i++)
            {
                string scid = lbClients.Items[i].ToString();
                if (scid != cn.FullId)
                    SendServerMessage(cn, new ServerSend(OperationType.ServerClientName, scid));
            }
        }

        private void SendServerMessage(ClientNode cn, ServerSend sCmd)
        {
            string cmd = sCmd.ToString(); //testing
            cn._Tx = Encoding.ASCII.GetBytes(sCmd.ToString());
            cn._Client.GetStream().BeginWrite(cn._Tx, 0, cn._Tx.Length, OnCompleteWriteTcpClient, cn._Client);
        }

        #endregion

        #endregion

        #region [ Listen ]

        private void btnStartListening_Click(object sender, EventArgs e)
        {
            EnableListeningControls(false);
            StartListening();
        }

        private void StartListening()
        {
            int nPort;

            try
            {
                PrintLine(null, String.Format("Listening on IP addr: [{0}]", _ipAddr));

                if (!int.TryParse(tbPort.Text, out nPort))
                {
                    nPort = 23000;

                    PrintLine(null, "Invalid Port supplied\n");
                    PrintLine(null, String.Format("Default Port set [{0}]", nPort));
                    tbPort.Text = nPort.ToString();
                }

                _tcpListener = new TcpListener(_ipAddr, nPort);
                _tcpListener.Start();
                _tcpListener.BeginAcceptTcpClient(new AsyncCallback(OnCompleteAcceptTcpClient), _tcpListener);
                PrintLine(null, "Waiting for connection...");
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(null, ex);
                EnableListeningControls(true);
            }
        }

        #region [ Connect ]

        private void OnCompleteAcceptTcpClient(IAsyncResult iar)
        {
            TcpListener listener = (TcpListener)iar.AsyncState;
            TcpClient client = null;
            ClientNode cn = new ClientNode();

            try
            {
                client = listener.EndAcceptTcpClient(iar);

                listener.BeginAcceptTcpClient(OnCompleteAcceptTcpClient, listener);

                lock (_clientSocks)
                {
                    //string ipAddr = ((IPEndPoint)client.Client.RemoteEndPoint).Address.ToString();
                    _clientSocks.Add((cn = new ClientNode(client, new byte[cn.BufSize], new byte[cn.BufSize], client.Client.RemoteEndPoint.ToString())));
                    //lbClients.Items.Add(cn.ToString());
                }
                PrintLine(cn, "Client connected");

                client.GetStream().BeginRead(cn._Rx, 0, cn._Rx.Length, OnCompleteReadTcpClient, client);

                SendServerClientID(cn);

                //_buffer = new byte[_bufLen];
                //_tcpClient.GetStream().BeginRead(_buffer, 0, _buffer.Length, OnCompleteReadTcpClient, _tcpClient);
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(cn, ex);
            }
        }

        #endregion

        #region [ Read ]

        private void OnCompleteReadTcpClient(IAsyncResult iar)
        {
            TcpClient client = null;
            int readCount = 0;
            string recieved = "";
            ClientNode cn = null;

            try
            {
                lock (_clientSocks)
                {
                    client = (TcpClient)iar.AsyncState;
                    cn = _clientSocks.Find(x => x._Id == client.Client.RemoteEndPoint.ToString());
                    readCount = client.GetStream().EndRead(iar);

                    if (readCount == 0) // happens when disconnected
                    {
                        PrintLine(cn, "Client disconnected");
                        _clientSocks.Remove(cn);
                        lbClients.Items.Remove(cn.FullId);
                        SendClientDisconnected(cn);
                        return;
                    }

                    recieved = Encoding.ASCII.GetString(cn._Rx, 0, readCount).Trim();

                    //process reads
                    ProcessClientMessage(cn, new ClientRecieve(recieved));
                    
                    //recieved = Encoding.ASCII.GetString(_buffer, 0, readCount);

                    cn._Rx = new byte[cn.BufSize];
                    client.GetStream().BeginRead(cn._Rx, 0, cn._Rx.Length, OnCompleteReadTcpClient, client);
                }
            }
            catch (IOException)
            {
                PrintLine(cn, "Client disconnected");
                _clientSocks.Remove(cn);
                lbClients.Items.Remove(cn.FullId);
                SendClientDisconnected(cn);
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(cn, ex);
            }
        }

        private void ProcessClientMessage(ClientNode cn, ClientRecieve cm)
        {
            try
            {
                switch (cm.OpType)
                {
                    case OperationType.ServerClientId:
                        break;
                    case OperationType.ClientName:
                        cn.Name = cm.Data;
                        lbClients.Items.Add(String.Format("{0} [{1}]", cn.ToString(), cn.Name));
                        PrintLine(cn, "HS: recieved client name");
                        SendServerClientNames(cn);
                        UpdateAllConnectedClients(cn);
                        break;
                    case OperationType.TotalServerClients:
                        break;
                    case OperationType.ServerClientName:
                        break;
                    case OperationType.RouteToServerClient:
                        string[] split = cm.Data.Split(Const.ClientNameDataDeliminator);
                        string clientName = split[0]; // testing

                        cn = _clientSocks.Find(x => x._Id == cn.FullId);
                        SendDataToClient(split[0], OperationType.Message, cm.Data);
                        break;
                    case OperationType.Message:
                        PrintLine(cn, cm.Data);
                        break;
                    default:
                        break;
                }
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(cn, ex);
            }
        }

        #endregion

        private void cbIpSelect_SelectedIndexChanged(object sender, EventArgs e)
        {
            ConnectionIP cip;

            try
            {
                Enum.TryParse<ConnectionIP>(cbIpSelect.SelectedValue.ToString(), out cip);
                switch (cip)
                {
                    case ConnectionIP.AnyIP:
                        _ipAddr = IPAddress.Any;
                        break;
                    case ConnectionIP.IPv4:
                        _ipAddr = GetIPv4Address();
                        break;
                    default:
                        break;
                }
                tbIPAddress.Text = _ipAddr.ToString();
            }
            catch (Exception ex)
            {
                ExceptionMessageHandler(null, ex);
            }
        }

        private void EnableListeningControls(bool enable)
        {
            tbPort.Enabled = enable;
            cbIpSelect.Enabled = enable;
            btnStartListening.Enabled = enable;
        }

        #endregion

        private void ErrorMessageHandler(string error)
        {
            PrintLine(null, String.Format("[Error] {0}", error));
        }

        private void ExceptionMessageHandler(ClientNode cn, Exception ex)
        {
            PrintLine(cn, String.Format("[Exception] {0}", ex.Message));
        }

        public void PrintLine(ClientNode cn, string str)
        {
            string entry;

            if (cn == null) entry = str;
            else entry = String.Format("|{0}| {1}", string.IsNullOrEmpty(cn.Name) ? cn._Id : cn.Name, str);
            tbConsoleOutput.Invoke(new Action<string>(DoInvoke), entry);
        }

        public void DoInvoke(string str)
        {
            DateTime dt = DateTime.Now;
            string date = String.Format("{0}/{1}/{2}", dt.Month, dt.Day, dt.Year);
            string time = dt.TimeOfDay.ToString();
            tbConsoleOutput.Text = String.Format("[{0}_{1}] {2}{3}{4}", new object[] { date, time, str, Environment.NewLine, tbConsoleOutput.Text });
        }

        
        //private byte[] _buffer = new byte[_bufLen];
        //private static int _bufLen = 1024;
        private IPAddress _ipAddr = null;
        //private TcpClient _tcpClient;
        private TcpListener _tcpListener;
        private List<ClientNode> _clientSocks;

        private void lblPayload_Click(object sender, EventArgs e)
        {

        }
    }

    enum ConnectionIP
    {
        AnyIP = 0,
        IPv4 = 1,
    }
}
