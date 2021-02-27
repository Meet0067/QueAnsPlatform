package com.Controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Model.User;
import com.Service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestParam("email") String email,@RequestParam("password") String pass){		
			
		User user = userService.validateuser(email,pass);
		try{
			if(user.getId()!=0) {			
	
			return new ResponseEntity<>("User Logged IN", HttpStatus.ACCEPTED);
			
			}else {			
				throw new Exception();
			}
		}catch (Exception e) {
			return new ResponseEntity<>("Incorrect Email or Pass", HttpStatus.BAD_REQUEST);
		}
		
	}
	//@ApiOperation(value = "******************************************", produces = "application/json")
	@PostMapping("/signup")
	public  ResponseEntity<?> addUser(@RequestParam("email") String email,@RequestParam("password") String pass,@RequestParam String first_name,@RequestParam String last_name){		
		
		
		try {
			User user = new User();
			user.setEmail(email);
			user.setFirst_name(first_name);
			user.setLast_name(last_name);
			user.setPassword(pass);
			long uid = userService.addUser(user);
			if(uid!=0){
				return new ResponseEntity<>("User Created with Id " + uid, HttpStatus.ACCEPTED);
			}else {			
				throw new Exception();
			}		
			
		}catch (Exception e) {
			return new ResponseEntity<>("User Not Created", HttpStatus.BAD_REQUEST);
		}
		
	}
/*
	@DeleteMapping("/signup/{user_id}")
	public ResponseEntity<?>  deleteUser(@PathVariable("user_id") long user_id){		
		//System.out.println(user.getId());
		try {
			Optional<User> user_found = userService.findUserById(user_id);
			if (user_found.isPresent()) {
				userService.deleteUser(user_id);	
				return new ResponseEntity<>("User Deleted", HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
				

	}*/
	@GetMapping("/user")
	public ResponseEntity<?> showUser(@RequestParam("uId") Integer uid){				
		try{
			Optional<User> user_found  = userService.findUserById(uid);
			if(user_found.isPresent()) {
				return new ResponseEntity<>(user_found.get(), HttpStatus.ACCEPTED);
			}else {
				throw new Exception();
			}
			
		}catch (Exception e) {
			return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
		}
		
	}
}
