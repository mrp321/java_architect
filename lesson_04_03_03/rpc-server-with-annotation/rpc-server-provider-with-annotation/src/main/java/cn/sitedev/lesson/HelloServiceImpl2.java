package cn.sitedev.lesson;

@RpcService(value = IHelloService.class, version = "v2.0")
public class HelloServiceImpl2 implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("HelloServiceImpl[V2.0].sayHello: " + content);
        return "HelloServiceImpl[V2.0].sayHello: " + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("HelloServiceImpl[V2.0].saveUser" + user);
        return "HelloServiceImpl[V2.0].saveUser" + user;
    }
}
