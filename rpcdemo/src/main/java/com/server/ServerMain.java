package com.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class ServerMain {
    public static void main(String[] margs) {
        try {
            TServerSocket socket = new TServerSocket(7911);
            TServer.Args args = new TServer.Args(socket);
            TProcessor process = new HelloService.Processor(new HelloServiceImpl());
            TBinaryProtocol.Factory portFactory = new TBinaryProtocol.Factory(true, true);
            args.processor(process);
            args.protocolFactory(portFactory);

            TServer server = new TSimpleServer(args);
            server.serve();
            /**
            TNonblockingServerSocket socket = new TNonblockingServerSocket(7911);
            HelloService.Processor processor = new HelloService.Processor(new HelloServiceImpl());

            TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
            arg.protocolFactory(new TBinaryProtocol.Factory());
            arg.transportFactory(new TFramedTransport.Factory());
            arg.processorFactory(new TProcessorFactory(processor));

            TServer server = new TNonblockingServer(arg);
            server.serve();
             */

        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
