/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: M01Service
 * Author:   冷酷的苹果
 * Date:     2020/12/30 16:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.M01;

import java.text.ParseException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/30
 * @since 1.0.0
 */
public interface M01Service {
    Body selectM01Details(String MustId, String name, String phone) throws ParseException;

    Body updateM01(M01 m01);

    Body insertM01(String Creator,String M0101,String M0102,String M0103,String M0104,String M0105,String M0106,String M0107,String M0108,String M0109,String ShortSpell);

    Body uploadQRCode(String files, String Status, String MustId);

    Body M01Check(String MustId,String Status);
}
