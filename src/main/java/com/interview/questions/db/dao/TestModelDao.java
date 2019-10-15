package com.interview.questions.db.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.interview.questions.db.entities.TestModel;


@Component
public class TestModelDao extends AbstractDao<TestModel> {
	
	public TestModel findTestById(ObjectId objectId)
	{
		Query<TestModel> query =getDatastore().createQuery(TestModel.class).disableValidation();
		query.field("_id").equal(objectId);
		return query.get();
	}
	
	public List<TestModel> findTestByUserAndStatus(ObjectId objectId, boolean completedStatus)
	{
		Query<TestModel> query =getDatastore().createQuery(TestModel.class);
		query.field("reviers").equal(objectId);
		query.field("completed").equal(completedStatus);
		return query.asList();
	}
	


}
