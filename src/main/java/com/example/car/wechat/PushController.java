/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: PushController
 * Author:   冷酷的苹果
 * Date:     2020/7/27 8:47
 * Description: 微信推送
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.wechat;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.ListUtils;
import com.example.car.mapper.mysql.DeviceAlarmMapper;
import com.example.car.mapper.mysql.DeviceAlarmSeverityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpUserServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信推送〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/27
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PushController {
    @Autowired
    private DeviceAlarmSeverityMapper deviceAlarmSeverityMapper;
    @Autowired
    private DeviceAlarmMapper deviceAlarmMapper;

    /*
     * 微信测试账号推送
     * */
    @GetMapping("/push")
    public void push() {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId("wx7513f9ef191ec705");
        wxStorage.setSecret("d33477fd7f24a92a7d7f56288c42048b");
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        Integer count = deviceAlarmSeverityMapper.selectAlarmNowCount();
        count+=deviceAlarmMapper.selectAlarmNowCount(2);
        //2.获取关注着列表
        WxMpUserService wxMpUserService=new WxMpUserServiceImpl(wxMpService);
        WxMpUserList wxMpUserList=null;
        try {
            wxMpUserList= wxMpUserService.userList(null);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        assert wxMpUserList != null;
        List<String>list=wxMpUserList.getOpenids();
        List<String> list1=deviceAlarmSeverityMapper.selectAlarmName();
        String carName=ListUtils.listToString5(list1, ',');
        for (String s : list) {
            //3,推送消息
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(s)//要推送的用户openid
                    .templateId("N4q58QS0L1fXXFpVvepluhlnNpqWkDGTmMyz5cB-Cjs")//模版id
                    .url("https://www.merbang.cn/MerSlag/report.html")//点击模版消息要访问的网址
                    .build();
            //4,如果是正式版发送模版消息，这里需要配置你的信息
            templateMessage.addData(new WxMpTemplateData("first", "报警通知", "#000000"));
            templateMessage.addData(new WxMpTemplateData("performance", "当前系统有"+count+"条报警信息。", "#000000"));
            templateMessage.addData(new WxMpTemplateData("time", DateUtil.getDateFormat(new Date(),
                    DateUtil.FULL_TIME_SPLIT_PATTERN), "#000000"));
            templateMessage.addData(new WxMpTemplateData("remark",carName+"等车辆有报警消息，点击查看详情。", "#000000"));
//                        templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
            try {
                wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            } catch (Exception e) {
                System.out.println("推送失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}