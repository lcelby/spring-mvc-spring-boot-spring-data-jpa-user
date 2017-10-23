package com.liucheng.springbootjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucheng.springbootjpa.bean.User;
import com.liucheng.springbootjpa.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> queryUserAll() {		
		return userDao.findAll();
	}

	@Override
	public User queryUser(Integer id) {		
		return userDao.findUserById(id);
	}

	@Override
	public void add(User u) {
		userDao.save(u);
		
	}

	@Override
	public int updateUserByName(String password, String username) {		
		return userDao.updateUserByName(password, username);
	}

	@Override
	public void deleteUserById(String id) {
		userDao.delete(Integer.valueOf(id));
		
	}

}
