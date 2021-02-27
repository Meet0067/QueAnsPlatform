package com.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Model.Question;
import com.Model.QuestionLike;
import com.Service.QuestionLikeService;
import com.Service.QuestionService;
import com.Service.TagService;
import com.Service.UserService;

@RestController
public class  QuestionLikeController{
	
	@Autowired
	QuestionLikeService questionLikeService;
	@Autowired
	TagService tagService;
	@Autowired
	UserService userService;	
	@Autowired
	QuestionService questionService;
	@PostMapping("/questions_like")
	public ResponseEntity<?> addQuestionLikes(@Valid QuestionLike questionLike){	
		
		try {
			if(!questionLikeService.getQuestionLikeByQID(questionLike.getQuestion_id(),questionLike.getId()).isPresent()) {
				if(!userService.findUserById(questionLike.getId()).isPresent()) {
					return new ResponseEntity<>("QuestionLike Not Created Due to Unavailable UserId "+ questionLike.getId(), HttpStatus.BAD_REQUEST);
				}
				
				if(questionService.getQuestionById(questionLike.getQuestion_id()).isPresent()) {					
					Question question = questionService.getQuestionById(questionLike.getQuestion_id()).get();
					question.setQuestion_likes(questionService.getQuestionById(questionLike.getQuestion_id()).get().getQuestion_likes() + 1);
					long total_likes = questionService.updateQuestionLikes(question);
					long id_que = questionLikeService.addQuestionLike(questionLike);
					return new ResponseEntity<>("Total Likes "+ total_likes, HttpStatus.ACCEPTED);						
				}else {
					return new ResponseEntity<>("QuestionLike Not Created Due to Unavailable QuestionId "+ questionLike.getQuestion_id(), HttpStatus.BAD_REQUEST);
				}			
			}else {
				return new ResponseEntity<>("Question has Already Liked ", HttpStatus.BAD_REQUEST);
			}					
		}catch (Exception e) {
			return new ResponseEntity<>("QuestionLike Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}