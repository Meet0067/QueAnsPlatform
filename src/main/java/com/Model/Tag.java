package com.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="tag")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tag_id;
	
	@Column(name = "tag_name", nullable = false)
	private String tag_name;

	@ManyToMany(mappedBy = "tag")
	@JsonIgnore
	List<Question> question;
	
	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public long getTag_id() {
		return tag_id;
	}

	public void setTag_id(long tag_id) {
		this.tag_id = tag_id;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	
}
