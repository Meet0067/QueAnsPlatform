package com.Controller;

import java.util.Date;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Model.Comment;
import com.Service.AnswerService;
import com.Service.CommentService;
import com.Service.UserService;


@RestController
public class  CommentController{
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AnswerService answerService;
	@PostMapping("/comments")
	public ResponseEntity<?> addComment(@RequestParam String comment_text,@RequestParam(name = "answer_id") Long answer_id,@RequestParam("user_id") Long id ){		
		
		try {
			Comment comment = new Comment();
			comment.setComment_(comment_text);
			if(answerService.getAnswerById(answer_id).isPresent()) {
				comment.setAnswer(answerService.getAnswerById(answer_id).get());
			}else {
				return new ResponseEntity<>("comment Not Created Due to Unavailable AnswerId "+ answer_id, HttpStatus.BAD_REQUEST);
			}
			if(userService.findUserById(id).isPresent()) {
				comment.setUser(userService.findUserById(id).get());
			}else {
				return new ResponseEntity<>("comment Not Created Due to Unavailable UserId "+ id, HttpStatus.BAD_REQUEST);
			}
			Date d = new Date();
			comment.setCommentDate(d);
			long id_que = commentService.addComment(comment);			
			return new ResponseEntity<>("comment Created with Id "+ id_que, HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("comment Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	
	
}