package cn.sitedev.lesson;

import cn.sitedev.lesson.config.MyRpcClientConfig;
import cn.sitedev.lesson.service.IUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        // 启动容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyRpcClientConfig.class);
        applicationContext.start();

        MyRpcClientRemote myRpcClientRemote = applicationContext.getBean(MyRpcClientRemote.class);
        // 获取要调用的服务的动态代理对象
        IUserService userService = (IUserService) Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class<?>[]{IUserService.class}, new MyProxyInstance(myRpcClientRemote, "v1.0"));
        Object result = userService.getUserById("0002");
        System.out.println("[CLIENT]:IUserService.getUserById=>" + result);

        userService = (IUserService) Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class<?>[]{IUserService.class}, new MyProxyInstance(myRpcClientRemote, "v2.0"));
        result = userService.getUserList();
        System.out.println("[CLIENT]:IUserService.getUserList=>" + result);

    }
}
