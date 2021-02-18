package com.hangman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangman.entities.HangmanSinglePuzzle;
import com.hangman.repos.PuzzleStore;

//Business logic class for the SingleGameController etc.
@Service
public class PuzzleService {
	
	@Autowired
	private PuzzleStore pRepo;
	
	//TODO: addrepo methods/class for h2/etc
	
	//Temp method to test
	public void addTestPuzzlesToDB() {
		pRepo.save(new HangmanSinglePuzzle("testword", 6));
		HangmanSinglePuzzle addme = new HangmanSinglePuzzle("testwordtwo", 6);
		ArrayList<Character> testChar = new ArrayList<>();
		testChar.add('c');
		testChar.add('a');
		testChar.add('r');
		addme.setGuessedLetters(testChar);
		pRepo.save(addme);
	}

	//right now just returns all found puzzles regardless of completion status
	public List<HangmanSinglePuzzle> getAllPuzzles(){
		addTestPuzzlesToDB();
		
		ArrayList<HangmanSinglePuzzle> temp = new ArrayList<>();
		try {
			for(Object o : pRepo.findAll()) {
				if(o instanceof HangmanSinglePuzzle) {
					temp.add((HangmanSinglePuzzle)o);
				}
			}
		} catch(Exception err){
			System.out.println("Something went wrong fetching all hangmanSinglePuzzles");
			System.out.println(err);
		}
		
		return temp;
	}
	
}
