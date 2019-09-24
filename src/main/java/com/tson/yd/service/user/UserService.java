package com.tson.yd.service.user;

import com.tson.yd.base.BaseResponse;
import com.tson.yd.base.ListBaseData;
import com.tson.yd.model.UserEntity;
import com.tson.yd.model.request.InsertUserRequest;

public interface UserService {

    /**
     * 分页查询用户列表
     *
     * @param page     页码
     * @param pageSize 每页条数
     * @return 用户列表数据
     */
    BaseResponse<ListBaseData<UserEntity>> queryUsers(int page, int pageSize);

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 返回用户ID对应信息
     */
    BaseResponse<UserEntity> queryUserById(String userId);

    /**
     * 新增用户
     *
     * @param userRequest 用户数据
     * @return 返回用户ID
     */
    BaseResponse<String> insertUser(InsertUserRequest userRequest);

}
