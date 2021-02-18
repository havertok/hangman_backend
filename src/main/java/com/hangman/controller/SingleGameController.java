package com.hangman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangman.entities.HangmanSinglePuzzle;
import com.hangman.service.PuzzleService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/puzzles")
public class SingleGameController {
	
	private PuzzleService ps;
	
	@Autowired
	public SingleGameController(PuzzleService ps) {
		this.ps = ps;
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<HangmanSinglePuzzle>> getAllCampaigns(){
		return new ResponseEntity<>(ps.getAllPuzzles(), HttpStatus.OK);
	}

}
