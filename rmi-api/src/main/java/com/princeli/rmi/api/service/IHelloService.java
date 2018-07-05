package com.princeli.rmi.api.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-04 16:57
 **/
public interface IHelloService extends Remote {
    /**
     * hello方法
     * @param msg
     * @return
     * @throws RemoteException
     */
    public String sayHello(String msg) throws RemoteException;
}
