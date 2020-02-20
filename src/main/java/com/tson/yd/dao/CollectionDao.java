package com.tson.yd.dao;

import com.tson.yd.model.collection.CollectionEntity;
import com.tson.yd.model.collection.CollectionResponseEntity;

import java.util.List;

public interface CollectionDao {

    /**
     * 新增收藏
     *
     * @param request 收藏请求实体
     */
    void insertCollection(CollectionEntity request);

    /**
     * 删除收藏
     *
     * @param userId 用户ID
     * @param gameId gameId
     */
    void deleteCollection(String userId, int gameId, int collectionType);

    /**
     * 查询用户的所有收藏
     *
     * @param userId 用户ID
     * @return 收藏记录
     */
    List<CollectionResponseEntity> queryCollectionByUser(String userId, int collectionType);

    /**
     * 查询某一条收藏记录
     *
     * @param userId 用户ID
     * @param gameId gameId
     * @return 收藏信息
     */
    CollectionResponseEntity queryCollection(String userId, int gameId, int collectionType);
}
