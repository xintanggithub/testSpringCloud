package com.tson.yd.dao;

import com.tson.yd.model.main.MainConfigEntity;

public interface MainDao {

    /**
     * 查询首页配置
     * @return 返回配置
     */
    MainConfigEntity queryMainConfig();

}
