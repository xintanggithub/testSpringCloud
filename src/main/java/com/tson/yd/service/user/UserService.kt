package com.tson.yd.service.user

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.model.UserEntity
import com.tson.yd.model.request.InsertUserRequest
import com.tson.yd.model.request.UpdateUserRequest

interface UserService {

    /**
     * 分页查询用户列表
     *
     * @param page     页码
     * @param pageSize 每页条数
     * @return 用户列表数据
     */
    fun queryUsers(page: Int, pageSize: Int): BaseResponse<ListBaseData<UserEntity>>

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID
     * @return 返回用户ID对应信息
     */
    fun queryUserById(userId: String): BaseResponse<UserEntity>

    /**
     * 新增用户
     *
     * @param userRequest 用户数据
     * @return 返回用户ID
     */
    fun insertUser(userRequest: InsertUserRequest): BaseResponse<String>

    /**
     * 更新用户信息
     *
     * @param userEntity 用户数据
     * @return 返回更新结果
     */
    fun updateUser(userEntity: UpdateUserRequest): BaseResponse<*>

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return 返回删除结果
     */
    fun deleteUser(userId: String): BaseResponse<*>

}
