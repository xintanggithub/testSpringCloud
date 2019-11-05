package com.tson.yd.dao;

import com.tson.yd.model.game.GameEntity;
import com.tson.yd.model.game.request.GameRequest;

import java.util.List;

public interface GameDao {

    /**
     * 新增
     *
     * @param request 新增内容
     */
    void insertGame(GameRequest request);

    /**
     * 删除
     *
     * @param id 需要删除的ID
     */
    void deleteGame(Integer id);

    /**
     * 查询所有
     *
     * @return 数据列表
     */
    List<GameEntity> queryList();

}
