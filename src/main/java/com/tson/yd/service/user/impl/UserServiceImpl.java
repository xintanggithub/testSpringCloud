package com.tson.yd.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tson.yd.base.BaseResponse;
import com.tson.yd.base.ListBaseData;
import com.tson.yd.base.LogCode;
import com.tson.yd.dao.UserDao;
import com.tson.yd.model.UserEntity;
import com.tson.yd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public BaseResponse<ListBaseData<UserEntity>> queryUsers(int page, int pageSize) {
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
        users.setPageSize(pageInfo.getPageSize());
        users.setTotalCount((int) pageInfo.getTotal());
        response.setData(users);
        response.setResultCode(LogCode.RC_SUCCESS.getCode());
        response.setResultMessage(LogCode.RC_SUCCESS.getMessage());
        return response;
    }

}
