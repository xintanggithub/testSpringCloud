package com.tson.yd.controller;

import com.tson.yd.base.BaseResponse;
import com.tson.yd.base.ListBaseData;
import com.tson.yd.model.UserEntity;
import com.tson.yd.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("UserController")
@RequestMapping("/v1/user")
@Api(value = "UserController 用户", description = "user", tags = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUsers", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询用户信息", notes = "v1.0.0")
    public BaseResponse<ListBaseData<UserEntity>> queryUsers(@ApiParam(required = true, name = "pageNum", value = "页数")
                                                             @RequestParam(value = "pageNum", required = true) int page,
                                                             @ApiParam(required = true, name = "pageSize", value = "条数")
                                                             @RequestParam(value = "pageSize", required = true) int pageSize) {
        return userService.queryUsers(page, pageSize);
    }


}
