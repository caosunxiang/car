/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarCertificationController
 * Author:   冷酷的苹果
 * Date:     2020/12/28 10:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.CarCertificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/28
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarCertificationController {

    @Autowired
    private CarCertificationService carCertificationService;

    /**
     * @ Description: 查询车辆审查状态
     * @ Param: [carid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/28 10:24
     */
    @RequestMapping("selectCarCertification")
    public Body selectCarCertification(String carid) {
        return carCertificationService.selectCarCertification(carid);
    }

    /**
     * @ Description: 修改车辆审查状态
     * @ Param:
     * @ return:
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/28 10:24
     */
    @RequestMapping("updateCarCertification")
    public Body updateCarCertification(String certificationStatus, String certificationTime, String carid,
                                       String userid) {
        return carCertificationService.updateCarCertification(certificationStatus, certificationTime, carid, userid);
    }
}
