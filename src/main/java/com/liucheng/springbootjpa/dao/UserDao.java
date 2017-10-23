package com.liucheng.springbootjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.liucheng.springbootjpa.bean.User;

public interface UserDao extends JpaRepository<User,Integer>{
	
	@Query("select u from User u where u.id= :id")
	public User findUserById(@Param("id") Integer id);
	
	@Modifying
	@Query("update User u set u.password = :password where u.username= :username")
	public int updateUserByName(@Param("password")String password, @Param("username")String username);
}
