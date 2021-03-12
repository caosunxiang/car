package com.example.car.mapper.sqlserver;


import com.example.car.common.utils.entity.EChatBean7;
import com.example.car.common.utils.excel.entity.MuckExcel;
import com.example.car.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MuckMapper {
    List<Map<String, String>> selectMuck(@Param("carNo") String carNo, @Param("permitNo") String permitNo, @Param(
            "time") String time);

    List<Map<String, String>> selectMuckByName(@Param("projectName") String projectName, @Param("time") String time,
                                               @Param("name") String name,
                                               @Param("endTime") String endTime, @Param("page") Integer page, @Param(
            "size") Integer size);

    List<Map<String, String>> selectMuckByProject(@Param("projectId") String projectId, @Param("time") String time);

    Integer selectMuckCount(@Param("projectName") String projectName, @Param("time") String time,
                            @Param("name") String name,
                            @Param("endTime") String endTime);

    List<Map<String, String>> selectCarInfo(@Param("carNo") String carNo, @Param("size") Integer size,
                                            @Param("total") Integer total, @Param("CompanyName") String CompanyName,
                                            @Param("DriverName") String DriverName, @Param("EngineNo") String EngineNo,
                                            @Param("CarframeNo") String CarframeNo, @Param("CarType") String CarType);

    List<M07> selectGivenPlace(@Param("name") String name, @Param("size") Integer size, @Param("total") Integer total,
                               @Param("M0702") String M0702, @Param("M0707") String M0707, @Param("M0708") String M0708,
                               @Param("M0703") String M0703, @Param("M0704") String M0704, @Param("M0709") String M0709,
                               @Param("M0710") String M0710);

    List<M04> selectConstructionSite(@Param("name") String name, @Param("size") Integer size,
                                     @Param("total") Integer total, @Param("M0417") String M0417,
                                     @Param("M0419") String M0419, @Param("M0402") String M0402,
                                     @Param("M0403") String M0403, @Param("M0414") String M0414,
                                     @Param("M0420") String M0420, @Param("M0425") String M0425,
                                     @Param("M0426") String M0426);

    Integer selectCarInfoCount(@Param("carNo") String carNo, @Param("CompanyName") String CompanyName,
                               @Param("DriverName") String DriverName, @Param("EngineNo") String EngineNo,
                               @Param("CarframeNo") String CarframeNo, @Param("CarType") String CarType);

    Integer selectGivenPlaceCount(@Param("name") String name, @Param("M0702") String M0702,
                                  @Param("M0707") String M0707, @Param("M0708") String M0708,
                                  @Param("M0703") String M0703, @Param("M0704") String M0704,
                                  @Param("M0709") String M0709,
                                  @Param("M0710") String M0710);

    Integer selectConstructionSiteCount(@Param("name") String name, @Param("M0417") String M0417,
                                        @Param("M0419") String M0419, @Param("M0402") String M0402,
                                        @Param("M0403") String M0403, @Param("M0414") String M0414,
                                        @Param("M0420") String M0420, @Param("M0425") String M0425,
                                        @Param("M0426") String M0426);


    M07 selectGivenPlaceOne(@Param("recid") String recid);

    M04 selectConstructionSiteOne(@Param("recid") String recid);

    void updateGivenPlace(M07 m07);

    void updateConstructionSite(M04 m04);

    List<Map<String, Object>> selectCarInfoByCompany(@Param("companyId") String companyId);

    List<Operprogress> selectoperprogress(@Param("number") String number);

    List<Notice> selectNotice();

    List<M01> selectM01(@Param("name") String name);

    List<EChatBean7> selectCountByMuck(@Param("time") String time);

    List<Map<String, Object>> selectMuckadvanced(@Param("name") String name, @Param("size") Integer size,
                                                 @Param("total") Integer total,@Param("pname")String pname,
                                                 @Param("BeginTime") String BeginTime, @Param("time1") String time1,
                                                 @Param("time2") String time2, @Param("type") String type);

    Integer selectMuckadvancedCount(@Param("name") String name,@Param("pname")String pname,
                                    @Param("BeginTime") String BeginTime, @Param("time1") String time1,
                                    @Param("time2") String time2, @Param("type") String type);

    List<String> selectCarByPermitNo(@Param("PermitNo") String PermitNo);

    Integer uploadImg(@Param("img") String img, @Param("carNo") String carNo);

    List<Map<String, Object>> selectMuckPage(@Param("name") String name, @Param("size") Integer size,
                                             @Param("total") Integer total,
                                             @Param("BeginTime") String BeginTime, @Param("type") String type);


    Integer selectMuckPageCount(@Param("name") String name,
                                @Param("BeginTime") String BeginTime, @Param("type") String type);

    List<Map<String, Object>> PowerControl(@Param("userid") String userid);

    void updateM04VideoUrl(M04 m04);

    void validM04(@Param("valid") Integer valid, @Param("RecId") String RecId);

    void validM07(@Param("valid") Integer valid, @Param("RecId") String RecId);

    List<Map<String, Object>> selectUserRole(@Param("UserId") String UserId, @Param("RoleId") String RoleId);

    Integer selectNewApply(@Param("userid") String userid);

    Integer selectPendingApply(@Param("userid") String userid);

    Integer selectIngApply(@Param("userid") String userid);

    Integer selectOverApply(@Param("userid") String userid);

    Integer insertUser(@Param("UserId") Integer UserId, @Param("UserName") String UserName,
                       @Param("UserPwd") String UserPwd,
                       @Param("name") String name);

    Integer selectUserId();

    void insertUserRole(@Param("userid") Integer UserId);

    Integer selectUserName(@Param("UserName") String UserName);

    List<Map<String, Object>> selectMuckadvanced1(@Param("name") String name, @Param("size") Integer size,
                                                  @Param("total") Integer total,
                                                  @Param("BeginTime") String BeginTime, @Param("type") String type,
                                                  @Param("time1") String time1,@Param("pname")String pname,
                                                  @Param("time2") String time2, @Param("car") String car);

    Integer selectMuckadvancedCount1(@Param("name") String name,
                                     @Param("BeginTime") String BeginTime, @Param("type") String type,
                                     @Param("time1") String time1,@Param("pname")String pname,
                                     @Param("time2") String time2, @Param("car") String car);

    List<MuckExcel> selectMuckExcel(@Param("name") String name, @Param("size") Integer size,
                                    @Param("total") Integer total,@Param("pname")String pname,
                                    @Param("BeginTime") String BeginTime, @Param("time1") String time1,
                                    @Param("time2") String time2, @Param("type") String type);

    List<MuckExcel> selectMuckExcel1(@Param("name") String name, @Param("size") Integer size,
                                     @Param("total") Integer total,@Param("pname")String pname,
                                     @Param("BeginTime") String BeginTime, @Param("type") String type,
                                     @Param("time1") String time1,
                                     @Param("time2") String time2, @Param("car") String car);
}
