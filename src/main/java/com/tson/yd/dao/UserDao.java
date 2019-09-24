package com.tson.yd.dao;

import com.tson.yd.model.UserEntity;

import java.util.List;

public interface UserDao {

    /**
     * 分页查询用户列表
     *
     * @return 用户列表数据
     */
    List<UserEntity> queryUsers();

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 返回用户ID对应信息
     */
    UserEntity queryUserById(String userId);

    /**
     * 新增用户数据
     *
     * @param userEntity 用户数据
     */
    void insertUser(UserEntity userEntity);

    /**
     * 更新用户信息
     *
     * @param userEntity 用户数据
     */
    void updateUser(UserEntity userEntity);

}
