/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: ListUtils
 * Author:   冷酷的苹果
 * Date:     2020/7/21 13:51
 * Description: 操作集合
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils;

import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈操作集合〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/21
 * @since 1.0.0
 */
public class ListUtils {
    /**
     * 将一组数据平均分成n组
     *
     * @param source 要分组的数据源
     * @param n      平均分成n组
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }


    /**
     * 将一组数据固定分组，每组n个元素
     *
     * @param source 要分组的数据源
     * @param n      每组n个元素
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> fixedGrouping(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = new ArrayList<List<T>>();

        int sourceSize = source.size();
        int size = (source.size() / n) + 1;
        for (int i = 0; i < size; i++) {
            List<T> subset = new ArrayList<T>();
            for (int j = i * n; j < (i + 1) * n; j++) {
                if (j < sourceSize) {
                    subset.add(source.get(j));
                }
            }
            result.add(subset);
        }
        return result;
    }


    /**
     * 将一组数据固定分组，每组n个元素
     *
     * @param source 要分组的数据源
     * @param n      每组n个元素
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> fixedGrouping2(List<T> source, int n) {

        if (null == source || source.size() == 0 || n <= 0)
            return null;
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;
        int size = (source.size() / n);
        for (int i = 0; i < size; i++) {
            List<T> subset = null;
            subset = source.subList(i * n, (i + 1) * n);
            result.add(subset);
        }
        if (remainder > 0) {
            List<T> subset = null;
            subset = source.subList(size * n, size * n + remainder);
            result.add(subset);
        }
        return result;
    }


    /**
     * @Description: list转字符串
     * @Param: [list, separator]
     * @return: java.lang.String
     * @Author: 冷酷的苹果
     * @Date: 2020/7/31 13:13
     */
    public static String listToString5(List list, char separator) {
        return StringUtils.join(list.toArray(),separator);
    }


    public static void main(String[] args) {
        ClassLoader classLoader = org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
        URL res = classLoader.getResource("org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
        String path = res.getPath();
        System.out.println("POI Core came from : " + path);

        classLoader = org.apache.poi.POIXMLDocument.class.getClassLoader();
        res = classLoader.getResource("org/apache/poi/POIXMLDocument.class");
        path = res.getPath();
        System.out.println("POI OOXML came from : " + path);

		/*classLoader = org.apache.poi.hslf.HSLFSlideShow.class.getClassLoader();
		res = classLoader.getResource("org/apache/poi/hslf/HSLFSlideShow.class");
		path = res.getPath();
		System.out.println("POI Scratchpad came from : " + path);*/

    }


    /**
     * 获取list中存放的最后一个元素
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }
}
