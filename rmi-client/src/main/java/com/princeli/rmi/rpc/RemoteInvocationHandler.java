package com.princeli.rmi.rpc;

import com.princeli.rmi.rpc.zk.IServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 12:32
 **/
public class RemoteInvocationHandler implements InvocationHandler {

    private IServiceDiscovery serviceDiscovery;
    private String version;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery,String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装请求
        RpcRequest request=new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion(version);

        String serviceAddress=serviceDiscovery.discover(request.getClassName());
        TCPTransport tcpTransport=new TCPTransport(serviceAddress);
        return tcpTransport.send(request);
    }
}
