import java.util.*;
//load scrabble letterset suitable for the laungage
public class Sack
{
	Vector <Character> LetterSet;
	Vector <Character> Alphabet;
	Random Rand;
	public Sack (Lang lang)
	{
		Rand = new Random ();
		switch (lang) {
			case ENG:  loadEng();
		}
	}
	
	//draw k random letters
	Vector <Character> draw (int k)
	{
		Vector <Character> wek = new Vector <Character > (k);
		for (int i=0; i < k; i++) wek.add(Alphabet.get(Rand.nextInt(99)));
		return wek;
	}
	
	void loadEng ()
	{
		Alphabet = new Vector <Character> (27);
		for (char c='a'; c<='z'; c++) Alphabet.add(c);
		LetterSet = new Vector <Character> (100);
		
		
		for (int i=0; i < 12; i++) LetterSet.add('a');
		for (int i=0; i < 9; i++) {
			LetterSet.add('a');
			LetterSet.add('i');
		}
		for (int i=0; i < 8; i++) LetterSet.add('o');
		for (int i=0; i < 6; i++) {
			LetterSet.add('n');
			LetterSet.add('r');
			LetterSet.add('t');
		}
		for (int i=0; i < 4; i++) {
			LetterSet.add('l');
			LetterSet.add('s');
			LetterSet.add('u');
			LetterSet.add('d');
		}
		for (int i=0; i < 3; i++) {
			LetterSet.add('g');
		}
		for (int i=0; i < 2; i++) {
			LetterSet.add('b');
			LetterSet.add('c');
			LetterSet.add('m');
			LetterSet.add('p');
			LetterSet.add('f');
			LetterSet.add('h');
			LetterSet.add('v');
			LetterSet.add('w');
			LetterSet.add('y');
		}
		LetterSet.add('k');
		LetterSet.add('j');
		LetterSet.add('x');
		LetterSet.add('q');
		LetterSet.add('z');
	}
}
