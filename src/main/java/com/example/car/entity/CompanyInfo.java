/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CompanyInfo
 * Author:   冷酷的苹果
 * Date:     2020/6/24 11:15
 * Description: 渣土公司
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.entity;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈渣土公司〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/24
 * @since 1.0.0
 */
@Data
public class CompanyInfo {
    /**
     * 公司ID
     * */
    private String CompanyId;
    /**
     * 公司名称
     * */
    private String CompanyName;
    /**
     * 联系人
     * */
    private String Contract;
    /**
     * 联系电话
     * */
    private String Mobile;
}
