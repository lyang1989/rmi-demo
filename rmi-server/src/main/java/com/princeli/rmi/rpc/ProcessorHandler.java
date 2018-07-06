package com.princeli.rmi.rpc;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 09:01
 **/
public class ProcessorHandler implements Runnable {

    private Socket socket;

    Map<String,Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) inputStream.readObject();
            Object result = invoke(request);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i <args.length ; i++) {
            types[i] = args[i].getClass();
        }

        String serviceName=request.getClassName();
        String version=request.getVersion();
        if(version!=null&&!version.equals("")){
            serviceName=serviceName+"-"+version;
        }
        //从handlerMap中，根据客户端请求的地址，去拿到响应的服务，通过反射发起调用
        Object service=handlerMap.get(serviceName);
        Method method=service.getClass().getMethod(request.getMethodName(),types);
        return method.invoke(service,args);

    }
}
