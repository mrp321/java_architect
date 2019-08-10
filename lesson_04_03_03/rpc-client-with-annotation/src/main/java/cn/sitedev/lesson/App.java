package cn.sitedev.lesson;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcProxyClient = applicationContext.getBean(RpcProxyClient.class);
        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080, "v2.0");
        String result = helloService.sayHello("hello rpc....");
        System.out.println("client result: " + result);
        applicationContext.start();
    }
}
