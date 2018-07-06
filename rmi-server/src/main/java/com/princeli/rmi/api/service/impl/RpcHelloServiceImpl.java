package com.princeli.rmi.api.service.impl;

import com.princeli.rmi.api.service.IRpcHelloService;
import com.princeli.rmi.rpc.zk.anno.RpcAnnotation;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 10:52
 **/
@RpcAnnotation(IRpcHelloService.class)
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String sayHello(String msg) {
        return "rpc hello,"+msg;
    }
}
