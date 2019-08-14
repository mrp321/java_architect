package cn.sitedev.lesson;

@RpcService(value = IHelloService.class, version = "v1.0")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("HelloServiceImpl[V1.0].sayHello: " + content);
        return "HelloServiceImpl[V1.0].sayHello: " + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("HelloServiceImpl[V1.0].saveUser" + user);
        return "HelloServiceImpl[V1.0].saveUser" + user;
    }
}
