package com.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.Answer;
import com.Model.Question;
import com.Repository.AnswerRepository;


@Service
public class AnswerService {

		@Autowired
		AnswerRepository answerRepository;

		public long addAnswer(@Valid Answer answer) {
			// TODO Auto-generated method stub
			
			
			return answerRepository.save(answer).getAnswer_id();
		}
		public Optional<Answer> getAnswerById(long id) {		
			return answerRepository.findById(id);
			
		}
		public long updateAnswerLikes(Answer answer) {
			// TODO Auto-generated method stub
			
			
				return answerRepository.save(answer).getAnswer_likes();
				
			
		}
		public List<Answer> getAnswerByQId(long question_id) {
			// TODO Auto-generated method stub
			return answerRepository.getAnswers(question_id);
		}
		public Optional<Answer> getMostLikesAnswerById(Long question_id) {
			// TODO Auto-generated method stub
			return answerRepository.getMostLikesAnswer(question_id);
		}
		
}
