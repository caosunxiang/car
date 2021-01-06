/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarCertificationServiceImpl
 * Author:   冷酷的苹果
 * Date:     2020/12/28 10:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarCertification;
import com.example.car.entity.OperationLog;
import com.example.car.mapper.sqlserver.CarCertificationMapper;
import com.example.car.mapper.sqlserver.OperationLogMapper;
import com.example.car.service.CarCertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/28
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class CarCertificationServiceImpl implements CarCertificationService {

    @Autowired
    private CarCertificationMapper carCertificationMapper;
    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public Body selectCarCertification(String carid) {
        List<CarCertification> list = carCertificationMapper.selectCarCertification(carid);
        return Body.newInstance(list);
    }


    @Override
    public Body updateCarCertification(String certificationStatus, String certificationTime,
                                       String carId, String userid) {
        List<CarCertification> list = carCertificationMapper.selectCarCertification(carId);
        if (list.size() > 0) {
            CarCertification carCertification = new CarCertification(null, certificationStatus, certificationTime,
                    null, null, carId, userid);
            carCertificationMapper.updateCarCertification(carCertification);
            OperationLog operationLog = new OperationLog(null, carId, "修改", "修改车辆审查状态",
                    DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN),userid,null,null );
            operationLogMapper.insertLog(operationLog);
            return Body.newInstance(carCertification);
        } else {
            CarCertification carCertification = new CarCertification(null, certificationStatus, certificationTime,
                    DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), userid, carId, null);
            carCertificationMapper.insertCarCertification(carCertification);
            OperationLog operationLog = new OperationLog(null, carId, "修改", "修改车辆审查状态",
                    DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN),userid,null,null );
            operationLogMapper.insertLog(operationLog);
            return Body.newInstance(carCertification);
        }
    }
}
