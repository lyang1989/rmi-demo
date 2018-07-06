package com.princeli.rmi.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-05 12:45
 **/
public class TCPTransport {

    private String serviceAddress;

    public TCPTransport(String serviceAddress) {
        this.serviceAddress=serviceAddress;
    }
    private Socket newSocket(){
        System.out.println("创建一个新的连接");
        Socket socket;
        try {
            String[] arrs=serviceAddress.split(":");
            socket=new Socket(arrs[0],Integer.parseInt(arrs[1]));
            return socket;
        } catch (Exception e) {
            throw new RuntimeException("连接建立失败",e);
        }
    }


    public Object send(RpcRequest request){
        Socket socket = null;
        socket = newSocket();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object result = inputStream.readObject();
            inputStream.close();
            outputStream.close();

            return result;
        } catch (Exception e) {
            throw new RuntimeException("发起远程调用异常",e);
        }finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
