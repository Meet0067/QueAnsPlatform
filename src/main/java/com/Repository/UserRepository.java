package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmailAndPassword(String email, String passwrod);
}
