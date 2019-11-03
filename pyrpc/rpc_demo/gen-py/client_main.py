#! /usr/bin/env python
# -*- coding:utf-8 -*-

# @Time    : 2019/11/3 23:06
# @Author  : ljyang

from thrift.protocol import TBinaryProtocol
from thrift.transport import TSocket, TTransport
from rpc_client import HelloService


def main():
    transport = TSocket.TSocket('localhost', 7911)

    # Buffering is critical. Raw sockets are very slow
    transport = TTransport.TBufferedTransport(transport)

# Wrap in a protocol
    protocol = TBinaryProtocol.TBinaryProtocol(transport)

# Create a client to use the protocol encoder
    client = HelloService.Client(protocol)

# Connect!
    transport.open()

    str = client.helloString("qqqqqqqqqqqq")
    print(str)

if __name__ == '__main__':
    main()
