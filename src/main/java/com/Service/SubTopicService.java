package com.Service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.SubTopic;
import com.Repository.SubTopicRepository;


@Service
public class SubTopicService {

	@Autowired
	private SubTopicRepository subTopicRepository;
	
	public long addSubTopic(SubTopic subtopic) {
		return subTopicRepository.save(subtopic).getSubtopic_id();		
	}

	public void updateSubTopic(SubTopic subtopic) {
		subTopicRepository.save(subtopic);
	}

	public Optional<SubTopic> getSubTopicById(long topic_id) {
		return subTopicRepository.findById(topic_id);
	}

	public void deleteById(long topic_id) {
		subTopicRepository.deleteById(topic_id);
	}

	public Long[] getAllSubTopics() {
		return subTopicRepository.getAllSubTopics();
	}

}
