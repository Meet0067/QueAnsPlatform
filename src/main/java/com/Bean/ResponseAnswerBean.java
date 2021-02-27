package com.Bean;

import java.util.List;

public class ResponseAnswerBean {

	private String answer;
	private long user_id;
	private long answer_likes;
	private List<ResponseCommentBean> comments;
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public long getAnswer_likes() {
		return answer_likes;
	}
	public void setAnswer_likes(long answer_likes) {
		this.answer_likes = answer_likes;
	}
	public List<ResponseCommentBean> getComments() {
		return comments;
	}
	public void setComments(List<ResponseCommentBean> comments) {
		this.comments = comments;
	}
	
}
