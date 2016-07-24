package com.dasx.fitness.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasx.fitness.entity.Course;
import com.dasx.fitness.service.WeekCourseService;

@Controller
public class WeekCourseController {
	@Autowired
	private WeekCourseService	weekCourseService;
	
	@RequestMapping(value = "weekCourse/getHeaderInfo")
	@ResponseBody
	public JSONObject getHeaderInfo(HttpServletRequest request,
			HttpServletResponse response,Integer cityId) throws IOException {
		return weekCourseService.getHeaderInfo();
		
	}
	
	
	@RequestMapping(value = "weekCourse/getCourses")
	@ResponseBody
	public JSONArray getCourses(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		 List<Course> courses=weekCourseService.queryCourses();
		return JSONArray.fromObject(courses);
		
	}
}
