package com.tson.yd.service.user;

import com.tson.yd.base.BaseResponse;
import com.tson.yd.base.ListBaseData;
import com.tson.yd.model.UserEntity;

public interface UserService {

    BaseResponse<ListBaseData<UserEntity>> queryUsers(int page, int pageSize);

}
