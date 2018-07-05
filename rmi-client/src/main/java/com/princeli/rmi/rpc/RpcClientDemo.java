package com.princeli.rmi.rpc;

import com.princeli.rmi.api.service.IRpcHelloService;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 10:58
 **/
public class RpcClientDemo {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IRpcHelloService rpcHelloService = rpcClientProxy.clientProxy(IRpcHelloService.class,"localhost",8888);
        System.out.println(rpcHelloService.sayHello("prince"));
    }





}
