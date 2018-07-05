package com.princeli.rmi.api.service.impl;

import com.princeli.rmi.api.service.IRpcHelloService;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 10:52
 **/
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String sayHello(String msg) {
        return "rpc hello,"+msg;
    }
}
