/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: FileProperties
 * Author:   冷酷的苹果
 * Date:     2020/12/2 17:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/2
 * @since 1.0.0
 */
@Component
public class FileProperties {
    @Autowired
    private Environment env;
    public static String mapping;
    public static String address;
    public static String returnUrl;

    @PostConstruct
    public void readConfig() {
        mapping = env.getProperty("upload.mapping");
        address = env.getProperty("upload.address");
        returnUrl = env.getProperty("upload.returnUrl");
    }

}
