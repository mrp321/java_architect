package cn.sitedev.lesson.service.impl;

import cn.sitedev.lesson.annotation.MyRpcService;
import cn.sitedev.lesson.entity.User;
import cn.sitedev.lesson.service.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务层实现-版本2
 */
@MyRpcService(type = IUserService.class, version = "v2.0")
public class UserServiceImplV2 implements IUserService {
    /**
     * 用户列表
     */
    private static List<User> list = new ArrayList<>();

    /**
     * 初始化用户列表
     */
    static {
        User zhangsan = new User("0001", "张三V2", 56);
        User lisi = new User("0002", "李四V2", 85);
        User wangwu = new User("0003", "王五V2", 34);
        list.add(zhangsan);
        list.add(lisi);
        list.add(wangwu);
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public List<User> getUserList() {
        System.out.println("[SERVER]:UserServiceImplV2[v2.0].getUserList");
        return list;
    }

    /**
     * 根据id获取指定用户
     *
     * @param id 用户id
     * @return
     */
    @Override
    public User getUserById(String id) {
        System.out.println("[SERVER]:UserServiceImplV2[v2.0].getUserById");
        for (User user : list) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
