package com.Repository;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Model.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
	
	@Query(value = "select question_id from ("
			+ " select t1.question_id,t1.question_likes,t1.question_date,t2.tag_id,t3.company_id,t4.subtopic_id from question t1"
			+ " inner join  question_tag t2 on t1.question_id = t2.question_id"
			+ " inner join question_company_mapping t3 on t1.question_id =t3.question_id "
			+ " inner join question_subtopic_mapping t4 on t1.question_id = t4.question_id)"
			+ " AS derivedtable where question_likes >= :question_like and tag_id in :tag_id and company_id in :company_id and subtopic_id in :subtopic_id and question_date > cast(:date AS timestamp)", nativeQuery = true)
	Set<Long> getQidForFilterQuestion(Long question_like,Long[] company_id,Long[] tag_id,Long[] subtopic_id,Date date);
}