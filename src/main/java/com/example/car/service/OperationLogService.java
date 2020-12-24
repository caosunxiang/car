/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: OperationLogService
 * Author:   冷酷的苹果
 * Date:     2020/12/22 13:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service;

import com.example.car.common.utils.json.Body;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/22
 * @since 1.0.0
 */
public interface OperationLogService {

    Body selectLog(String carid,String userid,Integer id);
}
