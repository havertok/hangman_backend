package com.hangman.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hangman.entities.UserModel;

@Repository(value="UserStore")
@Transactional
public interface UserStore extends JpaRepository<UserModel, Integer> {
	
	Optional<UserModel> findByUsername(String username);

	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
}
