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
import com.example.car.common.utils.json.Body;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @Autowired
    private WxMpService wxMpService;


    /**
     * @ Description: 小程序登入解码
     * @ Param: [request, encryptedData, iv, session_key]
     * @ return: java.lang.String
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/17 14:03
     */
    @GetMapping("/decoding")
    public String mini_getPhone(HttpServletRequest request, @Param("encryptedData") String encryptedData,
                                @Param("iv") String iv, @Param("session_key") String session_key) {
        //
        JSONObject obj = getPhoneNumber(session_key, encryptedData, iv);//解密电话号码
        //System.out.println(obj);
        String sphone = obj.get("phoneNumber").toString();
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


    @GetMapping("/authorize")
    public Body authorize(@RequestParam("returnUrl") String returnUrl) {
        String url = "http://47.99.155.20:8080/grant/suanz.html";
        //构造网页授权url
        //可使用WxConsts.OAuth2Scope.SNSAPI_USERINFO模式，也可使用WxConsts.OAuth2Scope.SNSAPI_BASE模式，SNSAPI_BASE模式用户是无感知的
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE , "123");
        log.info("微信网页授权获取code，redirectUrl={}", redirectUrl);
        return Body.newInstance(redirectUrl);
    }

    @GetMapping("/userInfo")
    public Body userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
        //获得access token
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {

            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("[微信网页授权] {}", e);
//            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        //获取openid
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return Body.newInstance(openId);
    }

    @RequestMapping("/token")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null ) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
