package cn.sitedev.lesson;

import cn.sitedev.lesson.config.MyRpcServerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class ServerApp {

    public static void main(String[] args) throws IOException {
        // 启动容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyRpcServerConfig.class);
        applicationContext.start();
        // 启动服务端, 处理来自客户端的请求
        MyRpcServerRemote myRpcServerRemote = applicationContext.getBean(MyRpcServerRemote.class);
        myRpcServerRemote.handler();
    }
}
