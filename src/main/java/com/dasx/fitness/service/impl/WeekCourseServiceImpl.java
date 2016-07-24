package com.dasx.fitness.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.dasx.fitness.common.utils.DateUtils;
import com.dasx.fitness.common.utils.bean.DateInfo;
import com.dasx.fitness.entity.Course;
import com.dasx.fitness.entity.Store;
import com.dasx.fitness.service.WeekCourseService;

@Service(value="weekCourseService")
public class WeekCourseServiceImpl implements   WeekCourseService{

	@Override
	public JSONObject getHeaderInfo() {
		JSONObject json=new JSONObject();
		List<DateInfo> weeks=DateUtils.getWeekDates();
		json.put("weeks", weeks);
		json.put("stores", initDevDate());
		return json;
	}

	private List<Store> initDevDate(){
		List<Store> stores=new ArrayList<Store>();
		Store store=new Store();
		store.setId(1);
		store.setAddress("拱墅区丰潭路380号（银泰写字楼A座10楼)");
		store.setIntroduction("wwwwww");
		store.setLat("30.305");
		store.setLng("120.113");
		store.setManager("ERIC");
		store.setName("城西银泰店");
		store.setPhone("18357194946");
		store.setPic("http://7xq7nd.com2.z0.glb.qiniucdn.com/o_1amsramcp19g377u1m8g1l30t4o9.png");
		stores.add(store);
		return stores;
	}

	@Override
	public List<Course> queryCourses() {
		List<Course> cousers=new ArrayList<Course>();
		Course course=new Course();
		course.setId(1);
		course.setCoacheName("Andy");
		course.setStartTime("2016-09-11");
		course.setEndTime("2016-09-21");
		course.setName("尊巴热舞");
		course.setImg("static/img/course1.jpg");
		Course course2=new Course();
		course2.setId(2);
		course2.setCoacheName("ERIC");
		course2.setStartTime("2016-04-11");
		course2.setEndTime("2016-09-21");
		course2.setName("有氧塑形");
		course2.setImg("static/img/course2.jpg");
		cousers.add(course);
		cousers.add(course2);
		return cousers;
	}
	
}
