/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: MuckController
 * Author:   冷酷的苹果
 * Date:     2020/6/22 10:29
 * Description: 准运证
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.MuckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈准运证〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/22
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MuckController {

    private final MuckService muckService;
/** 
* @Description: 根据车牌号 准运证号 查询准运证信息
* @Param: [carNo, permitNo]
* @return: com.example.car.common.utils.json.Body
* @Author: 冷酷的苹果
* @Date: 2020/6/22 10:33
*/
    @RequestMapping("selectMuck")
    public Body selectMuck(String carNo, String permitNo,String time) {
        return this.muckService.selectMuck(carNo, permitNo,time);
    }

    /** 
    * @Description: 工程名称模糊查询工程信息
    * @Param: [name]
    * @return: com.example.car.common.utils.json.Body
    * @Author: 冷酷的苹果
    * @Date: 2020/6/22 10:34
    */
    @RequestMapping("selectMuckByName")
    public Body selectMuckByName(String projectName,String time,String name,String endTime,Integer index,Integer size) {
        return this.muckService.selectMuckByName(projectName,time,name,endTime,index,size);
    }

    /** 
    * @Description: 工程id查询工程下的准运证信息
    * @Param: [projectId]
    * @return: com.example.car.common.utils.json.Body
    * @Author: 冷酷的苹果
    * @Date: 2020/6/22 10:34
    */
    @RequestMapping("selectMuckByProject")
    public Body selectMuckByProject(String projectId,String time) {
        return this.muckService.selectMuckByProject(projectId,time);
    }

    /**
    * @Description: 查询准运证数量
    * @Param: [projectName, time, name, endTime]
    * @return: com.example.car.common.utils.json.Body
    * @Author: 冷酷的苹果
    * @Date: 2020/7/27 9:33
    */
    @RequestMapping("selectMuckCount")
    public Body selectMuckCount(String projectName, String time, String name, String endTime) {
        return this.muckService.selectMuckCount(projectName, time, name, endTime);
    }
}

