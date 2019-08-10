package cn.sitedev.lesson;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        IHelloService helloService = new HelloServiceImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(helloService, 8080);

    }
}
