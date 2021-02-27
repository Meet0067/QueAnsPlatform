package com.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "answer")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_id;
	
	@NotBlank
    @Column(name = "answer_", nullable = false)
    private String answer_;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Question question;


    @Column(name = "answer_likes", nullable = false)
    private long answer_likes;
	public long getAnswer_likes() {
		return answer_likes;
	}


	public void setAnswer_likes(long answer_likes) {
		this.answer_likes = answer_likes;
	}


	public Long getAnswer_id() {
		return answer_id;
	}


	public void setAnswer_id(Long answer_id) {
		this.answer_id = answer_id;
	}


	public String getAnswer_() {
		return answer_;
	}


	public void setAnswer_(String answer_) {
		this.answer_ = answer_;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}
    
}