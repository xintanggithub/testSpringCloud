package com.tson.yd.service.main.impl

import com.tson.yd.base.BaseResponse
import com.tson.yd.base.LogCode
import com.tson.yd.dao.MainDao
import com.tson.yd.model.main.MainConfigEntity
import com.tson.yd.service.main.MainService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Service
class MainServiceImpl : MainService {

    @Autowired
    private lateinit var mainDao: MainDao

    override fun queryMainConfig(): BaseResponse<MainConfigEntity> {
        return BaseResponse<MainConfigEntity>().also {
            it.data = mainDao.queryMainConfig()
            it.setStatus(LogCode.RC_SUCCESS)
        }
    }
}
