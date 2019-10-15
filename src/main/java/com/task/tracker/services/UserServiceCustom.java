package com.task.tracker.services;

import java.util.List;

import com.interview.questions.beans.ResponseBean;
import com.interview.questions.beans.UserBean;
import com.interview.questions.db.entities.UserModel;

public interface UserServiceCustom {

	
	public List<String> getAllUsersNames();
	
	public String getNameByEmail(String mail);
	
	public UserModel getUserByMail(String mail);
	
	public ResponseBean createUser(UserBean userBean);
}
