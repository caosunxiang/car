/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: WxConfig
 * Author:   冷酷的苹果
 * Date:     2021/3/9 14:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.wechat;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/3/9
 * @since 1.0.0
 */
@Component
public class WxConfig {

    @Bean
  public static WxMpService config(){
      WxMpService wxMpService = new WxMpServiceImpl();
      WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage=new WxMpInMemoryConfigStorage();
      wxMpInMemoryConfigStorage.setAppId("wxbf9ff145d7917743");
      wxMpInMemoryConfigStorage.setSecret("d4005b80ab69c312bbd6ce123d5636f5");
      wxMpService.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
      return wxMpService;
  }
}
