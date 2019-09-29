package com.tson.yd.service.main

import com.tson.yd.base.BaseResponse
import com.tson.yd.model.main.MainConfigEntity

interface MainService {

    /**
     * 查询首页配置
     * @return 返回配置
     */
    fun queryMainConfig(): BaseResponse<MainConfigEntity>

}
