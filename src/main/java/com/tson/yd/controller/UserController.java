package com.tson.yd.controller;

import com.tson.yd.base.BaseResponse;
import com.tson.yd.base.ListBaseData;
import com.tson.yd.model.UserEntity;
import com.tson.yd.model.request.InsertUserRequest;
import com.tson.yd.model.request.UpdateUserRequest;
import com.tson.yd.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("UserController")
@RequestMapping("/v1/user")
@Api(value = "UserController 用户", description = "user", tags = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUsers", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询用户信息", notes = "v1.0.0")
    public BaseResponse<ListBaseData<UserEntity>> queryUsers(@ApiParam(required = true, name = "page", value = "页数")
                                                             @RequestParam(value = "page", required = true) int page,
                                                             @ApiParam(required = true, name = "pageSize", value = "每页条数")
                                                             @RequestParam(value = "pageSize", required = true) int pageSize) {
        return userService.queryUsers(page, pageSize);
    }

    @RequestMapping(value = "/queryUserById", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户ID查询用户信息", notes = "v1.0.0")
    public BaseResponse<UserEntity> queryUserById(@ApiParam(required = true, name = "userId", value = "用户ID")
                                                  @RequestParam(value = "userId", required = true) String userId) {
        return userService.queryUserById(userId);
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户", notes = "v1.0.0")
    public BaseResponse<String> insertUser(@RequestBody InsertUserRequest userRequest) {
        return userService.insertUser(userRequest);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户信息", notes = "v1.0.0")
    public BaseResponse updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户ID删除用户信息", notes = "v1.0.0")
    public BaseResponse deleteUser(@ApiParam(required = true, name = "userId", value = "用户ID")
                                   @RequestParam(value = "userId", required = true) String userId) {
        return userService.deleteUser(userId);
    }

}
