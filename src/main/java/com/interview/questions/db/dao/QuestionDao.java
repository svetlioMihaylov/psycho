package com.interview.questions.db.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interview.questions.db.entities.QuestionModel;

@Component
public class QuestionDao extends AbstractDao<QuestionModel>{
	
	@Autowired
	TagDao tagDao;
	
	
	public List<QuestionModel> getQuestionNumberByTag(List<String> tags)
	{
		
		List<ObjectId> tagIds = tagDao.getTagsByNames(tags);
		
		Query<QuestionModel> query =getDatastore().createQuery(QuestionModel.class);
		
		query.field("tags").in(tagIds);
		List<QuestionModel> asList = query.asList();
		
		System.out.println(asList);
		
		
		return asList;
		
	}
	
	public List<QuestionModel> findAll()
	{
		return finaAll(QuestionModel.class);
	}
	
	public QuestionModel findQuestionById(String  id)
	{
		
		Query<QuestionModel> query = getDatastore().createQuery(QuestionModel.class);
			query.field("_id").equal(new ObjectId(id));
		return query.get();
		
		
	}
	

}
