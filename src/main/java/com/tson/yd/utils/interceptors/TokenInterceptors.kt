package com.tson.yd.utils.interceptors

import com.alibaba.fastjson.JSON
import com.tson.yd.utils.token.JwtUtils
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.thymeleaf.util.StringUtils
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class TokenInterceptors : HandlerInterceptor {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TokenInterceptors::class.java)
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val accessToken = request.getHeader("AccessToken")
        LOGGER.info("headers  ==> ${JSON.toJSONString(request.headerNames)}")
        LOGGER.info("method ===> ${request.method}")
        if (request.method == "OPTIONS") {
            return true
        }
        val param = getAllRequestParam(request)
        if (StringUtils.isEmpty(accessToken) || !param.containsKey("userId")) {
            LOGGER.info("accessToken为空或者param中无userId，鉴权失败")
            return false
        }
        val map = JwtUtils.verifyToken(accessToken)
        if (map["userId"] != param["userId"]) {
            return false
        }
        return true
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        super.postHandle(request, response, handler, modelAndView)
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        super.afterCompletion(request, response, handler, ex)
    }

    private fun getAllRequestParam(request: HttpServletRequest): Map<String, String> {
        val res = HashMap<String, String>()
        val temp = request.parameterNames
        if (null != temp) {
            while (temp.hasMoreElements()) {
                val en = temp.nextElement() as String
                val value = request.getParameter(en)
                res[en] = value
            }
        }
        return res
    }

}