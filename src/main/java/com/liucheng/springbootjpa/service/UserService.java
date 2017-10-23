package com.liucheng.springbootjpa.service;

import java.util.List;

import com.liucheng.springbootjpa.bean.User;

public interface UserService {
	
	public List<User> queryUserAll();
	
	public User queryUser(Integer id);
	
	public void add(User u);
	
	public int updateUserByName(String password , String username);
	
	public void deleteUserById(String id);

}
