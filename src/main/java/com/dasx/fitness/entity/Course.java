package com.dasx.fitness.entity;

public class Course {
	
	private Integer id;
	
	private String name;
	
	private String startTime;
	
	private String endTime;
	
	private String coacheName;

	private String img;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCoacheName() {
		return coacheName;
	}

	public void setCoacheName(String coacheName) {
		this.coacheName = coacheName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
