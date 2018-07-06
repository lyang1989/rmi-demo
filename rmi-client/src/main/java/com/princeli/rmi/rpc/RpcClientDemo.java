package com.princeli.rmi.rpc;

import com.princeli.rmi.api.service.IRpcHelloService;
import com.princeli.rmi.rpc.zk.IServiceDiscovery;
import com.princeli.rmi.rpc.zk.ServiceDiscoveryImpl;
import com.princeli.rmi.rpc.zk.ZkConfig;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 10:58
 **/
public class RpcClientDemo {
    public static void main(String[] args) {
        IServiceDiscovery serviceDiscovery=new
                ServiceDiscoveryImpl(ZkConfig.CONNNECTION_STR);
        RpcClientProxy rpcClientProxy=new RpcClientProxy(serviceDiscovery);
        IRpcHelloService hello = rpcClientProxy.clientProxy(IRpcHelloService.class, null);
        System.out.println(hello.sayHello("prince"));
    }





}
