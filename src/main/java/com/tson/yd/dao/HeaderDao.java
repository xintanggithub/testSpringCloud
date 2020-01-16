package com.tson.yd.dao;

import com.tson.yd.model.header.HeadEntity;

import java.util.List;

public interface HeaderDao {

    /**
     * 查询头像列表
     */
    List<HeadEntity> queryHead();

}
