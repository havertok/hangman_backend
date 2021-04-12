package com.hangman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangman.entities.SinglePuzzle;
import com.hangman.service.PuzzleService;

@RestController
@CrossOrigin
@RequestMapping("/puzzles")
public class PuzzleController {
	
	private PuzzleService ps;
	
	@Autowired
	public PuzzleController(PuzzleService ps) {
		this.ps = ps;
		ps.addTestPuzzlesToDB(); //REMOVE ME WHEN NO LONGER TESTING
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<SinglePuzzle>> getAllPuzzless(){
		return new ResponseEntity<>(ps.getAllPuzzles(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public ResponseEntity<String> postOnePuzzle(@RequestBody SinglePuzzle puzz){
		boolean foundPuzz = ps.modifyPuzzle(puzz);
		if(foundPuzz) {
			return new ResponseEntity<>("We got it dudes!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("There is no such puzzle or puzzle is malformed.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<String> addNewPozzle(@RequestBody SinglePuzzle puzz){
		boolean foundPuzz = ps.modifyPuzzle(puzz);
		if(foundPuzz) {
			return new ResponseEntity<>("That puzzle already exists, play the game to modify it!.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>("We got it dudes!", HttpStatus.ACCEPTED);
		}
	}
	
	@RequestMapping(value="/testAuth", method=RequestMethod.GET)
	public ResponseEntity<String> testAuthGet(){
		return new ResponseEntity<>("You are authorized!", HttpStatus.OK);
	}

}
