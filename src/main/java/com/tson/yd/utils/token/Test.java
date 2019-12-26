package com.tson.yd.utils.token;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("loginId", "USED_TEST");
        map.put("userName", "testName");
        String accessToken;
        try {
            accessToken = JwtUtils.INSTANCE.createToken(map);
            System.out.println("accessToken ===> " + accessToken);

            Map<String, String> map2 = JwtUtils.INSTANCE.verifyToken(accessToken);
            System.out.println("verifyToken ===> " + JSON.toJSONString(map2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
