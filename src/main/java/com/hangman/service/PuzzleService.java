package com.hangman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangman.entities.SinglePuzzle;
import com.hangman.repos.PuzzleStore;

//Business logic class for the SingleGameController etc.
@Service
public class PuzzleService {
	
	@Autowired
	private PuzzleStore pRepo;
	
	//TODO: addrepo methods/class for h2/etc
	
	//Temp method to test
	public void addTestPuzzlesToDB() {
		pRepo.save(new SinglePuzzle("testword", 0));
		SinglePuzzle addme = new SinglePuzzle("testwordtwo", 3);
		SinglePuzzle addme2 = new SinglePuzzle("testwordthree", 1);
		SinglePuzzle addme3 = new SinglePuzzle("testwordfour", 5);
		SinglePuzzle addme4 = new SinglePuzzle("testwordfive", 2);
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
	
	public SinglePuzzle getPuzzleById(int ID) {
		Optional<SinglePuzzle> result = pRepo.findById(ID);
		if(result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	//right now just returns all found puzzles regardless of completion status
	public List<SinglePuzzle> getAllPuzzles(){
		
		ArrayList<SinglePuzzle> temp = new ArrayList<>();
		try {
			for(Object o : pRepo.findAll()) {
				if(o instanceof SinglePuzzle) {
					temp.add((SinglePuzzle)o);
				}
			}
		} catch(Exception err){
			System.out.println("Something went wrong fetching all hangmanSinglePuzzles");
			System.out.println(err);
		}
		
		return temp;
	}
	
	//First gets a puzzle by it's ID, then updates it, returning true or false depending on whether it found such a puzzle
	public boolean modifyPuzzle(SinglePuzzle puzz) {
		try {
			pRepo.save(puzz);
			return true;
		} catch (Exception err) {
			System.out.println(err);
		}
		return false;
	}
	
}
