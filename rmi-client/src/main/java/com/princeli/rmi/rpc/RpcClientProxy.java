package com.princeli.rmi.rpc;

import com.princeli.rmi.rpc.zk.IServiceDiscovery;

import java.lang.reflect.Proxy;
import java.rmi.server.RemoteObjectInvocationHandler;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 11:00
 **/
public class RpcClientProxy {

    private IServiceDiscovery serviceDiscovery;

    public RpcClientProxy(IServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public<T>T clientProxy(final Class<T> interfaceCls,String version){
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),new Class[]{interfaceCls},new RemoteInvocationHandler(serviceDiscovery,version));
    }
}
