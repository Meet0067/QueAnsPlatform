package com.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Model.Answer;
import com.Service.AnswerService;
import com.Service.CompanyService;
import com.Service.QuestionService;
import com.Service.TagService;
import com.Service.UserService;

@RestController
public class  AnswerController{
	@Autowired
	AnswerService answerService;
	@Autowired
	TagService tagService;
	@Autowired
	UserService userService;
	@Autowired
	QuestionService questionService;
	@Autowired
	CompanyService companyService;
	@PostMapping("/answers")
	public ResponseEntity<?> addAnswer(@RequestParam String answer_,@RequestParam(name = "question_id") Long question_id,@RequestParam("user_id") Long id ){		
		
		try {
			Answer answer = new Answer();
			answer.setAnswer_(answer_);
			if(questionService.getQuestionById(question_id).isPresent()) {
				answer.setQuestion(questionService.getQuestionById(question_id).get());
			}else {
				return new ResponseEntity<>("answer Not Created Due to Unavailable QuestionId "+ question_id, HttpStatus.ACCEPTED);
			}
			if(userService.findUserById(id).isPresent()) {
				answer.setUser(userService.findUserById(id).get());
			}else {
				return new ResponseEntity<>("answer Not Created Due to Unavailable UserId "+ id, HttpStatus.ACCEPTED);
			}
			answer.setAnswer_likes(0);
			long id_que = answerService.addAnswer(answer);
		
			
			
			
			return new ResponseEntity<>("answer Created with Id "+ id_que, HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("answer Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}