package cn.sitedev.lesson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 发布/暴露服务
 */
public class RpcProxyServer {

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(IHelloService helloService, int port) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            // 不断接收请求
            while (true) {
                Socket socket = serverSocket.accept(); // BIO
                // 每个socket交给一个processorHandler处理
                executorService.execute(new ProcessorHandler(helloService, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
