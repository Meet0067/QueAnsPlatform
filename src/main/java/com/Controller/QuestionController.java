package com.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.ResponseAnswerBean;
import com.Bean.ResponseCommentBean;
import com.Bean.ResponseQuestionBean;
import com.Model.Answer;
import com.Model.Comment;
import com.Model.Company;
import com.Model.Question;
import com.Model.SubTopic;
import com.Model.Tag;
import com.Service.AnswerService;
import com.Service.CommentService;
import com.Service.CompanyService;
import com.Service.QuestionService;
import com.Service.SubTopicService;
import com.Service.TagService;
import com.Service.UserService;

//@Validated
@RestController
public class  QuestionController{
	@Autowired
	QuestionService questionService;
	@Autowired
	TagService tagService;
	@Autowired
	UserService userService;
	@Autowired
	SubTopicService subTopicService;
	@Autowired
	CompanyService companyService;
	@Autowired
	AnswerService answerService;
	@Autowired
	CommentService commentService;
	@PostMapping("/questions")
	//@Size(max = 500,min = 50 , message = "Question length must be between (50,500) characters !!")
	public ResponseEntity<?> addQuestion(  @RequestParam String question_,@RequestParam(name = "company_id",required = false) Long[] company_id,@RequestParam(name = "subtopic_id",required = true) Long[] subtopic_id,@RequestParam("user_id") Long id,
			@RequestParam(name = "tags", required = false) Long[] tags ){
		
		if(question_.length() < 50 | question_.length() > 500) {
			return new ResponseEntity<>("Question Not Created Due to question length which should be in between (50,500)", HttpStatus.BAD_REQUEST);
		}
		try {
			Question question = new Question();
			question.setQuestion_(question_);
			if(company_id!=null) {
				List<Company> company = new ArrayList<Company>() ;
				for(Long s:company_id) {
					if(companyService.getCompanyById(s).isPresent()) {
						company.add(companyService.getCompanyById(s).get());
						
					}else {
						return new ResponseEntity<>("Company Not Added Due to Unavailable CompanyId "+ s, HttpStatus.BAD_REQUEST);
					}
				}
				question.setCompany(company);

			}else {
				List<Company> company = new ArrayList<Company>() ;
				company.add(companyService.getCompanyById(1).get());
				question.setCompany(company);
			}
			if(tags!=null) {
				List<Tag> tag = new ArrayList<Tag>() ;

				for(Long s:tags) {
					
					if(tagService.getTagById(s).isPresent()) {
						tag.add(tagService.getTagById(s).get());
						//question.setTag(tag);
					}else {
						return new ResponseEntity<>("Question Not Created Due to Unavailable TagId "+ s, HttpStatus.BAD_REQUEST);
					}
				}
				question.setTag(tag);

			}else {
				List<Tag> tag = new ArrayList<Tag>() ;
				tag.add(tagService.getTagById(1).get());
				question.setTag(tag);
			}
			if(subtopic_id!=null) {
				List<SubTopic> subTopic = new ArrayList<SubTopic>() ;

				for(Long s:subtopic_id) {
					if(s==null) {
						break;
					}
					if(subTopicService.getSubTopicById(s).isPresent()) {
						subTopic.add(subTopicService.getSubTopicById(s).get());
						
					}else {
						return new ResponseEntity<>("Question Not Created Due to Unavailable SubTopicId "+ s, HttpStatus.BAD_REQUEST);
					}
				}
				question.setSubTopic(subTopic);

			}						
		
			if(userService.findUserById(id).isPresent()) {
				question.setUser(userService.findUserById(id).get());
			}else {
				return new ResponseEntity<>("Question Not Created Due to Unavailable UserId "+ id, HttpStatus.BAD_REQUEST);
			}
			Date d = new Date();
			question.setQuestion_date(d);
			question.setQuestion_likes(0);
			long id_que = questionService.addQuestion(question);
			return new ResponseEntity<>("Question Created with Id "+ id_que, HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("Question Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/question")
	public ResponseQuestionBean getQuestions(@RequestParam("question_id") long question_id){
		ResponseQuestionBean rQB = new ResponseQuestionBean();
		ResponseAnswerBean rAB;
		ResponseCommentBean rCB ;
		if(questionService.getQuestionById(question_id).isPresent()) {
			Question question = questionService.getQuestionById(question_id).get();
			rQB.setQuestion_id(question_id);
			rQB.setQuestion(question.getQuestion_());
			rQB.setQuestion_likes(question.getQuestion_likes());
			List<Company> li = question.getCompany();
			List<String> companies  = new ArrayList<String>();
			for(Company c:li) {
				if(companyService.getCompanyById(c.getCompany_id()).isPresent()) {
					companies.add(companyService.getCompanyById(c.getCompany_id()).get().getCompany_name());					
				}
			}
			rQB.setCompanies(companies);
			List<String> tags = new ArrayList<String>() ;

			for(Tag s:question.getTag()) {				
				if(tagService.getTagById(s.getTag_id()).isPresent()) {
					tags.add(tagService.getTagById(s.getTag_id()).get().getTag_name());
					//question.setTag(tag);
				}
			}
			rQB.setTags(tags);
			List<String> topics = new ArrayList<String>();
			List<SubTopic> lis = question.getSubTopic();
			for(SubTopic s:lis) {
				if(s==null) {
					break;
				}
				if(subTopicService.getSubTopicById(s.getSubtopic_id()).isPresent()) {
					topics.add(subTopicService.getSubTopicById(s.getSubtopic_id()).get().getTopic().getTopic_name());
					
				}
			}
			rQB.setTopics(topics);
			
			List<ResponseAnswerBean> answers = new ArrayList<ResponseAnswerBean>();
			List<Answer> answer = answerService.getAnswerByQId(question_id);
			for(Answer a :answer) {
				rAB = new ResponseAnswerBean();
				rAB.setAnswer(a.getAnswer_());
				rAB.setAnswer_likes(a.getAnswer_likes());
				rAB.setUser_id(a.getUser().getId());
				List<Comment> comment = commentService.getCommentsByAID(a.getAnswer_id());
				List<ResponseCommentBean> comments = new ArrayList<ResponseCommentBean>();
				
				for(Comment c:comment) {
					rCB = new ResponseCommentBean();
					rCB.setUser_id(c.getUser().getId());
					rCB.setComment(c.getComment_());
					rCB.setComment_date(c.getCommentDate());
					comments.add(rCB);					
				}
				answers.add(rAB);
				rAB.setComments(comments);
			}
			
			rQB.setAnswers(answers);
			return rQB;
			
		}else {
			return null;
			//return new ResponseEntity<>("Question_id did not Found "+ question_id, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/Filter_Question")
	public List<ResponseQuestionBean> getFiterQuestions(@RequestParam(name = "company_id",required = false) Long[] company_id,@RequestParam(name = "subtopic_id",required=false) Long[] subtopic_id,@RequestParam(name = "likes",required=false) Long likes,
			@RequestParam(name = "tag_id", required = false) Long[] tag_id,@RequestParam(name = "date",required=false) String date ) throws ParseException{
		List<ResponseQuestionBean> rQBL = new ArrayList<ResponseQuestionBean>();
		
		ResponseQuestionBean rQB ;
		ResponseAnswerBean rAB;
		if(tag_id==null) {
			tag_id = tagService.getAllTags();
		}
		if(company_id==null) {
			company_id = companyService.getAllCompanies();
		}
		if(subtopic_id==null) {
			subtopic_id = subTopicService.getAllSubTopics();
		}
		if(date==null) {
			date = "0000-00-00";
		}
		if(likes==null) {
			likes = (long) 0;
		}
		
		List<Question> questions = questionService.getQuestionByFilter(likes, company_id,tag_id, subtopic_id,new SimpleDateFormat("yyyy-MM-dd").parse(date));
		for(Question q:questions) {
			rAB = new ResponseAnswerBean();
			rQB = new ResponseQuestionBean();
			rQB.setQuestion_id(q.getQuestion_id());
			rQB.setQuestion(q.getQuestion_());
			rQB.setQuestion_likes(q.getQuestion_likes());
			List<String> topics = new ArrayList<String>();
			for(SubTopic s: q.getSubTopic()) {
				if(s==null) {
					break;
				}
				if(subTopicService.getSubTopicById(s.getSubtopic_id()).isPresent()) {
					topics.add(subTopicService.getSubTopicById(s.getSubtopic_id()).get().getTopic().getTopic_name());					
				}
			}
			rQB.setTopics(topics);
			 
			List<String> companies  = new ArrayList<String>();
			for(Company c:q.getCompany()) {
				if(companyService.getCompanyById(c.getCompany_id()).isPresent()) {
					companies.add(companyService.getCompanyById(c.getCompany_id()).get().getCompany_name());					
				}
			}
			rQB.setCompanies(companies);
			List<String> tags = new ArrayList<String>() ;
			for(Tag s:q.getTag()) {				
				if(tagService.getTagById(s.getTag_id()).isPresent()) {
					tags.add(tagService.getTagById(s.getTag_id()).get().getTag_name());
					//question.setTag(tag);
				}
			}
			rQB.setTags(tags);
			
			Optional<Answer> ans = answerService.getMostLikesAnswerById(q.getQuestion_id());
			if(ans.isPresent()) {
				rAB.setAnswer(ans.get().getAnswer_());
				rAB.setAnswer_likes(ans.get().getAnswer_likes());
				rAB.setUser_id(ans.get().getUser().getId());
			}
			List<ResponseAnswerBean> anss = new ArrayList<ResponseAnswerBean>();
			anss.add(rAB);
			rQB.setAnswers(anss);
			rQBL.add(rQB);
		}
		return rQBL;
		/*
		if(questionService.getQuestionById(Long.parseLong((String)json.get("question_id"))).isPresent()) {
			
			Question question = questionService.getQuestionById(Long.parseLong(json.get("question_id"))).get();
			rQB.setQuestion_id(question_id);
			rQB.setQuestion(question.getQuestion_());
			rQB.setQuestion_likes(question.getQuestion_likes());
			List<Company> li = question.getCompany();
			List<String> companies  = new ArrayList<String>();
			for(Company c:li) {
				if(companyService.getCompanyById(c.getCompany_id()).isPresent())
					companies.add(companyService.getCompanyById(c.getCompany_id()).get().getCompany_name());					
				}
			rQB.setCompanies(companies);
			List<String> tags = new ArrayList<String>() ;

			for(Tag s:question.getTag()) {				
				if(tagService.getTagById(s.getTag_id()).isPresent()) {
					tags.add(tagService.getTagById(s.getTag_id()).get().getTag_name());
					//question.setTag(tag);
				}
			}
			rQB.setTags(tags);
			List<String> topics = new ArrayList<String>();
			List<SubTopic> lis = question.getSubTopic();
			for(SubTopic s:lis) {
				if(s==null) {
					break;
				}
				if(subTopicService.getSubTopicById(s.getSubtopic_id()).isPresent()) {
					topics.add(subTopicService.getSubTopicById(s.getSubtopic_id()).get().getTopic().getTopic_name());
					
				}
			}
			rQB.setTopics(topics);
			
			List<ResponseAnswerBean> answers = new ArrayList<ResponseAnswerBean>();
			List<Answer> answer = answerService.getAnswerByQId(question_id);
			for(Answer a :answer) {
				rAB = new ResponseAnswerBean();
				rAB.setAnswer(a.getAnswer_());
				rAB.setAnswer_likes(a.getAnswer_likes());
				rAB.setUser_id(a.getUser().getId());
				List<Comment> comment = commentService.getCommentsByAID(a.getAnswer_id());
				List<ResponseCommentBean> comments = new ArrayList<ResponseCommentBean>();
				
				for(Comment c:comment) {
					rCB = new ResponseCommentBean();
					rCB.setUser_id(c.getUser().getId());
					rCB.setComment(c.getComment_());
					rCB.setComment_date(c.getCommentDate());
					comments.add(rCB);					
				}
				answers.add(rAB);
				rAB.setComments(comments);
			}
			
			rQB.setAnswers(answers);
			return rQB;
			
		}else {
			return null;
			//return new ResponseEntity<>("Question_id did not Found "+ question_id, HttpStatus.BAD_REQUEST);
		}
		*/
	}
}
