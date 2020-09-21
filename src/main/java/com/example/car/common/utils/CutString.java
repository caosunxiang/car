/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CutString
 * Author:   冷酷的苹果
 * Date:     2020/7/21 9:22
 * Description: 切割字符串
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈切割字符串〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/21
 * @since 1.0.0
 */
public class CutString {
    /*** 
    * @Description: 切割字符串
    * @Param: [msg]
    * @return: java.util.List<java.lang.String>
    * @Author: 冷酷的苹果
    * @Date: 2020/7/21 9:29
    */
    public static List<String> divide(String msg) {
        List<String> list = new ArrayList<String>();
        msg = msg + ",";
        char a[] = msg.toCharArray();
        Integer c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == ',') {
                String string = msg.substring(c, i);
                c = i + 1;
                System.out.println(string);
                list.add(string);
            }
        }
        return list;
    }
}
