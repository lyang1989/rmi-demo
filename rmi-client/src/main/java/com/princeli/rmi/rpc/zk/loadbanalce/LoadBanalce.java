package com.princeli.rmi.rpc.zk.loadbanalce;

import java.util.List;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-06 15:59
 **/
public interface LoadBanalce {
    String selectHost(List<String> repos);
}
