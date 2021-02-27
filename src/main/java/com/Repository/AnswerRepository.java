package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Model.Answer;



@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

	
	@Query(value = "SELECT * FROM answer WHERE question_id = ?1 ", nativeQuery = true)	
	List<Answer> getAnswers(long question_id);
	
	@Query(value = "select * from (select * from answer where question_id=?1) AS newTable where answer_likes = "
			+ "( select MAX(answer_likes) from (select * from answer where question_id=?1) AS Dtables )", nativeQuery = true)	
	Optional<Answer> getMostLikesAnswer(long question_id);
}
