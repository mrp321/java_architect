package cn.sitedev.lesson;

public class App {
    public static void main(String[] args) {
        // 生成代理类
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080);
        String result = helloService.sayHello("hello rpc");
        System.out.println(result);
    }
}
