package com.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Model.Tag;
import com.Service.TagService;

@RestController
public class TagController {
	@Autowired
	TagService tagService;

	@PostMapping("/tag")
	public ResponseEntity<?> addTag(@RequestParam("tag_name") String tag_name){		
		try {
			Tag t = new Tag();
			t.setTag_name(tag_name);
			long tag_id = tagService.addTag(t);
			return new ResponseEntity<>("Tag Created with Id" + tag_id, HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("Tag Not Created", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/tag")
	public ResponseEntity<?> updateTag(@RequestParam("tag_name") String tag_name,@RequestParam("tag_id") long tag_id){		
		try {
			Tag t = new Tag();
			t.setTag_name(tag_name);
			t.setTag_id(tag_id);
			Optional<Tag> tag_found = tagService.getTagById(tag_id);
			if (tag_found.isPresent()) {
				tagService.updateTag(t);
				return new ResponseEntity<>("Tag Updated", HttpStatus.ACCEPTED);		
			}else {
				throw new Exception();
			}
				
		}catch (Exception e) {
			return new ResponseEntity<>("Tag Not Updated Due to Unavailable tag_id", HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/tag")
	public ResponseEntity<?> getTag(@RequestParam("tag_id") long tag_id){		
		try {
			Optional<Tag> tag_found = tagService.getTagById(tag_id);
			if (tag_found.isPresent()) {
				return new ResponseEntity<>(tag_found.get(), HttpStatus.ACCEPTED);		
			}else {
				throw new Exception();
			}			
		}catch (Exception e) {
			return new ResponseEntity<>("Tag Not Found", HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/tag")
	public ResponseEntity<?> deleteTag(@RequestParam("tag_id") long tag_id){		
		try {
			tagService.deleteById(tag_id);
			 return new ResponseEntity<>("Tag Deleted", HttpStatus.ACCEPTED);			
		}catch (Exception e) {
			return new ResponseEntity<>("Tag Not Deleted Due to Unavailable tag_id", HttpStatus.BAD_REQUEST);
		}
	}
	
}

