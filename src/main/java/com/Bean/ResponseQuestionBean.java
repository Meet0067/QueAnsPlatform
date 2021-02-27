package com.Bean;

import java.util.List;


public class ResponseQuestionBean
{
	private long question_id;
	
	private String question;
	private List<String> tags;
	private List<String> topics;
	private List<String> companies;
	private long question_likes;
	private List<ResponseAnswerBean> answers;
	public long getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<String> getTopics() {
		return topics;
	}
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	public List<String> getCompanies() {
		return companies;
	}
	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}
	public long getQuestion_likes() {
		return question_likes;
	}
	public void setQuestion_likes(long question_likes) {
		this.question_likes = question_likes;
	}
	public List<ResponseAnswerBean> getAnswers() {
		return answers;
	}
	public void setAnswers(List<ResponseAnswerBean> answers) {
		this.answers = answers;
	}

	
	
	
	
}