package com.princeli.rmi.rpc.zk;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-06 15:57
 **/
public interface IServiceDiscovery {
    /**
     * 根据请求的服务地址，获得对应的调用地址
     * @param serviceName
     * @return
     */
    String discover(String serviceName);
}
