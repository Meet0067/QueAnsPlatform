package com.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Model.SubTopic;
import com.Model.Topic;
import com.Service.SubTopicService;
import com.Service.TopicService;

@RestController
public class SubTopicController {
	
	@Autowired
	SubTopicService subTopicService;
	@Autowired
	TopicService topicService;

	@PostMapping("/subtopic")
	public ResponseEntity<?> addSubTopic(@RequestParam("topic_id") long topic_id,@RequestParam String subtopic_name){		
		
		try {			
			Optional<Topic> topic_found = topicService.getTopicById(topic_id);
			if (topic_found.isPresent()) {
				SubTopic subTopic = new SubTopic();
				subTopic.setSubtopic_name(subtopic_name);
				subTopic.setTopic(topic_found.get());
				long subtopic_id = subTopicService.addSubTopic(subTopic);
				return new ResponseEntity<>("SubTopic Created with Id" + subtopic_id, HttpStatus.ACCEPTED);		
			}else {
				return new ResponseEntity<>("SubTopic Not Created due to Unavailable Topic", HttpStatus.BAD_REQUEST);
			}			
		}catch (Exception e) {
			return new ResponseEntity<>("SubTopic Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/subtopic")
	public ResponseEntity<?> updateSubTopic(@RequestParam("topic_id") long topic_id,@RequestParam String subtopic_name,@RequestParam long subtopic_id){		
		
		try {
			Optional<Topic> topic_found = topicService.getTopicById(topic_id);
			Optional<SubTopic> subtopic_found = subTopicService.getSubTopicById(subtopic_id);
			if (subtopic_found.isPresent() & topic_found.isPresent()) {
				SubTopic subTopic = new SubTopic();
				subTopic.setSubtopic_name(subtopic_name);
				subTopic.setTopic(topic_found.get());
				subTopic.setSubtopic_id(subTopic.getSubtopic_id());
				subTopicService.updateSubTopic(subTopic);
				return new ResponseEntity<>("SubTopic Updated", HttpStatus.ACCEPTED);		
			}else {
				return new ResponseEntity<>("SubTopic Not Created due to Unavailable Topic or SubTopic", HttpStatus.BAD_REQUEST);
			}				
		}catch (Exception e) {
			return new ResponseEntity<>("SubTopic Not Updated", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/subtopic")
	public ResponseEntity<?> getSubTopic(@RequestParam long subtopic_id){		
		try {
			Optional<SubTopic> subtopic = subTopicService.getSubTopicById(subtopic_id);
			if(subtopic.isPresent()) {
				return new ResponseEntity<>(subtopic.get(), HttpStatus.ACCEPTED);		
			}else {
				throw new Exception();
			}				
		}catch (Exception e) {
			return new ResponseEntity<>("SubTopic Not Found", HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/subtopic")
	public ResponseEntity<?> deleteSubTopic(@RequestParam long subtopic_id){		
		try {
			subTopicService.deleteById(subtopic_id);
			 return new ResponseEntity<>("SubTopic Deleted", HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("SubTopic Not Deleted due to Unavailable subtopic_id", HttpStatus.BAD_REQUEST);
		}
	}
	
}


