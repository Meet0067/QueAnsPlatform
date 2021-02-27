package com.Service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.AnswerLike;
import com.Model.QuestionLike;
import com.Repository.AnswerLikeRepository;


@Service
public class AnswerLikeService {

		@Autowired
		AnswerLikeRepository answerLikeRepository;

		public long addAnswerLike(@Valid AnswerLike answerLike) {
			return answerLikeRepository.save(answerLike).getAnswer_like_id();
		}
		public Optional<AnswerLike> getAnswerLikeByQID(long answer_id,long id){
			return answerLikeRepository.checkUserLike(answer_id,id);
		}

		
		
}
