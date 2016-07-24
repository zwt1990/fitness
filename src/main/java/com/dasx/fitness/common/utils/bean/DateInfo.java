package com.dasx.fitness.common.utils.bean;


public class DateInfo {

	
	private String dateStr;
	
	private Integer shortDateStr;//取最后一位日期

	private int weekIndex;
	
	private String weekChinese;//中文的一、二


	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public int getWeekIndex() {
		return weekIndex;
	}

	public void setWeekIndex(int weekIndex) {
		this.weekIndex = weekIndex;
	}

	public String getWeekChinese() {
		return weekChinese;
	}

	public void setWeekChinese(String weekChinese) {
		this.weekChinese = weekChinese;
	}

	public Integer getShortDateStr() {
		return shortDateStr;
	}

	public void setShortDateStr(Integer shortDateStr) {
		this.shortDateStr = shortDateStr;
	}
}
