package com.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.Question;
import com.Repository.QuestionRepository;

@Service
public class QuestionService {

		@Autowired
		QuestionRepository questionRepository;

		public long addQuestion(@Valid Question question) {
			return questionRepository.save(question).getQuestion_id();
		}
		public long updateQuestionLikes(Question question) {
			return questionRepository.save(question).getQuestion_likes();		
		}

		public Optional<Question> getQuestionById(Long question_id) {
			// TODO Auto-generated method stub
			 return questionRepository.findById(question_id);
		}
		
		public List<Question> getQuestionByFilter(Long question_like,Long[] company_id,Long[] tag_id,Long[] subtopic_id,Date date) {
			// TODO Auto-generated method stub
			Set<Long> li = questionRepository.getQidForFilterQuestion(question_like,company_id,tag_id,subtopic_id,date);
			List<Question> questions = new ArrayList<Question>();
			for(Long l:li) {
				questions.add(getQuestionById(l).get());
			}
			return questions;
		}
}
