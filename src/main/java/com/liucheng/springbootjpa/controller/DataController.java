package com.liucheng.springbootjpa.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liucheng.springbootjpa.bean.User;
import com.liucheng.springbootjpa.service.UserService;

@Controller
public class DataController {
	
	@Autowired
	private UserService userService;
	
	//查询所有用户
	@RequestMapping("/queryUserAll")
	public String queryUserAll(Model m) {
		List<User> userList = userService.queryUserAll();
		for(User user:userList) {
			System.out.println(user.getId()+user.getUsername()+user.getAge());
		}
		m.addAttribute(userList);
		return "userList";
	}
	
	
	//查询单个用户
	@RequestMapping("/querySingleUser")
	public String querySingleUser(Model m, String id) {
		Integer idNum = null;
		if(id!=null) {
			idNum = Integer.valueOf(id);
			User user = userService.queryUser(idNum);
			System.out.println(user.getId()+user.getUsername()+user.getAge());			
			m.addAttribute(user);
			return "querySingleUser";
		}
		return "queryUser";
	}
	
	@RequestMapping("/queryUser")
	public String queryUser() {		
		return "queryUser";
	}
	
	//用户注册（增加数据）
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)	
	public String registerUser(Model m, @RequestParam("username")String name, String password1,String password2, String age) {
		if(password1.equals(password2)) {
			User user = new User(name, password1, Integer.valueOf(age));
			System.out.println(user.getUsername());
			userService.add(user);
			List<User> userList = userService.queryUserAll();
			m.addAttribute(userList);
			return "userList";
		}
		return "register";
	}
	
	
	// 更改密码（修改数据）
	@RequestMapping("/update")
	public String update() {
		return "update";
	}
	
	@Transactional
	@RequestMapping(value="/updatePassword", method = RequestMethod.POST)	
	public String UpdateUser(Model m, @RequestParam("username")String name, String password1,String password2, String age) {
		if(password1.equals(password2)) {
			userService.updateUserByName(password1, name);
			System.out.println(name);			
			List<User> userList = userService.queryUserAll();
			m.addAttribute(userList);
			return "userList";
		}
		return "update";
	}
	
	@RequestMapping("delete")
	public String delete() {
		return "delete";
	}
	
	@Transactional
	@RequestMapping("/deleteUserById")
	public ModelAndView deleteUserById(@RequestParam("id") String id) {
		userService.deleteUserById(id);
		List<User> userList = userService.queryUserAll();
		for(User user:userList) {
			System.out.println(user.getId()+user.getUsername()+user.getAge());
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userList");
		mv.addObject("userList",userList);
		return mv;
	}

}
