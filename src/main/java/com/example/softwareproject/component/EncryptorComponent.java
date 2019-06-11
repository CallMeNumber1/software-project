package com.example.softwareproject.component;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;



/**
 * @program: software-project
 * @description: 加密解密组件
 * @author: zhanyeye
 * @create: 2019-06-08 13:58
 */

@Slf4j
@Component
public class EncryptorComponent {
    @Value("${my.secretkey}")
    private String secretKey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    private ObjectMapper mapper;
    // 加密
    public String encrypt(Map payload) {
        try {
            String json = mapper.writeValueAsString(payload);
            return Encryptors.text(secretKey, salt).encrypt(json);
        } catch (JsonProcessingException e) {
        }
        return null;
    }
    // 解密
    public Map<String, Object> decrypt(String encryptString) {
        try {
            String json = Encryptors.text(secretKey, salt).decrypt(encryptString);
            return mapper.readValue(json, Map.class);
        } catch (Exception e) {
            //若反序列化时抛异常，则说明 token 是伪造的，未登录！
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录");
        }
    }
}
