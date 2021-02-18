package com.hangman.entities;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


//This represents a single Hangman game: A word, an int for how many misses are allowed, a number of guesses maximum, 
//a number of guesses taken, and a list of letters guessed. Also will have a solved boolean
@Entity
@Table(name = "PUZZLES")
public class HangmanSinglePuzzle {
	
	public HangmanSinglePuzzle() {
		super();
	}
	
	public HangmanSinglePuzzle(String word, int maxMisses) {
		super();
		this.hiddenWord = word;
		this.maxMisses = maxMisses;
		guessesTaken = 0;
		isSolved = false;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PUZZLE_SEQ")
	@SequenceGenerator(allocationSize = 1, name = "PUZZLE_SEQ", sequenceName = "SQ_PUZZLE")
	@Column(name = "PUZZLE_ID")
	private int ID;

	@Column(name = "PUZZLE_WORD", nullable = false)
	private String hiddenWord;
	
	@Column(name = "PUZZLE_MAX_MISSES")
	private int maxMisses;
	
	@Column(name = "PUZZLE_GUESSES_TAKEN")
	private int guessesTaken;
	
	//This will likely need to be changed
	@ElementCollection
	private Collection<Character> guessedLetters = new ArrayList<Character>();
	
	@Column(name = "PUZZLE_IS_SOLVED")
	private boolean isSolved;
	
	public int getID() {
		return ID;
	}
	
	//testing function, remove later
	public void setID(int id) {
		this.ID = id;
	}

	public String getHiddenWord() {
		return hiddenWord;
	}

	public void setHiddenWord(String hiddenWord) {
		this.hiddenWord = hiddenWord;
	}

	public int getMaxMisses() {
		return maxMisses;
	}

	public void setMaxMisses(int maxMisses) {
		this.maxMisses = maxMisses;
	}

	public int getGuessesTaken() {
		return guessesTaken;
	}

	public void setGuessesTaken(int guessesTaken) {
		this.guessesTaken = guessesTaken;
	}

	public Collection<Character> getGuessedLetters() {
		return guessedLetters;
	}

	public void setGuessedLetters(Collection<Character> guessedLetters) {
		this.guessedLetters = guessedLetters;
	}

	public boolean isSolved() {
		return isSolved;
	}

	public void setSolved(boolean isSolved) {
		this.isSolved = isSolved;
	}

	@Override
	public String toString() {
		return "HangmanSinglePuzzle [ID=" + ID + ", hiddenWord=" + hiddenWord + ", maxMisses=" + maxMisses
				+ ", guessesTaken=" + guessesTaken + ", guessedLetters=" + guessedLetters + ", isSolved=" + isSolved
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof HangmanSinglePuzzle) {
			if(this.hiddenWord.equals(((HangmanSinglePuzzle) obj).hiddenWord) && this.isSolved == ((HangmanSinglePuzzle)obj).isSolved) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
	
	

}
