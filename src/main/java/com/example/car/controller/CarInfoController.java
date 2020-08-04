package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.ICarInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 车辆信息表 Controller
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 08:54:02
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarInfoController {

    private final ICarInfoService carInfoService;

    /**
     * @Description: 查询车辆信息
     * @Param: [carnumber, terminalId, sim]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/18 15:11
     */
    @RequestMapping("selectCar")
    public Body selectCar(String carnumber, String terminalId, String sim) {
        return this.carInfoService.selectCar(carnumber, terminalId, sim);
    }

    /**
     * @Description: 公司名称模糊查询车辆信息
     * @Param: [deptname]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/18 15:44
     */
    @RequestMapping("selectCarByDeptName")
    public Body selectCarByDeptName(String deptname) {
        return this.carInfoService.selectCarByDeptName(deptname);
    }

    /**
     * @Description: 根据查询机构的(名称 ( 必填), 车辆的状态[在线, 离线, 全部](必填), 搜索的车牌号/设备号(选填))查找车辆(返回:车牌号码；设备ID)
     * @Param: [deptid, carnumber, terminalid, status]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/6/18 16:39
     */
    @RequestMapping("selectCarDetail")
    public Body selectCarDetail(String deptid, String carnumber, String terminalid, String status) {
        return this.carInfoService.selectCarDetail(deptid, carnumber, terminalid, status);
    }

    /** 
    * @Description: 根据查询机构的(名称 ( 必填), 车辆的状态[在线, 离线, 全部](必填), 搜索的车牌号/设备号(选填))查找车辆(返回:车牌号码；设备ID) 分页
    * @Param: [deptid, carnumber, terminalid, status, index, size]
    * @return: com.example.car.common.utils.json.Body
    * @Author: 冷酷的苹果
    * @Date: 2020/8/3 9:18
    */
    @RequestMapping("selectCarDetailPage")
    public Body selectCarDetailPage(String deptid, String carnumber, String terminalid, String status, Integer index,
                                    Integer size) {
        if (StringUtils.isEmpty(index)||StringUtils.isEmpty(size)){
            return Body.BODY_451;
        }
        return this.carInfoService.selectCarDetailPage(deptid, carnumber, terminalid, status, index, size);
    }
}
