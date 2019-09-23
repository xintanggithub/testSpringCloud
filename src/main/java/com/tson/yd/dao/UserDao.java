package com.tson.yd.dao;

import com.tson.yd.model.UserEntity;

import java.util.List;

public interface UserDao {

    List<UserEntity> queryUsers();

}
