package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

}