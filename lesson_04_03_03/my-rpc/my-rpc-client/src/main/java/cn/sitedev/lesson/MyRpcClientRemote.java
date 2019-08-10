package cn.sitedev.lesson;

import cn.sitedev.lesson.request.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端远程通信
 */
public class MyRpcClientRemote {
    private String host;
    private int port;

    public MyRpcClientRemote(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object request(RpcRequest rpcRequest) throws IOException {
        // 建立socket连接
        Socket socket = new Socket(host, port);
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            // 发送要传输的数据
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            // 接收服务端返回的数据
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        return null;

    }

}
