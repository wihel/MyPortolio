using System;

namespace TCPClient01
{
    class Const
    {
        public const string ServerClientID = "<scid>"; //passed from the server to the connecting client
        public const string ClientName = "<cn>"; //passed from the connecting client to the server
        public const string TotalServerClients = "<tsc>"; //total clients connected to server passed to connecting server
        public const string ServerClientName = "<scn>"; //name of connected client passed to connecting client
        public const string NewClientConnected = "<ncn>"; //name of connecting client pased from server to all active clients
        public const string RouteToServerClient = "<rsc>"; //route message through server to specified client
        public const string ClientDisconnected = "<cnd>"; //sent to active clients from server to remove client name

        public const string Message = "<msg>"; //message passed to server

        public const char CmdMessageDeliminator = ':';
        public const char ClientNameDataDeliminator = '|';
    }
}
