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
import org.springframework.util.StringUtils;
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
    public Body selectCarInfo(String carNo, Integer size, Integer index, String CompanyName,
                              String DriverName, String EngineNo,
                              String CarframeNo, String CarType) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(size)) {
            size = 10;
            index = 1;
        }
        return this.muckService.selectCarInfo(carNo, size, index, CompanyName, DriverName, EngineNo, CarframeNo,
                CarType);
    }

    /**
     * @ Description: 查询消纳场所
     * @ Param: []
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/15 9:17
     */
    @RequestMapping("selectGivenPlace")
    public Body selectGivenPlace(String name, Integer size, Integer index, String M0702, String M0707, String M0708,
                                 String M0703, String M0704, String M0709,
                                 String M0710) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(size)) {
            return Body.BODY_451;
        }
        return this.muckService.selectGivenPlace(name, size, index, M0702, M0707, M0708, M0703, M0704, M0709, M0710);
    }

    /**
     * @ Description: 查询工地信息
     * @ Param: []
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/15 9:18
     */
    @RequestMapping("selectConstructionSite")
    public Body selectConstructionSite(String name, Integer size, Integer index, String M0417,
                                       String M0419, String M0402,
                                       String M0403, String M0414,
                                       String M0420, String M0425,
                                       String M0426) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(size)) {
            return Body.BODY_451;
        }
        return this.muckService.selectConstructionSite(name, size, index, M0417, M0419, M0402, M0403, M0414, M0420,
                M0425, M0426);
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
        return this.muckService.selectoperprogress(number);
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
        return this.muckService.selectNotice();
    }

    /**
     * @ Description: 查找公司详细信息
     * @ Param: []
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/10/30 15:46
     */
    @RequestMapping("selectM01")
    public Body selectM01(String name) {
        return this.muckService.selectM01(name);
    }

    /*** 
     * @ Description: 每天准运证数量折线图
     * @ Param: [time]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/11/5 14:45
     */
    @RequestMapping("selectCountByMuck")
    public Body selectCountByMuck(String time) {
        return this.muckService.selectCountByMuck(time);
    }

    /**
     * @ Description: 分页高级查询准运证
     * @ Param: [name, size, index, CarNo, BeginTime, type]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/11/20 14:16
     */
    @RequestMapping("selectMuckAdvanced")
    public Body selectMuckAdvanced(String name, Integer size, Integer index, String BeginTime, String type,
                                   String time1, String time2, String car, String pname) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(size)) {
            return Body.BODY_451;
        }
        return this.muckService.selectMuckAdvanced(name, size, index, BeginTime, type, time1, time2, car, pname);
    }

    /**
     * @ Description: 准运证查询
     * @ Param: [name, size, index, BeginTime, type]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/15 14:26
     */
    @RequestMapping("selectMuckPage")
    public Body selectMuckPage(String name, Integer size, Integer index, String BeginTime, String type) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(size)) {
            return Body.BODY_451;
        }
        return this.muckService.selectMuckPage(name, size, index, BeginTime, type);
    }

    /**
     * @ Description: 验证用户是不是管理员
     * @ Param: [userid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/21 15:20
     */
    @RequestMapping("PowerControl")
    public Body PowerControl(String userid) {
        if (StringUtils.isEmpty(userid)) {
            return Body.BODY_451;
        }
        return this.muckService.PowerControl(userid);
    }


    /**
     * @ Description: 修改工地视频地址
     * @ Param: [VideoUrl, videoName, videoPassword, recId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/6 14:05
     */
    @RequestMapping("updateM04VideoUrl")
    public Body updateM04VideoUrl(String VideoUrl, String videoName, String videoPassword, String recId) {
        if (StringUtils.isEmpty(recId)) {
            return Body.BODY_451;
        }
        return this.muckService.updateM04VideoUrl(VideoUrl, videoName, videoPassword, recId);
    }

    /**
     * @ Description: 停用工地
     * @ Param: [valid, RecId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/7 16:42
     */
    @RequestMapping("validM04")
    public Body validM04(Integer valid, String RecId) {
        if (StringUtils.isEmpty(RecId) || StringUtils.isEmpty(valid)) {
            return Body.BODY_451;
        }
        return this.muckService.validM04(valid, RecId);
    }

    /**
     * @ Description: 停用消纳场所
     * @ Param: [valid, RecId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/7 17:29
     */
    @RequestMapping("validM07")
    public Body validM07(Integer valid, String RecId) {
        if (StringUtils.isEmpty(RecId) || StringUtils.isEmpty(valid)) {
            return Body.BODY_451;
        }
        return this.muckService.validM07(valid, RecId);
    }


    /**
     * @ Description: 判断用户身份
     * @ Param: [UserId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/11 8:54
     */
    @RequestMapping("selectUserRole")
    public Body selectUserRole(String UserId, String RoleId) {

        return this.muckService.selectUserRole(UserId, RoleId);
    }

    /**
     * @ Description: 工单数量
     * @ Param: [userid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/11 9:24
     */
    @RequestMapping("selectApply")
    public Body selectApply(String userid) {
        if (StringUtils.isEmpty(userid)) {
            return Body.BODY_451;
        }
        return this.muckService.selectApply(userid);
    }
}

