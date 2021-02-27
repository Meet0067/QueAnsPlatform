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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "answerlike")
public class AnswerLike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long answer_like_id;
	@Column(name = "id", nullable = false)
    private long id;

	@Column(name = "answer_id", nullable = false)
    private long answer_id;

	public Long getAnswer_like_id() {
		return answer_like_id;
	}

	public void setAnswer_like_id(Long answer_like_id) {
		this.answer_like_id = answer_like_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(long answer_id) {
		this.answer_id = answer_id;
	}
	
   
    
}