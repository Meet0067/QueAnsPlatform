package com.Service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.Topic;
import com.Repository.TopicRepository;
@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	public long addTopic(@Valid Topic topic) {
		return topicRepository.save(topic).getTopic_id();
		// TODO Auto-generated method stub
		
	}

	public void updateTopic(@Valid Topic topic) {
		// TODO Auto-generated method stub
		
		topicRepository.save(topic);
	}

	public Optional<Topic> getTopicById(long topic_id) {
		// TODO Auto-generated method stub
		return topicRepository.findById(topic_id);
	}

	public void deleteById(long topic_id) {
		// TODO Auto-generated method stub
		topicRepository.deleteById(topic_id);
	}

}
