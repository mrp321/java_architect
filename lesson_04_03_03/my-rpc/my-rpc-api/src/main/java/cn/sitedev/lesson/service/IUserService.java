package cn.sitedev.lesson.service;

import cn.sitedev.lesson.entity.User;

import java.util.List;

/**
 * 用户业务层接口
 */
public interface IUserService {
    /**
     * 获取用户列表
     *
     * @return
     */
    List<User> getUserList();

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    User getUserById(String id);


}
