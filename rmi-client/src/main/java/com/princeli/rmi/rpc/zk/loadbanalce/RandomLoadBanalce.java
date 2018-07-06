package com.princeli.rmi.rpc.zk.loadbanalce;

import java.util.List;
import java.util.Random;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-06 16:01
 **/
public class RandomLoadBanalce extends AbstractLoadBanalce {
    @Override
    protected String doSelect(List<String> repos) {
        int len=repos.size();
        Random random=new Random();
        return repos.get(random.nextInt(len));
    }
}
