package com.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
    private Long question_id;
	
	@NotBlank
    @Size(min = 50, max = 500)
    @Column(name = "question_", nullable = false)
    private String question_;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
	@Column
	private Date question_date;

   
	public Date getQuestion_date() {
		return question_date;
	}

	public void setQuestion_date(Date question_date) {
		this.question_date = question_date;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
    		  name = "question_subtopic_mapping", 
    		  joinColumns = @JoinColumn(name = "question_id", nullable = false), 
    		  inverseJoinColumns = @JoinColumn(name = "subtopic_id", nullable = false))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<SubTopic> subTopic;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
    		  name = "question_tag", 
    		  joinColumns = @JoinColumn(name = "question_id", nullable = false), 
    		  inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Tag> tag;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
    		  name = "question_company_mapping", 
    		  joinColumns = @JoinColumn(name = "question_id", nullable = false), 
    		  inverseJoinColumns = @JoinColumn(name = "company_id", nullable = false))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<Company> company;

    @Column(name = "question_likes", nullable = false)
    private long question_likes;
    

	public long getQuestion_likes() {
		return question_likes;
	}

	public void setQuestion_likes(long question_likes) {
		this.question_likes = question_likes;
	}

	public Long getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Long question_id) {
		this.question_id = question_id;
	}

	

	public String getQuestion_() {
		return question_;
	}

	public void setQuestion_(String question_) {
		this.question_ = question_;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	

	public List<Company> getCompany() {
		return company;
	}

	public void setCompany(List<Company> company) {
		this.company = company;
	}

	

	public List<SubTopic> getSubTopic() {
		return subTopic;
	}

	public void setSubTopic(List<SubTopic> subTopic) {
		this.subTopic = subTopic;
	}

	public List<Tag> getTag() {
		return tag;
	}

	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}

	


}
