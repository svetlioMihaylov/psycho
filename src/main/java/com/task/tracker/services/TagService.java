package com.task.tracker.services;

import java.util.List;

import org.bson.types.ObjectId;

import com.interview.questions.beans.ResponseBean;
import com.interview.questions.db.entities.TagModel;

public interface TagService {
	
	public TagModel findTagByName(String name);
	
	public void saveTag(TagModel tagModel);
	
	public List<String> getAllTags();
	
	public List<String> getTagsForIds(List<ObjectId> list);
	
	public ResponseBean saveTag(String tagName);
	
}
