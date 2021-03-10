package com.hangman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		pRepo.save(new HangmanSinglePuzzle("testword", 0));
		HangmanSinglePuzzle addme = new HangmanSinglePuzzle("testwordtwo", 3);
		HangmanSinglePuzzle addme2 = new HangmanSinglePuzzle("testwordthree", 1);
		HangmanSinglePuzzle addme3 = new HangmanSinglePuzzle("testwordfour", 5);
		HangmanSinglePuzzle addme4 = new HangmanSinglePuzzle("testwordfive", 2);
		ArrayList<Character> testChar = new ArrayList<>();
		testChar.add('c');
		testChar.add('a');
		testChar.add('r');
		ArrayList<Character> testChar2 = new ArrayList<>();
		testChar2.add('q');
		ArrayList<Character> testChar3 = new ArrayList<>();
		testChar3.add('c');
		testChar3.add('a');
		testChar3.add('r');
		testChar3.add('t');
		testChar3.add('w');
		ArrayList<Character> testChar4 = new ArrayList<>();
		testChar4.add('e');
		testChar4.add('t');
		addme.setGuessedLetters(testChar);
		addme2.setGuessedLetters(testChar2);
		addme3.setGuessedLetters(testChar3);
		addme4.setGuessedLetters(testChar4);
		pRepo.save(addme);
		pRepo.save(addme2);
		pRepo.save(addme3);
		pRepo.save(addme4);
	}
	
	public HangmanSinglePuzzle getPuzzleById(int ID) {
		Optional<HangmanSinglePuzzle> result = pRepo.findById(ID);
		if(result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	//right now just returns all found puzzles regardless of completion status
	public List<HangmanSinglePuzzle> getAllPuzzles(){
		
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
	
	//First gets a puzzle by it's ID, then updates it, returning true or false depending on whether it found such a puzzle
	public boolean modifyPuzzle(HangmanSinglePuzzle puzz) {
		try {
			pRepo.save(puzz);
			return true;
		} catch (Exception err) {
			System.out.println(err);
		}
		return false;
	}
	
}
