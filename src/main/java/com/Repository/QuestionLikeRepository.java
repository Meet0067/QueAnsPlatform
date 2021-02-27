package com.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Model.QuestionLike;

@Repository
public interface QuestionLikeRepository extends JpaRepository<QuestionLike,Long> {

	
	@Query(value = "SELECT * FROM questionlike WHERE question_id = ?1 and id =?2", nativeQuery = true)
	Optional<QuestionLike> checkUserLike(long question_id,long id);
	
    
}	
