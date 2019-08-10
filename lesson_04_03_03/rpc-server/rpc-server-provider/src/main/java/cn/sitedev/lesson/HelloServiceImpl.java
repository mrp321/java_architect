package cn.sitedev.lesson;

public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("HelloServiceImpl.sayHello: " + content);
        return "HelloServiceImpl.sayHello: " + content;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("HelloServiceImpl.saveUser" + user);
        return "HelloServiceImpl.saveUser" + user;
    }
}
