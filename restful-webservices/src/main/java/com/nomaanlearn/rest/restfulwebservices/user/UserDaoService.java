package com.nomaanlearn.rest.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;


@Component
public class UserDaoService {
	
	private static List<User> userList=new ArrayList<>();
	
	static {
		userList.add(new User(1,"Adam",LocalDate.now().minusYears(25)));
		userList.add(new User(2,"Eve",LocalDate.now().minusYears(24)));
		userList.add(new User(3,"John",LocalDate.now().minusYears(20)));
	}
	private int userCount=3;
	
	public List<User> getAllUser(){
		return userList;
	}
	
	public User getUserById(int id) {
		//Create a filter that finds by id
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		//Use the filter to get the object
		return userList.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteUserById(int id) {
		//Create a filter that finds by id
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		//Use the filter to get the object
		userList.removeIf(predicate);
	}
	
	public User createUser(User user) {
		user.setId(++userCount);
		userList.add(user);
		return user;
	}
}
