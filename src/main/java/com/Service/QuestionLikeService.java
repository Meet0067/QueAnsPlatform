package com.Service;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.Question;
import com.Model.QuestionLike;
import com.Repository.QuestionLikeRepository;
import com.Repository.QuestionRepository;


@Service
public class QuestionLikeService {

		@Autowired
		QuestionLikeRepository questionLikeRepository;

		public long addQuestionLike(@Valid QuestionLike questionLike) {
			return questionLikeRepository.save(questionLike).getQuestion_like_id();
		}
		
		public Optional<QuestionLike> getQuestionLikeByQID(long question_id,long id){
			return questionLikeRepository.checkUserLike(question_id,id);
		}

		
		
}
