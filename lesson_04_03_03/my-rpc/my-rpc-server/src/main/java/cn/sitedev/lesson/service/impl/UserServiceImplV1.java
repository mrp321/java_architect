package cn.sitedev.lesson.service.impl;

import cn.sitedev.lesson.annotation.MyRpcService;
import cn.sitedev.lesson.entity.User;
import cn.sitedev.lesson.service.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务层实现-版本1
 */
@MyRpcService(type = IUserService.class, version = "v1.0")
public class UserServiceImplV1 implements IUserService {
    /**
     * 用户列表
     */
    private static List<User> list = new ArrayList<>();

    /**
     * 初始化用户列表
     */
    static {
        User zhangsan = new User("0001", "张三", 24);
        User lisi = new User("0002", "李四", 32);
        User wangwu = new User("0003", "王五", 13);
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
        System.out.println("[SERVER]:UserServiceImplV1[v1.0].getUserList");
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
        System.out.println("[SERVER]:UserServiceImplV1[v1.0].getUserById");
        for (User user : list) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
