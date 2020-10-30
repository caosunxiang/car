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
import com.example.car.entity.M04;
import com.example.car.entity.M07;
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
    public Body selectMuck(String carNo, String permitNo, String time) {
        return this.muckService.selectMuck(carNo, permitNo, time);
    }

    /**
     * @Description: 工程名称模糊查询工程信息
     * @Param: [name]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/22 10:34
     */
    @RequestMapping("selectMuckByName")
    public Body selectMuckByName(String projectName, String time, String name, String endTime, Integer index,
                                 Integer size) {
        return this.muckService.selectMuckByName(projectName, time, name, endTime, index, size);
    }

    /**
     * @Description: 工程id查询工程下的准运证信息
     * @Param: [projectId]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/22 10:34
     */
    @RequestMapping("selectMuckByProject")
    public Body selectMuckByProject(String projectId, String time) {
        return this.muckService.selectMuckByProject(projectId, time);
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

    /**
     * @Description: 查询车辆详情
     * @Param: [carNo]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/9/17 13:48
     */
    @RequestMapping("selectCarInfo")
    public Body selectCarInfo(String carNo) {
        return this.muckService.selectCarInfo(carNo);
    }

    /**
     * @ Description: 查询消纳场所
     * @ Param: []
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/15 9:17
     */
    @RequestMapping("selectGivenPlace")
    public Body selectGivenPlace() {
        return this.muckService.selectGivenPlace();
    }

    /**
     * @ Description: 查询工地信息
     * @ Param: []
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/15 9:18
     */
    @RequestMapping("selectConstructionSite")
    public Body selectConstructionSite() {
        return this.muckService.selectConstructionSite();
    }

    /**
     * @ Description: 修改消纳场所
     * @ Param: [m07]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/15 9:52
     */
    @RequestMapping("updateGivenPlace")
    public Body updateGivenPlace(M07 m07) {
        return this.muckService.updateGivenPlace(m07);
    }

    /**
     * @ Description: 修改工地
     * @ Param: [m04]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/15 9:52
     */
    @RequestMapping("updateConstructionSite")
    public Body updateConstructionSite(M04 m04) {
        return this.muckService.updateConstructionSite(m04);
    }

    /**
     * @ Description: 二维码跳转准允资格接口
     * @ Param: [companyId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/15 17:32
     */

    @RequestMapping("selectCarInfoByCompany")
    public Body selectCarInfoByCompany(String companyId) {
        return this.muckService.selectCarInfoByCompany(companyId);
    }

    /**
     * @ Description: 消纳场所详情
     * @ Param: [recid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/22 17:37
     */
    @RequestMapping("selectGivenPlaceOne")
    public Body selectGivenPlaceOne(String recid) {
        return this.muckService.selectGivenPlaceOne(recid);
    }

    /**
    * @ Description: 查询工地详情
    * @ Param: [recid]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/10/22 17:38
    */
    @RequestMapping("selectConstructionSiteOne")
    public Body selectConstructionSiteOne(String recid) {
        return this.muckService.selectConstructionSiteOne(recid);
    }

    /** 
    * @ Description: 查询准运证的审批流程
    * @ Param: [number]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/10/23 15:13
    */
    @RequestMapping("selectoperprogress")
    public Body selectoperprogress(String number) {
        return Body.newInstance(this.muckService.selectoperprogress(number));
    }

    /** 
    * @ Description: 查询系统公告
    * @ Param: []
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/10/30 9:50
    */
    @RequestMapping("selectNotice")
    public Body selectNotice() {
        return Body.newInstance(this.muckService.selectNotice());
    }

    /** 
    * @ Description: 查找公司详细信息
    * @ Param: []
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/10/30 15:46
    */
    @RequestMapping("selectM01")
    public Body selectM01() {
        return Body.newInstance(this.muckService.selectM01());
    }
}

