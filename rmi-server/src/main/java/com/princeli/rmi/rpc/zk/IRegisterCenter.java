package com.princeli.rmi.rpc.zk;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-06 14:35
 **/
public interface IRegisterCenter {

    void register(String serviceName,String serviceAddress);
}
