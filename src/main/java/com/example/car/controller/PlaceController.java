package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.Place;
import com.example.car.service.PlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
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
public class PlaceController {

    private final PlaceService PlaceService;

    /**
     * @Description: 新增停车场
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("insertPlace")
    public Body insertPlace(Place place) {
        return this.PlaceService.insertPlace(place);
    }

    /**
     * @Description: 修改停车场
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("updatePlace")
    public Body updatePlace(Place place) {
        if (StringUtils.isEmpty(place.getName()) || StringUtils.isEmpty(place.getId())) {
            return Body.BODY_451;
        }
        return this.PlaceService.updatePlace(place);
    }

    /**
     * @Description: 删除停车场
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("delPlace")
    public Body delPlace(Integer id) {
        return this.PlaceService.delPlace(id);
    }

    /**
     * @Description: 查询指定停车场
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("selectPlaceOne")
    public Body selectOne(Integer id) {
        return this.PlaceService.selectOne(id);
    }

    /**
     * @Description: 查询所有停车场
     * @Param: [givenPlace]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/10/13 10:40
     */
    @RequestMapping("selectPlaceAll")
    public Body selectAll(String name, Integer index, Integer size) {
        if (StringUtils.isEmpty(index) || StringUtils.isEmpty(size)) {
            return Body.BODY_451;
        }
        return this.PlaceService.selectAll(name, index, size);
    }
}
