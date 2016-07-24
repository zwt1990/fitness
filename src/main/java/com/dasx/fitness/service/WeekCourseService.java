package com.dasx.fitness.service;

import java.util.List;

import com.dasx.fitness.entity.Course;

import net.sf.json.JSONObject;

public interface WeekCourseService {

	public JSONObject getHeaderInfo();
	
	public List<Course> queryCourses();
}
