using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net.Sockets;

namespace TCPServer01
{
    public class ClientNode : IEquatable<string>
    {
        #region [ Public Methods ]

        public override string ToString()
        {
            return _Id;
        }

        bool IEquatable<string>.Equals(string client)
        {
            if (string.IsNullOrEmpty(client)) return false;
            if (this._Client == null) return false;
            return this._Id.Equals(client);
        }

        #endregion

        #region [ Properties ]

        public string Name
        {
            get { return _name; }
            set 
            {
                _name = value;
                _fullId = String.Format("{0} [{1}]", _Id, _name);
            }
        }

        public string FullId { get { return _fullId; } }
        public int BufSize { get { return _bufSize; } }

        #endregion

        #region [ Contructors ]

        public ClientNode(TcpClient client, byte[] tx, byte[] rx, string id)
        {
            this._Client = client;
            this._Tx = tx;
            this._Rx = rx;
            this._Id = id;
        }

        public ClientNode(TcpClient client)
        {
            _Tx = new byte[_bufSize];
            _Rx = new byte[_bufSize];
            _Id = string.Empty;
            _Client = client;
        }

        public ClientNode()
        {
            _Tx = new byte[_bufSize];
            _Rx = new byte[_bufSize];
            _Id = string.Empty;
            _Client = new TcpClient();
        }

        #endregion

        #region [ Public Members ]

        public byte[] _Tx;
        public byte[] _Rx;
        public string _Id;
        public TcpClient _Client;

        #endregion

        #region [ Private Members ]

        private string _fullId;
        private string _name;
        private static int _bufSize = 1024;

        #endregion
    }
}
