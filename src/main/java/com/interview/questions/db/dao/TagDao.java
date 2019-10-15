package com.interview.questions.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.interview.questions.db.entities.TagModel;
import com.interview.questions.populator.EntryImpl;

@Component
public class TagDao  extends AbstractDao<TagModel> {
	
	public TagModel findByName(String name)
	{
		return findOne(new EntryImpl<String, String>("name", name), TagModel.class);
	}
	
	public List<TagModel> getAll()
	{
		return finaAll(TagModel.class);
	}
	
	public List<ObjectId> getTagsByNames(List<String> tagNames)
	{
	Query<TagModel> query =getDatastore().createQuery(TagModel.class);
//	List<String> asd = new ArrayList<String>();
//	asd.add("Applications Engineer");
//	asd.add("Computer and Information Systems Manager");
	
//	query.filter("name : $in", asd);
	query.field("name").in(tagNames);
	List<TagModel> asList = query.asList();
	List<ObjectId> ids = new ArrayList<ObjectId>();
	for(TagModel tagModel : asList)
	{
		ids.add(tagModel.get_id());
	}
	
//	System.out.println(asList);
	
	return ids;
	
	}
	
	public List<TagModel> getTagsForIds(List<ObjectId> list)
	{
		Query<TagModel> query =getDatastore().createQuery(TagModel.class);	
		query.field("_id").in(list);
		List<TagModel> asList = query.asList();
		
		
		
		
		return asList;
	}
	
	public TagModel getTagByName(String name) {
		return findOne(new EntryImpl<String, String>("name", name), TagModel.class );
		
	}
	

}
