package com.dasx.fitness.common.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.dasx.fitness.common.utils.bean.DateInfo;

public class DateUtils {
	/**定义常量**/
    public static final String DATE_JFP_STR="yyyyMM";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
	
    /**
     * 获取一周的日期信息
     * @return
     */
	public static List<DateInfo> getWeekDates(){
		List<DateInfo> dates=new ArrayList<DateInfo>();
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        for(int i=0;i<7;i++){
        	DateInfo dateInfo=new DateInfo();
        	cd.add(Calendar.DAY_OF_MONTH,i==0?0:1);
        	dateInfo.setShortDateStr(cd.get(Calendar.DAY_OF_MONTH));
        	dateInfo.setDateStr(unixTimestampToDate(cd.getTimeInMillis()) );
        	dateInfo.setWeekIndex(cd.get(Calendar.DAY_OF_WEEK));
        	dateInfo.setWeekChinese(getWeekChinese(dateInfo.getWeekIndex()));
        	dates.add(dateInfo);
        }
        return dates;
	}
	
	public static  String getWeekChinese(int dayOfweek){
		String chinesWeek="";
		switch (dayOfweek) {
		case 1:
			chinesWeek= "日";
			break;
		case 2:
			chinesWeek= "一";
			break;
		case 3:
			chinesWeek= "二";
			break;
		case 4:
			chinesWeek= "三";
			break;
		case 5:
			chinesWeek= "四";
			break;
		case 6:
			chinesWeek= "五";
			break;
		case 7:
			chinesWeek= "六";
			break;
		default:
			chinesWeek= "日";
			break;
		}
		return chinesWeek;
	}
	
	 /**
     * 将Unix时间戳转换成日期
     * @param long timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(Long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_SMALL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }
	public static void main(String[] args) {
		 List<DateInfo> week=getWeekDates();
		 System.out.println(week.size());
	}
}
