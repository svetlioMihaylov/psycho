package com.interview.questions.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.interview.questions.beans.UserBean;
import com.interview.questions.db.entities.UserModel;
import com.interview.questions.enums.UserRole;

@Component
public class UserModelPopulator implements Populator<UserBean, UserModel>{
	
	@Autowired
	PasswordEncoder passwornEncoder;

	@Override
	public UserModel populate(UserBean bean) {
		UserModel userModel = new UserModel();
		userModel.setFirstName(bean.getFirstName());
		userModel.setLastName(bean.getLastName());
		userModel.setEmail(bean.getMail());
		userModel.setRole(UserRole.valueOf(bean.getRole()));
		
		userModel.setPassword(passwornEncoder.encode(bean.getPassword()));
		return userModel;
	}

}
