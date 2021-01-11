/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: test
 * Author:   冷酷的苹果
 * Date:     2021/1/8 13:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.wechat;

import com.example.car.common.utils.redis.RedisCacheConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInRedisConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceJoddHttpImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceOkHttpImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/8
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class BindingController {
    @Autowired
    private RedisCacheConfiguration redisConfigure;

    @RequestMapping("tt")
    public  void tt() throws WxErrorException {
        WxMpService wxMpService=new WxMpServiceHttpClientImpl();
        WxMpInRedisConfigStorage redisConfigStorage=new WxMpInRedisConfigStorage(redisConfigure.redisPoolFactory());
        redisConfigStorage.setAppId("wx7513f9ef191ec705");
        redisConfigStorage.setSecret("d33477fd7f24a92a7d7f56288c42048b");
        wxMpService.setWxMpConfigStorage(redisConfigStorage);
        String accessToken=wxMpService.getAccessToken();
        System.out.println("微信官网获取到的"+accessToken);
    }
}
