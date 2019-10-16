package com.tson.yd.utils;

import org.apache.tomcat.util.security.MD5Encoder
import org.slf4j.LoggerFactory
import org.springframework.util.StringUtils
import sun.security.provider.MD5
import java.math.BigDecimal
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.experimental.and

/**
 *  Created tangxin
 *  Time 2019/5/5 1:38 PM
 */
class CharUtils {

    companion object {

        const val MIN_1 = 1000;

        private val LOGGER = LoggerFactory.getLogger(CharUtils::class.java)

        fun getUserUUID(): String {
            val uuid = md5Upper(getRandomString(14))
            LOGGER.warn("getUserUUID = $uuid")
            return uuid
        }

        fun getPassword(password: String): String {
            var mdStr1 = md5Lower(password)
            LOGGER.warn("mdStr1 = $mdStr1")
            mdStr1 = md5Upper(mdStr1)
            LOGGER.warn("mdStr2 = $mdStr1")
            return mdStr1
        }

        fun getBookId():String{
            return "book${UUID.randomUUID()}"
        }

        fun verificationCode(): String {
            return getRandomString(6, "0123456789")
        }

        fun getRandomString(length: Int): String {
            return getRandomString(length, "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTWVUXYZ0123456789")
        }

        fun getRandomString(length: Int, base: String): String { //length表示生成字符串的长度
            val random = Random()
            val sb = StringBuilder()
            for (i in 0 until length) {
                val number = random.nextInt(base.length)
                sb.append(base[number])
            }
            return sb.toString()
        }

        fun md5Upper(string: String): String {
            return md5(string, true)
        }

        fun md5Lower(string: String): String {
            return md5(string, false)
        }

        fun md5(string: String, isUpper: Boolean): String {
            if (StringUtils.isEmpty(string)) {
                return ""
            }
            val md5: MessageDigest
            try {
                md5 = MessageDigest.getInstance("MD5")
                val bytes = md5.digest(string.toByteArray())
                val result = StringBuilder()
                for (b in bytes) {
                    var temp = Integer.toHexString((b and 0xff.toByte()).toInt())
                    if (temp.length == 1) {
                        temp = "0$temp"
                    }
                    result.append(temp)
                }
                if (isUpper) {
                    return result.toString().toUpperCase()
                } else {
                    return result.toString().toLowerCase()
                }
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return ""
        }

        /**
         * 判断字符串是否为纯数字
         *
         * @param str 字符串
         * @return 是否纯数字 boolean
         */
        fun isNumber(str: String): Boolean {
            for (i in 0 until str.length) {
                if (!Character.isDigit(str[i])) {
                    return false
                }
            }
            return true
        }

        fun getFileSize(size: Long): String {
            val kiloByte = (size / 1024).toDouble()
            if (kiloByte < 1) {
                return "0K"
            }

            val megaByte = kiloByte / 1024
            if (megaByte < 1) {
                val result1 = BigDecimal(kiloByte.toString())
                return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString() + "KB"
            }

            val gigaByte = megaByte / 1024
            if (gigaByte < 1) {
                val result2 = BigDecimal(megaByte.toString())
                return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString() + "MB"
            }

            val teraBytes = gigaByte / 1024
            if (teraBytes < 1) {
                val result3 = BigDecimal(gigaByte.toString())
                return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                        .toPlainString() + "GB"
            }
            val result4 = BigDecimal(teraBytes)
            return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
        }

        fun isEmail(string: String): Boolean {
            val regEx1: String = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            val p = Pattern.compile(regEx1)
            val m = p.matcher(string)
            return m.matches()
        }

    }

}