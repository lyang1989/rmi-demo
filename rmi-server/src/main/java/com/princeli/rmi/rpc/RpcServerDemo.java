package com.princeli.rmi.rpc;

import com.princeli.rmi.api.service.IRpcHelloService;
import com.princeli.rmi.api.service.impl.RpcHelloServiceImpl;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 10:53
 **/
public class RpcServerDemo {
    public static void main(String[] args) {
        IRpcHelloService rpcHelloService = new RpcHelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(rpcHelloService,8888);
    }

}
