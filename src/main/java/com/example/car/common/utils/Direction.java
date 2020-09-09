/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: Direction
 * Author:   冷酷的苹果
 * Date:     2020/9/9 8:39
 * Description: 角度计算
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈角度计算〉
 *
 * @author 冷酷的苹果
 * @create 2020/9/9
 * @since 1.0.0
 */
public class Direction {
    public static boolean directionGap(double direction1,double direction2){
        if ((direction1>15&&direction1<345)||(direction2>15&&direction2<345)){
            double gap=direction1-direction2;
            return Math.abs(gap) > 15;
        }else {
            if ((direction1>180&&direction2<180)||(direction2>180&&direction1<180)){
               if (direction1>180){
                   direction1=360-direction1;
               }
                if (direction2>180){
                    direction2=360-direction2;
                }
                return direction1 + direction2 > 15;
            }else {
                double gap=direction1-direction2;
                return Math.abs(gap) > 15;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Direction.directionGap(180,120));
    }
}
