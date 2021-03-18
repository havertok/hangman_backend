package com.hangman.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hangman.entities.*;

//Will do a custom one without jpa magic later
@Repository(value="PuzzleStore")
@Transactional
public interface PuzzleStore extends JpaRepository<SinglePuzzle, Integer>  {

}
