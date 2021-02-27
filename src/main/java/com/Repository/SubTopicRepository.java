package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Model.SubTopic;

@Repository
public interface SubTopicRepository extends JpaRepository<SubTopic,Long> {
	

	@Query(value = "SELECT subtopic_id FROM subtopic", nativeQuery = true)
	Long[] getAllSubTopics();
}
