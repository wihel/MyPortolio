using System;
using System.Collections.Generic;

namespace TCPServer01
{
    sealed class ClientRecieve
    {
        public OperationType OpType { get { return _type; } }
        public string Data { get { return _data; } }

        public ClientRecieve(string message)
        {
            int index = message.IndexOf(Const.CmdMessageDeliminator);
            string[] split = message.Split(':');
            _type = _dicStrToOp[message.Substring(0, index)]; //split[0]];
            _data = message.Substring(index + 1, message.Length - index - 1); // split[1];
        }

        private readonly OperationType _type;
        private readonly string _data;

        private Dictionary<string, OperationType> _dicStrToOp = new Dictionary<string, OperationType>()
        {
            { Const.ServerClientID, OperationType.ServerClientId },
            { Const.ClientName, OperationType.ClientName },
            { Const.TotalServerClients, OperationType.TotalServerClients },
            { Const.ServerClientName, OperationType.ServerClientName },
            { Const.NewClientConnected, OperationType.NewClientConnected },
            { Const.RouteToServerClient, OperationType.RouteToServerClient },
            { Const.ClientDisconnected, OperationType.ClientDisconnected },
            { Const.Message, OperationType.Message },
        };
    }

    sealed class ServerSend
    {
        public override string ToString()
        {
            return String.Format("{0}{1}{2}", _dicOpToStr[_type], Const.CmdMessageDeliminator, _data);
        }

        public ServerSend(OperationType opType, string data)
        {
            _type = opType;
            _data = data;
        }

        private readonly OperationType _type;
        private readonly string _data;

        private Dictionary<OperationType, string> _dicOpToStr = new Dictionary<OperationType, string>()
            {
                { OperationType.ServerClientId, Const.ServerClientID },
                { OperationType.ClientName, Const.ClientName },
                { OperationType.TotalServerClients, Const.TotalServerClients },
                { OperationType.ServerClientName, Const.ServerClientName },
                { OperationType.NewClientConnected, Const.NewClientConnected },
                { OperationType.RouteToServerClient, Const.RouteToServerClient },
                { OperationType.ClientDisconnected, Const.ClientDisconnected },
                { OperationType.Message, Const.Message },
            };
    }

    enum OperationType
    {
        ServerClientId = 0,
        ClientName = 1,
        TotalServerClients = 2,
        ServerClientName = 3,
        NewClientConnected = 4,
        RouteToServerClient = 5,
        ClientDisconnected = 6,

        Message = 10,
    }
}
