package com.princeli.rmi.rpc;

import com.princeli.rmi.rpc.zk.IRegisterCenter;
import com.princeli.rmi.rpc.zk.anno.RpcAnnotation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: rmi-demo
 * @description: rpc服务类
 * @author: ly
 * @create: 2018-07-05 08:56
 **/
public class RpcServer {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 注册中心
     */
    private IRegisterCenter registerCenter;
    /**
     * 服务发布地址
     */
    private String serviceAddress;

    /**
     * 服务名称和服务对象的关系
     */
    Map<String,Object> handlerMap = new HashMap<>();

    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    public void bind(Object...services){
        for (Object service:services) {
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String serviceName = annotation.value().getName();
            String version = annotation.version();
            if (version!=null&&!version.equals("")){
                serviceName = serviceName+"-"+version;
            }
            handlerMap.put(serviceName,service);
        }
    }

    public void publisher(){
        ServerSocket serverSocket = null;
        try {
            String[] addrs = serviceAddress.split(":");
            serverSocket=new ServerSocket(Integer.parseInt(addrs[1]));

            for (String interfaceName:handlerMap.keySet()) {
                registerCenter.register(interfaceName,serviceAddress);
            }

            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket,handlerMap));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
