package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Model.Tag;


@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {

	@Query(value = "SELECT tag_id FROM tag", nativeQuery = true)
	Long[] getAllTags();
}
