package com.dasx.fitness.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@RequestMapping(value = "test/mvc")
	@ResponseBody
	public String queryList(HttpServletRequest request,
			HttpServletResponse response,String username,String password) throws IOException {
		System.out.println("spring MVC 配置成功");
		return "spring MVC 配置成功";
		
	}
}
