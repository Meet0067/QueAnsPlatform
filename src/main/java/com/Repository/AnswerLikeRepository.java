package com.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Model.AnswerLike;

@Repository
public interface AnswerLikeRepository extends JpaRepository<AnswerLike,Long> {
	
	@Query(value = "SELECT * FROM answerlike WHERE answer_id = ?1 and id =?2", nativeQuery = true)
	Optional<AnswerLike> checkUserLike(long answer_id,long id);
	
}
