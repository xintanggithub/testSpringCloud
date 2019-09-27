package com.tson.yd.service.login.impl;

import com.tson.yd.base.BaseResponse;
import com.tson.yd.base.LogCode;
import com.tson.yd.dao.LoginDao;
import com.tson.yd.model.login.LoginEntity;
import com.tson.yd.service.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service(value = "LoginService")
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginDao userDao;

    @Override
    public BaseResponse<LoginEntity> queryLogin(String userCode) {
        LOGGER.debug("queryLogin ---> userCode = " + userCode);
        BaseResponse<LoginEntity> response = new BaseResponse<>();
        if (StringUtils.isEmpty(userCode)) {
            response.setStatus(LogCode.RC_PARAMETER_ERROR);
        } else {
            LoginEntity loginEntity = userDao.queryLogin(userCode);
            response.setData(loginEntity);
            response.setStatus(LogCode.RC_SUCCESS);
        }
        return response;
    }
}
