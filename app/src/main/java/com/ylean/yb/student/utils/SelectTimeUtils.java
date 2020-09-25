package com.ylean.yb.student.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.ylean.yb.student.callback.TimeCallBack;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectTimeUtils {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 选择时间年月日时分秒
     * @param activity
     */
    public static void selectTimeAll(Activity activity, final TimeCallBack timeCallBack){
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);//该控件从1900年1月1日开始
        Calendar endDate = Calendar.getInstance();//控件截止时间
        endDate.set(2100, 2, 28);//该控件到2100年2月28日结束

        TimePickerView pvTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            public void onTimeSelect(Date date, View v) {//选中事件回调
                timeCallBack.getTime(getTimeAll(date));
            }
        })
            .setCancelText("取消")//取消按钮文字
            .setSubmitText("确定")//确认按钮文字
            .setContentTextSize(18)//滚轮文字大小
            .setTitleSize(17)//标题文字大小
            .setTitleText("")//标题文字
            .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
            .isCyclic(false)//是否循环滚动
            .setTitleColor(Color.BLACK)//标题文字颜色
            .setSubmitColor(Color.BLUE)//确定按钮文字颜色
            .setCancelColor(Color.BLUE)//取消按钮文字颜色
            .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
            .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
            .setRangDate(startDate,endDate)//默认是1900-2100年
            .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
            .setRangDate(startDate,endDate)//起始终止年月日设定
            .setType(new boolean[]{true, true, true, true, true, true})//分别对应年月日时分秒，默认全部显示
            .setLabel("年","月","日","时","分","秒")
//            .isDialog(true)//是否显示为对话框样式
            .build();
        pvTime.show();
    }


    /**
     * 选择时间年月日
     * @param activity
     */
    public static void selectTime(Activity activity, final TimeCallBack timeCallBack){
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);//该控件从1900年1月1日开始
        Calendar endDate = Calendar.getInstance();//控件截止时间
        endDate.set(2100, 2, 28);//该控件到2100年2月28日结束

        TimePickerView pvTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            public void onTimeSelect(Date date, View v) {//选中事件回调
                timeCallBack.getTime(getTime(date));
            }
        })
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(17)//标题文字大小
                .setTitleText("")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setRangDate(startDate,endDate)//默认是1900-2100年
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setType(new boolean[]{true, true, true, false, false, false})//分别对应年月日时分秒，默认全部显示
                .setLabel("年","月","日","时","分","秒")
//            .isDialog(true)//是否显示为对话框样式
                .build();
        pvTime.show();
    }


    /**
     * 判断时间大小
     * @param time1
     * @param time2
     * @return
     */
    public static boolean judgeTime(String time1,String time2){
        try {
            long long1=format2.parse(time1).getTime();
            long long2=format2.parse(time2).getTime();
            if(long1>long2){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }


    private static String getTimeAll(Date date) {//可根据需要自行截取数据显示
        return format.format(date);
    }

    private static String getTime(Date date) {//可根据需要自行截取数据显示
        return format2.format(date);
    }



}
