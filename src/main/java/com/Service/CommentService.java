package com.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.Comment;
import com.Repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;
	public long addComment(@Valid Comment comment) {
		
		 return commentRepository.save(comment).getComment_id();
	}
	public List<Comment> getCommentsByAID(Long answer_id) {
		// TODO Auto-generated method stub
		return commentRepository.getcommentsByAID(answer_id);
	}

}
