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
@Table(name = "questionlike")
public class QuestionLike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_like_id;


	@Column(name = "id", nullable = false)
    private long id;

	@Column(name = "question_id", nullable = false)
    private long question_id;

	public Long getQuestion_like_id() {
		return question_like_id;
	}

	public void setQuestion_like_id(Long question_like_id) {
		this.question_like_id = question_like_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(long question_id) {
		this.question_id = question_id;
	}
    
}