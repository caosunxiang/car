/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: WebscoketConfig
 * Author:   冷酷的苹果
 * Date:     2020/6/2 10:46
 * Description: webscoket配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.webscoketConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 〈一句话功能简述〉<br>
 * 〈webscoket配置〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/2
 * @since 1.0.0
 */
@Configuration
public class WebscoketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
