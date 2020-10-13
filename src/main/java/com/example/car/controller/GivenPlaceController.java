package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.GivenPlace;
import com.example.car.service.GivenPlaceService;
import com.example.car.service.IDeviceAlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备报警表 Controller
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:52:07
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class GivenPlaceController {

    private final GivenPlaceService givenPlaceService;

    /**
     * @Description: 新增消纳场所
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("insertGivenPlace")
    public Body insertGivenPlace(GivenPlace givenPlace) {
        return this.givenPlaceService.insertGivenPlace(givenPlace);
    }

    /**
     * @Description: 修改消纳场所
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("updateGivenPlace")
    public Body updateGivenPlace(GivenPlace givenPlace) {
        return givenPlaceService.updateGivenPlace(givenPlace);
    }

    /**
     * @Description: 删除消纳场所
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("delGivenPlace")
    public Body delGivenPlace(Integer id) {
        return givenPlaceService.delGivenPlace(id);
    }

    /**
     * @Description: 查询指定消纳场所
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("selectGivenPlaceOne")
    public Body selectOne(Integer id) {
        return givenPlaceService.selectOne(id);
    }

    /**
     * @Description: 查询所有消纳场所
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("selectGivenPlaceAll")
    public Body selectAll() {
        return givenPlaceService.selectAll();
    }
}
