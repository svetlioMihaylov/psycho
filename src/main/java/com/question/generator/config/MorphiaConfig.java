package com.question.generator.config;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


@Configuration
public class MorphiaConfig {
	
	@Value("${db.packageMapping}")
	private String packageMapping;
	
	@Value("${db.host}")
	private String host;
	
	@Value("${db.port}")
	private int port;
	
	@Value("${db.schema.name}")
	private String databaseName;
	
	@Value("${db.user}")
	private String user;
	
	@Value("${db.password}")
	private String password;
	
	
	@Bean
	public Datastore  getDataStore() throws UnknownHostException
	{
		final Morphia morphia = new Morphia();
    	morphia.mapPackage(packageMapping);
    	
    	MongoClient mongoClient = null;
    	MongoCredential mongoCredential = MongoCredential
    			.createScramSha1Credential(user, databaseName,
    					password.toCharArray());
    	mongoClient = new MongoClient(new ServerAddress(host, port));
//				,Arrays.asList(mongoCredential));
    	
    	final Datastore datastore = morphia.createDatastore(mongoClient,databaseName);
    	return datastore;
	}

}
