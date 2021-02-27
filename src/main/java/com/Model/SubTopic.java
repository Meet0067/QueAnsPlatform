package com.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "subtopic")
public class SubTopic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subtopic_id;

    @Column(name = "subtopic_name", nullable = false)
    private String subtopic_name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Topic topic;

	@ManyToMany(mappedBy = "subTopic")
	@JsonIgnore
	private List<Question> question;

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public Long getSubtopic_id() {
		return subtopic_id;
	}

	public void setSubtopic_id(Long subtopic_id) {
		this.subtopic_id = subtopic_id;
	}

	public String getSubtopic_name() {
		return subtopic_name;
	}

	public void setSubtopic_name(String subtopic_name) {
		this.subtopic_name = subtopic_name;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}

