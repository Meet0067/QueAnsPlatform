package com.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Model.Answer;
import com.Model.AnswerLike;
import com.Service.AnswerLikeService;
import com.Service.AnswerService;
import com.Service.UserService;

@RestController
public class  AnswerLikeController{
	@Autowired
	AnswerLikeService answerLikeService;
	
	@Autowired
	UserService userService;

	@Autowired
	AnswerService answerService;
	@PostMapping("/answers_like")
	public ResponseEntity<?> addAnswerLike(@Valid AnswerLike answerLike){	
		
		try {
			if(!answerLikeService.getAnswerLikeByQID(answerLike.getAnswer_id(),answerLike.getId()).isPresent()) {
				if(!userService.findUserById(answerLike.getId()).isPresent()) {
					return new ResponseEntity<>("AnswerLike Not Created Due to Unavailable UserId "+ answerLike.getId(), HttpStatus.BAD_REQUEST);
				}
				
				if(answerService.getAnswerById(answerLike.getAnswer_id()).isPresent()) {
					
					Answer answer = answerService.getAnswerById(answerLike.getAnswer_id()).get();
					answer.setAnswer_likes(answerService.getAnswerById(answerLike.getAnswer_id()).get().getAnswer_likes() + 1);
					long total_likes = answerService.updateAnswerLikes(answer);
					long id_que = answerLikeService.addAnswerLike(answerLike);
					return new ResponseEntity<>("Total Likes "+ total_likes, HttpStatus.ACCEPTED);	
					
				}else {
					return new ResponseEntity<>("AnswerLike Not Created Due to Unavailable AnswerId "+ answerLike.getAnswer_id(), HttpStatus.BAD_REQUEST);
				}			
			}else {
				return new ResponseEntity<>("Answer has Already Liked ", HttpStatus.BAD_REQUEST);
			}

					
		}catch (Exception e) {
			return new ResponseEntity<>("AnswerLike Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	
}