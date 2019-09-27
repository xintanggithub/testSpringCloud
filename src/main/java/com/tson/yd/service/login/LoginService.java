package com.tson.yd.service.login;

import com.tson.yd.base.BaseResponse;
import com.tson.yd.model.login.LoginEntity;

public interface LoginService {

    /**
     * 根据账号查询账号信息
     *
     * @param userCode 账号
     * @return 账号信息
     */
    BaseResponse<LoginEntity> queryLogin(String userCode);

}
