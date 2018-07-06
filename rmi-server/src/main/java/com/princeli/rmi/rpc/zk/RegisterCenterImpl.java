package com.princeli.rmi.rpc.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-06 14:37
 **/
public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNNECTION_STR).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,10)).
                build();
        curatorFramework.start();
    }


    @Override
    public void register(String serviceName, String serviceAddress) {
        String servicePath = ZkConfig.ZK_REGISTER_PATH+"/"+serviceName;
            try {

                if(curatorFramework.checkExists().forPath(servicePath)==null){
                    curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath,"0".getBytes());
                }

                String addressPath=servicePath+"/"+serviceAddress;
                String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath,"0".getBytes());

                System.out.println("服务注册成功:"+rsNode);

            } catch (Exception e) {
                e.printStackTrace();
            }


    }
}
