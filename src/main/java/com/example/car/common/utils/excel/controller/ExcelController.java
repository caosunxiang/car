/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: ExcelController
 * Author:   冷酷的苹果
 * Date:     2020/12/7 11:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.excel.controller;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.ListUtils;
import com.example.car.common.utils.entity.EChatBean;
import com.example.car.common.utils.excel.ExportExcelUtil;
import com.example.car.common.utils.excel.entity.Online;
import com.example.car.entity.CarInfo;
import com.example.car.entity.DeviceAlarmSeverity;
import com.example.car.mapper.mysql.CarInfoMapper;
import com.example.car.mapper.mysql.CarStatusChangeRecordMapper;
import com.example.car.mapper.mysql.DeviceAlarmSeverityMapper;
import com.example.car.mapper.mysql.DeviceOnlineRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/7
 * @since 1.0.0
 */
@RestController
@CrossOrigin
public class ExcelController {

    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private DeviceOnlineRecordMapper deviceOnlineRecordMapper;
    @Autowired
    private CarStatusChangeRecordMapper carStatusChangeRecordMapper;
    @Autowired
    private DeviceAlarmSeverityMapper deviceAlarmSeverityMapper;

    @RequestMapping("ExcelOnline")
    public void ExcelOnline(String deptid, String status, String startTime, String endTime) throws FileNotFoundException {
        ExportExcelUtil<Online> exportExcelUtil = new ExportExcelUtil();
        List<Online> lists = this.carInfoMapper.selectCarExcel(deptid, status);
        for (Online online : lists) {
            long up = 0;
            long down = 0;
            long other = 0;
            String temp = startTime;
            List<EChatBean> list = new ArrayList<>();
            CarInfo carInfo = carInfoMapper.selectCarOnly(online.getCarnumber());
            List<EChatBean> deviceOnlineRecords = deviceOnlineRecordMapper.selectEChat(online.getCarnumber(),
                    startTime, endTime);
            for (EChatBean deviceOnlineRecord : deviceOnlineRecords) {
                if (deviceOnlineRecord.getStatus().equals(1)) {
                    deviceOnlineRecord.setType("上线");
                } else {
                    deviceOnlineRecord.setType("下线");
                }
            }
            List<EChatBean> carStatusChangeRecords = carStatusChangeRecordMapper.selectEChat(online.getCarnumber(),
                    startTime, endTime);
            if (carInfo.getCarServiceStatus() != 0 && carStatusChangeRecords.size() <= 0) {
                EChatBean eChatBean = new EChatBean();
                eChatBean.setType("报备");
                eChatBean.setEndTime(endTime);
                eChatBean.setCreateDate(startTime);
                list.add(eChatBean);
            } else {
                for (EChatBean carStatusChangeRecord : carStatusChangeRecords) {
                    if (carStatusChangeRecord.getStatus().equals(0)) {
                        carStatusChangeRecord.setType("正常");
                        if (online.getCarstatus() == 1 || online.getCarstatus() == 2) {
                            carStatusChangeRecord.setNowStatus("下线");
                        } else {
                            carStatusChangeRecord.setNowStatus("上线");
                        }
                    } else {
                        carStatusChangeRecord.setType("报备");
                    }
                }
                list.addAll(deviceOnlineRecords);
                list.addAll(carStatusChangeRecords);
                list.sort((o1, o2) -> Integer.compare(o1.getCreateDate().compareTo(o2.getCreateDate()), 0));
                if (list.size() <= 0) {
                    EChatBean eChatBean = new EChatBean();
                    if (carInfo.getCarServiceStatus() == 0) {
                        if (!StringUtils.isEmpty(online)) {
                            if (online.getCarstatus() == 1 || online.getCarstatus() == 2) {
                                eChatBean.setNowStatus("下线");
                            } else {
                                eChatBean.setNowStatus("上线");
                            }
                            list.add(eChatBean);
                        }
                    } else {
                        eChatBean.setType("报备");
                        list.add(eChatBean);
                    }
                }
            }
            System.out.println(list.toString());
            for (EChatBean eChatBean : list) {
                if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("下线")) {
                    if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                        up += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                        temp = endTime;
                    } else {
                        up += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                        temp = eChatBean.getCreateDate();
                    }
                } else if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("上线")) {
                    if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                        down += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                        temp = endTime;
                    } else {
                        down += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                "h");
                        temp = eChatBean.getCreateDate();
                    }
                } else if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("报备")) {
                    if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                        other += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                        temp = endTime;
                    } else {
                        other += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                "h");
                        temp = eChatBean.getCreateDate();
                    }
                } else {
                    if (eChatBean.getNowStatus().equals("下线")) {
                        if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                            down += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            temp = endTime;
                        } else {
                            down += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN,
                                    "h");
                            temp = eChatBean.getCreateDate();
                        }
                    } else {
                        if (StringUtils.isEmpty(eChatBean.getCreateDate())) {
                            up += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                            temp = endTime;
                        } else {
                            up += DateUtil.datetime(temp, eChatBean.getCreateDate(), DateUtil.FULL_TIME_SPLIT_PATTERN
                                    , "h");
                            temp = eChatBean.getCreateDate();
                        }
                    }
                }
            }
            EChatBean eChatBean = ListUtils.getLastElement(list);
            if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("下线")) {
                down += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
            } else if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("上线")) {
                up += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
            } else if (!StringUtils.isEmpty(eChatBean.getType()) && eChatBean.getType().equals("报备")) {
                other += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
            } else {
                if (eChatBean.getNowStatus().equals("下线")) {
                    down += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                } else {
                    up += DateUtil.datetime(temp, endTime, DateUtil.FULL_TIME_SPLIT_PATTERN, "h");
                }
            }
            online.setDown(down);
            online.setOther(other);
            online.setUp(up);
        }
        String[] columnNames = {"车牌号", "机构名称", "设备id", "车辆状态", "在线情况", "上线时间", "下线时间", "报备时间"};
        exportExcelUtil.exportExcel("在线时间", columnNames, lists, new FileOutputStream("D:/Online/"+deptid+".xls"),
                ExportExcelUtil.EXCEL_FILE_2003);
    }

    @RequestMapping("ExcelAlarm")
    public void ExcelAlarm(String deptid,String startTime, String endTime) throws FileNotFoundException {
        List<DeviceAlarmSeverity> deviceAlarmSeverities=deviceAlarmSeverityMapper.selectAlarmExcel(startTime,endTime,deptid);
        ExportExcelUtil<DeviceAlarmSeverity> exportExcelUtil = new ExportExcelUtil();
        String[] columnNames = {"严重报警id", "报警名称", "报警车牌号", "报警开始时间", "报警开始速度", "报警结束时间", "报警结束速度", "报警纬度", "报警经度",
                "行驶里程", "报警结束纬度", "报警结束经度", "机构id", "处理人", "处理状态", "处理时间","机构名称"};
        exportExcelUtil.exportExcel("在线时间", columnNames, deviceAlarmSeverities, new FileOutputStream("D:/alarm/"+deptid+".xls"),
                ExportExcelUtil.EXCEL_FILE_2003);
    }
}
