package com.tson.yd.dao;

import com.tson.yd.model.star.BaseStar;
import com.tson.yd.model.star.StarResponse;

import java.util.List;

public interface StarDao {

    /**
     * 插入评星数据
     *
     * @param baseStar 评星基本数据
     */
    void insertStar(BaseStar baseStar);

    /**
     * 更新评星状态
     */
    void updateStatus(BaseStar baseStar);

    /**
     * 查看该点赞的重复数
     */
    Integer queryRetryStar(BaseStar baseStar);

    /**
     * 查看被点赞数量
     *
     * @param userId 被点赞人的ID
     * @return 被点赞数
     */
    Integer queryBStar(String userId);

    /**
     * 查看自己点赞过的人
     *
     * @param starUserId 点赞人的ID
     * @return 点赞数
     */
    Integer queryWStar(String starUserId);

    /**
     * 查询评论我的评星列表
     *
     * @param userId 用户ID
     * @return 评星列表
     */
    List<StarResponse> queryStarList(String userId);


    /**
     * 查询我的评论评星列表
     *
     * @param starUserId 点赞用户ID
     * @return 评星列表
     */
    List<StarResponse> queryWStarList(String starUserId);

}
