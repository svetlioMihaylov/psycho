package com.task.tracker.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.questions.beans.ResponseBean;
import com.interview.questions.db.dao.TagDao;
import com.interview.questions.db.entities.TagModel;
import com.interview.questions.enums.NotificationType;
import com.interview.questions.misc.NotificationMessages;
import com.task.tracker.services.TagService;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	TagDao tagDao;

	@Override
	public TagModel findTagByName(String name) {

		return tagDao.findByName(name);
	}

	@Override
	public void saveTag(TagModel tagModel) {
		tagDao.save(tagModel);
	}

	@Override
	public List<String> getAllTags() {
		List<TagModel> allTags = tagDao.getAll();
		List<String> stringList = new ArrayList<String>();
		for(TagModel tag : allTags)
		{
			stringList.add(tag.getName());
		}
		
		return stringList;
	}

	@Override
	public List<String> getTagsForIds(List<ObjectId> list) {
		
				
	 List<TagModel> tagsForIdsList = tagDao.getTagsForIds(list);
	 List<String> asString = new ArrayList<String>(); 
	 for(TagModel rr : tagsForIdsList)
	 {
		 asString.add(rr.getName());
	 }
		
		return asString;
	}

	@Override
	public ResponseBean saveTag(String tagName) {

		TagModel tag = tagDao.getTagByName(tagName);
		if (tag != null) {
			return new ResponseBean(NotificationMessages.TAG_ALREADY_CREATED,
					NotificationType.WARNING, true);
		} else {
			tag = new TagModel();
			tag.setName(tagName);
			tagDao.save(tag);
			return new ResponseBean(
					NotificationMessages.TAG_CREATED_SUCCESSFULLY,
					NotificationType.INFO, true);
		}

	}
}
