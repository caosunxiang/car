/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: MuckExcel
 * Author:   冷酷的苹果
 * Date:     2021/2/25 13:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.excel.entity;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/2/25
 * @since 1.0.0
 */
@Data
@Excel("准运证信息")
public class MuckExcel implements Serializable {
    @ExcelField(value = "批准文号")
    private String permitno;
    @ExcelField(value = "批准时间")
    private String approvetime;
    @ExcelField(value = "批准类别")
    private String permittype;
    @ExcelField(value = "申请单位")
    private String companyName;
    @ExcelField(value = "工程名称")
    private String projectName;
    @ExcelField(value = "发证车辆")
    private List<String> car;
    @ExcelField(value = "工程地点")
    private String place;
    @ExcelField(value = "消纳场所")
    private String absorption;
    @ExcelField(value = "渣土种类")
    private String mucktype;
    @ExcelField(value = "批准期限-起")
    private String beginTime;
    @ExcelField(value = "批准期限-止")
    private String endTime;
    @ExcelField(value = "运输线路")
    private String lineName;


}
