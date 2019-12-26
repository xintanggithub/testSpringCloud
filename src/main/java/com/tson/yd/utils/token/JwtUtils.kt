package com.tson.yd.utils.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import org.apache.commons.lang3.time.DateUtils
import java.io.UnsupportedEncodingException
import java.util.*
import kotlin.collections.HashMap

object JwtUtils {

    private const val SECRET = "322F1169FSADSAEWE74D533535ERQWGFA5FZXCASFF8E7B3A31FW1QDAXFFC923"
    private const val ISSUER = "user_root"
    private const val USER_ID = "userId"
    private const val USER_CODE = "userCode"

    fun create(userId: String, userCode: String): String {
        return createToken(HashMap<String, String>().also {
            it[USER_ID] = userId
            it[USER_CODE] = userCode
        })
    }

    /**
     * 生成token
     */
    @Throws(Exception::class)
    fun createToken(claims: Map<String, String>): String {
        try {
            val algorithm = Algorithm.HMAC256(SECRET)
            val builder = JWT.create()
                    .withIssuer(ISSUER)
                    //设置过期时间为2小时
                    .withExpiresAt(DateUtils.addHours(Date(), 1))
            claims.forEach { name, value -> builder.withClaim(name, value) }
            return builder.sign(algorithm)
        } catch (e: IllegalArgumentException) {
            throw Exception("生成token失败")
        } catch (e: UnsupportedEncodingException) {
            throw Exception("生成token失败")
        }
    }

    /**
     * 验证jwt，并返回数据
     */
    @Throws(Exception::class)
    fun verifyToken(token: String): Map<String, String> {
        val algorithm: Algorithm
        val map: Map<String, Claim>
        try {
            algorithm = Algorithm.HMAC256(SECRET)
            val verifier = JWT.require(algorithm).withIssuer(ISSUER).build()
            val jwt = verifier.verify(token)
            map = jwt.claims
        } catch (e: Exception) {
            throw Exception("鉴权失败")
        }
        val resultMap = HashMap<String, String>(map.size)
        map.forEach { k, v -> resultMap[k] = "${v.asString()}" }
        return resultMap
    }

}
