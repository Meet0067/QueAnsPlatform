package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Model.Tag;
import com.Repository.TagRepository;
@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;
	
	public long addTag(Tag tag) {
		return tagRepository.save(tag).getTag_id();
		// TODO Auto-generated method stub
		
	}

	public void updateTag(Tag tag) {
		// TODO Auto-generated method stub
		
		tagRepository.save(tag);
	}

	public Optional<Tag> getTagById(long tag_id) {
		// TODO Auto-generated method stub
		return tagRepository.findById(tag_id);
	}

	public void deleteById(long tag_id) {
		// TODO Auto-generated method stub
		tagRepository.deleteById(tag_id);
	}

	public Long[]getAllTags() {
		// TODO Auto-generated method stub
		return tagRepository.getAllTags();
	}
}