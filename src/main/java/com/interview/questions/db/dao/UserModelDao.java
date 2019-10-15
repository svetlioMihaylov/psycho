package com.interview.questions.db.dao;

import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.interview.questions.db.entities.UserModel;

@Component
public class UserModelDao extends AbstractDao<UserModel>{
	
	
	public UserModel findByEmail(String mail)
	{
		Query<UserModel> query =getDatastore().createQuery(UserModel.class);
		return query.field("email").equal(mail).get();
	}
	
	public List<UserModel> findAll()
	{
		return super.finaAll(UserModel.class);
	}


}
