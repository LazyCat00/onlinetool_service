package com.nnutc.system.custom;

import com.nnutc.common.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//自定义密码组件
@Component
public class CustomMd5Password implements PasswordEncoder {

//    密码加密方法
    @Override
    public String encode(CharSequence rawPassword) {
//        return MD5.encrypt(rawPassword.toString());
        return rawPassword.toString();
    }

//    校验密文和明文是否相同
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
        return encode(rawPassword).equals(encodedPassword);
    }
}
