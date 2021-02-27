package com.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Model.Topic;
import com.Service.TopicService;

@RestController
public class TopicController {
	@Autowired
	TopicService topicService;

	@PostMapping("/topic")
	public ResponseEntity<?> addTopic(@RequestParam String topic_name){		
		try {
			Topic t = new Topic();
			t.setTopic_name(topic_name);
			long topic_id = topicService.addTopic(t);
			return new ResponseEntity<>("Topic Created with Id " + topic_id, HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("Topic Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/topic")
	public ResponseEntity<?> updateTopic(@RequestParam String topic_name,@RequestParam("topic_id") long topic_id){		
		try {
			Topic t = new Topic();
			t.setTopic_name(topic_name);
			t.setTopic_id(topic_id);
			Optional<Topic> topic_found = topicService.getTopicById(topic_id);
			if (topic_found.isPresent()) {
				topicService.updateTopic(t);
				return new ResponseEntity<>("Topic Updated", HttpStatus.ACCEPTED);		
			}else {
				return new ResponseEntity<>("Topic Not Updated Due to unavailable topic_id "+ topic_id , HttpStatus.BAD_REQUEST);
			}
				
		}catch (Exception e) {
			return new ResponseEntity<>("Topic Not Updated ", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/topic")
	public ResponseEntity<?> getTopic(@RequestParam("topic_id") long topic_id){		
		try {
			Optional<Topic> topic = topicService.getTopicById(topic_id);
			if(topic.isPresent()) {
				return new ResponseEntity<>(topic.get(), HttpStatus.ACCEPTED);	
			}else {
				return new ResponseEntity<>("Topic Not Found Due to unavailable topic_id " + topic_id , HttpStatus.BAD_REQUEST);
			}
					
		}catch (Exception e) {
			return new ResponseEntity<>("Topic Not Found", HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/topic")
	public ResponseEntity<?> deleteTopic(@RequestParam("topic_id") long topic_id){		
		try {
			 topicService.deleteById(topic_id);
			 return new ResponseEntity<>("Topic Deleted", HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("Topic Not Deleted due to unavailable topic_id"+ topic_id , HttpStatus.BAD_REQUEST);
		}
	}
	
}
