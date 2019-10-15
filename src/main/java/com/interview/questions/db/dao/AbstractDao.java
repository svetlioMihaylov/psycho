package com.interview.questions.db.dao;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.interview.questions.db.entities.UserModel;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.WriteResult;

public class AbstractDao<E> {
	
	@Autowired
	private Datastore datastore;
	
	public Key<E> save(E item)
	{
		return datastore.save(item);
	}
	
	public UpdateOperations<? extends Object> update(E item)
	{
		return datastore.createUpdateOperations(item.getClass());	
	}
	
	public WriteResult delete(E item)
	{
		return datastore.delete(item);
	}

	
	public Datastore getDatastore() {
		return datastore;
	}
	
	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}
	
	protected E findOne(Map< String,String> conditions, Class<E> clazz)
	{
		Query<E> query =getDatastore().createQuery(clazz);
		for(Entry<String, String> a : conditions.entrySet())
		{
			query.field(a.getKey()).equal(a.getValue());
		}
		return query.get();
	}
	
	protected E findOne(Entry< String,String> condition, Class<E> clazz)
	{
		Query<E> query =getDatastore().createQuery(clazz).disableValidation();
		query.field(condition.getKey()).equal(condition.getValue());
		return query.get();
	}
	
	protected List<E> finaAll(Class<E> clazz)
	{
		Query<E> query =getDatastore().createQuery(clazz);
		return query.asList();
	}

}
