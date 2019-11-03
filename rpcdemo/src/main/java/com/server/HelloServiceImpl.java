package com.server;

import org.apache.thrift.TException;

public class HelloServiceImpl implements HelloService.Iface {
    @Override
    public String helloString(String para) throws TException {
        return "server: " + para;
    }
}
