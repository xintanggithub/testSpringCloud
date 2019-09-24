package com.tson.yd.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tson.yd.base.BaseResponse;
import com.tson.yd.base.ListBaseData;
import com.tson.yd.base.LogCode;
import com.tson.yd.dao.UserDao;
import com.tson.yd.model.UserEntity;
import com.tson.yd.model.request.InsertUserRequest;
import com.tson.yd.model.request.UpdateUserRequest;
import com.tson.yd.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static com.tson.yd.utils.CharUtils.MIN_1;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public BaseResponse<ListBaseData<UserEntity>> queryUsers(int page, int pageSize) {
        LOGGER.info("queryUsers ==> page = " + page + " ， pageSize = " + pageSize);
        if (page <= 0) {
            page = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        BaseResponse<ListBaseData<UserEntity>> response = new BaseResponse<>();
        PageHelper.startPage(page, pageSize);
        PageInfo<UserEntity> pageInfo = new PageInfo<>(userDao.queryUsers());
        if (pageInfo.getList().isEmpty()) {
            response.setResultCode(LogCode.RC_RESULT_EMPTY.getCode());
            response.setResultMessage(LogCode.RC_RESULT_EMPTY.getMessage());
            return response;
        }
        ListBaseData<UserEntity> users = new ListBaseData<>();
        users.setLists(pageInfo.getList());
        users.setPage(pageInfo.getPageNum());
        users.setPageSize(pageInfo.getSize());
        users.setTotalCount((int) pageInfo.getTotal());
        response.setData(users);
        response.setResultCode(LogCode.RC_SUCCESS.getCode());
        response.setResultMessage(LogCode.RC_SUCCESS.getMessage());
        return response;
    }

    @Override
    public BaseResponse<UserEntity> queryUserById(String userId) {
        LOGGER.info("queryUserById ==> userId = " + userId);
        BaseResponse<UserEntity> response = new BaseResponse<>();
        if (StringUtils.isEmpty(userId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR);
            return response;
        }
        UserEntity userEntity = userDao.queryUserById(userId);
        if (null == userEntity) {
            response.setStatus(LogCode.RC_USER_NONE);
            return response;
        }
        response.setData(userEntity);
        response.setStatus(LogCode.RC_SUCCESS);
        return response;
    }

    @Override
    public BaseResponse<String> insertUser(InsertUserRequest userRequest) {
        String userUserId = UUID.randomUUID().toString();
        UserEntity userEntity = JSON.parseObject(JSON.toJSONString(userRequest), UserEntity.class);
        userEntity.setUserId(userUserId);
        userEntity.setUserStatus(1);
        userEntity.setCreateTime(System.currentTimeMillis() / MIN_1);
        userEntity.setUpdateTime(System.currentTimeMillis() / MIN_1);
        userDao.insertUser(userEntity);
        BaseResponse<String> response = new BaseResponse<>();
        response.setData(userUserId);
        response.setStatus(LogCode.RC_SUCCESS);
        return response;
    }

    @Override
    public BaseResponse updateUser(UpdateUserRequest userEntity) {
        BaseResponse response = new BaseResponse();
        if (null != userEntity && !StringUtils.isEmpty(userEntity.getUserId())) {
            UserEntity queryCheck = userDao.queryUserById(userEntity.getUserId());
            if (null != queryCheck) {
                String jsonStr = JSON.toJSONString(userEntity);
                LOGGER.debug(jsonStr);
                UserEntity updateUserEntity = JSON.parseObject(jsonStr, UserEntity.class);
                updateUserEntity.setUpdateTime(System.currentTimeMillis() / MIN_1);
                userDao.updateUser(updateUserEntity);
                response.setStatus(LogCode.RC_SUCCESS);
            } else {
                response.setStatus(LogCode.RC_USER_NONE);
            }
        } else {
            response.setStatus(LogCode.RC_PARAMETER_ERROR);
        }
        return response;
    }

    @Override
    public BaseResponse deleteUser(String userId) {
        BaseResponse response = new BaseResponse();
        if (StringUtils.isEmpty(userId)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR);
        } else {
            UserEntity queryEntity = userDao.queryUserById(userId);
            if (null != queryEntity) {
                userDao.deleteUser(userId);
                response.setStatus(LogCode.RC_SUCCESS);
            } else {
                response.setStatus(LogCode.RC_USER_NONE);
            }
        }
        return response;
    }

}
