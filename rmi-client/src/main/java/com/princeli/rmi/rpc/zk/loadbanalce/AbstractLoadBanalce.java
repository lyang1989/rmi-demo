package com.princeli.rmi.rpc.zk.loadbanalce;

import java.util.List;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-06 16:00
 **/
public abstract class AbstractLoadBanalce implements LoadBanalce{
    @Override
    public String selectHost(List<String> repos) {
        if(repos==null||repos.size()==0){
            return null;
        }
        if(repos.size()==1){
            return repos.get(0);
        }
        return doSelect(repos);
    }

    protected  abstract String doSelect(List<String> repos);
}
