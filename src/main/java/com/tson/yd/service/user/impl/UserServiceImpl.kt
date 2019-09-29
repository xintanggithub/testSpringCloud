package com.tson.yd.service.user.impl

import com.alibaba.fastjson.JSON
import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.tson.yd.base.BaseResponse
import com.tson.yd.base.ListBaseData
import com.tson.yd.base.LogCode
import com.tson.yd.dao.UserDao
import com.tson.yd.model.UserEntity
import com.tson.yd.model.request.InsertUserRequest
import com.tson.yd.model.request.UpdateUserRequest
import com.tson.yd.service.user.UserService
import com.tson.yd.utils.CharUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*

@Service(value = "UserService")
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userDao: UserDao

    override fun queryUsers(page: Int, pageSize: Int): BaseResponse<ListBaseData<UserEntity>> {
        LOGGER.info("queryUsers ==> page = $page ， pageSize = $pageSize")
        val response = BaseResponse<ListBaseData<UserEntity>>()
        PageHelper.startPage<Any>(if (page <= 0) 1 else page, if (pageSize <= 0) 10 else pageSize)
        val pageInfo = PageInfo(userDao.queryUsers())
        if (pageInfo.list.isEmpty()) {
            response.resultCode = LogCode.RC_RESULT_EMPTY.code
            response.resultMessage = LogCode.RC_RESULT_EMPTY.message
            return response
        }
        val users = ListBaseData<UserEntity>()
        users.lists = pageInfo.list
        users.page = pageInfo.pageNum
        users.pageSize = pageInfo.size
        users.totalCount = pageInfo.total.toInt()
        response.data = users
        response.resultCode = LogCode.RC_SUCCESS.code
        response.resultMessage = LogCode.RC_SUCCESS.message
        return response
    }

    override fun queryUserById(userId: String): BaseResponse<UserEntity> {
        LOGGER.info("queryUserById ==> userId = $userId")
        val response = BaseResponse<UserEntity>()
        if (StringUtils.isEmpty(userId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
            return response
        }
        val userEntity = userDao.queryUserById(userId)
        if (null == userEntity) {
            response.setStatus(LogCode.RC_USER_NONE)
            return response
        }
        response.data = userEntity
        response.setStatus(LogCode.RC_SUCCESS)
        return response
    }

    override fun insertUser(userRequest: InsertUserRequest): BaseResponse<String> {
        val userUserId = UUID.randomUUID().toString()
        val userEntity = JSON.parseObject(JSON.toJSONString(userRequest), UserEntity::class.java)
        userEntity.userId = userUserId
        userEntity.userStatus = 1
        userEntity.createTime = System.currentTimeMillis() / CharUtils.MIN_1
        userEntity.updateTime = System.currentTimeMillis() / CharUtils.MIN_1
        userDao.insertUser(userEntity)
        val response = BaseResponse<String>()
        response.data = userUserId
        response.setStatus(LogCode.RC_SUCCESS)
        return response
    }

    override fun updateUser(userEntity: UpdateUserRequest): BaseResponse<Any> {
        val response = BaseResponse<Any>()
        if (!StringUtils.isEmpty(userEntity.userId)) {
            val queryCheck = userDao.queryUserById(userEntity.userId)
            if (null != queryCheck) {
                val jsonStr = JSON.toJSONString(userEntity)
                LOGGER.debug(jsonStr)
                val updateUserEntity = JSON.parseObject(jsonStr, UserEntity::class.java)
                updateUserEntity.updateTime = System.currentTimeMillis() / CharUtils.MIN_1
                userDao.updateUser(updateUserEntity)
                response.setStatus(LogCode.RC_SUCCESS)
            } else {
                response.setStatus(LogCode.RC_USER_NONE)
            }
        } else {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
        }
        return response
    }

    override fun deleteUser(userId: String): BaseResponse<*> {
        val response = BaseResponse<Any>()
        if (StringUtils.isEmpty(userId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR)
        } else {
            val queryEntity = userDao.queryUserById(userId)
            if (null != queryEntity) {
                userDao.deleteUser(userId)
                response.setStatus(LogCode.RC_SUCCESS)
            } else {
                response.setStatus(LogCode.RC_USER_NONE)
            }
        }
        return response
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }

}
