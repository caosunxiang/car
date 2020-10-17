/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: LoginController
 * Author:   冷酷的苹果
 * Date:     2020/10/17 11:41
 * Description: 小程序登入节吗
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.wechat;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br> 
 * 〈小程序登入解码〉
 *
 * @author 冷酷的苹果
 * @create 2020/10/17
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class LoginController {

    
    /** 
    * @ Description: 小程序登入解码
    * @ Param: [request, encryptedData, iv, session_key]
    * @ return: java.lang.String
    * @ Author: 冷酷的苹果
    * @ Date: 2020/10/17 14:03
    */
    @GetMapping("/decoding")
    public String mini_getPhone(HttpServletRequest request, @Param("encryptedData")String encryptedData, @Param("iv")String iv, @Param("session_key")String session_key)

    {
        //
        JSONObject obj=getPhoneNumber(session_key,encryptedData,iv);//解密电话号码
        //System.out.println(obj);
        String sphone=obj.get("phoneNumber").toString();
        return sphone;

    }

    //解析电话号码
    public JSONObject getPhoneNumber(String session_key, String encryptedData, String iv) {
        byte[] dataByte = Base64.decode(encryptedData);

        byte[] keyByte = Base64.decode(session_key);

        byte[] ivByte = Base64.decode(iv);
        try {

            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
