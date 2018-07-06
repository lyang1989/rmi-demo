package com.princeli.rmi.rpc;

import com.princeli.rmi.api.service.IRpcHelloService;
import com.princeli.rmi.api.service.impl.RpcHelloServiceImpl;
import com.princeli.rmi.rpc.zk.IRegisterCenter;
import com.princeli.rmi.rpc.zk.RegisterCenterImpl;

import java.io.IOException;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 10:53
 **/
public class RpcServerDemo {
    public static void main(String[] args) throws IOException {
        IRpcHelloService rpcHelloService = new RpcHelloServiceImpl();
        IRegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer rpcServer = new RpcServer(registerCenter,"127.0.0.1:8080");
        rpcServer.bind(rpcHelloService);
        rpcServer.publisher();
        System.in.read();
    }

}
