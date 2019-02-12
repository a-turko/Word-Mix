import java.util.*;
import java.io.*;

public class WordBase 
{
	String WordBaseName;
	Set <String> WordSet = new TreeSet<String>();
	Map <String, Vector <String> > WordMap = new TreeMap <String, Vector <String> > ();
	
	public WordBase (String name) 
	{
	this.WordBaseName=name;
	}
	
	public WordBase (String name, String fileName)
	{
		this.WordBaseName=name;
		File dataBase;
		Scanner sc;
		String key, word;
		String [] val = null;
		dataBase = new File (fileName);
		try {
			sc= new Scanner(dataBase);
			while (sc.hasNext() && sc.next()!="#WordMap") ;
			
			while (sc.hasNext()) {
				key=sc.next();
				int k = sc.nextInt();
				val = new String[k];
				for (int i=0; i<k; i++) 
				{
					val[i]=sc.next();
					WordSet.add(val[i]);
				}
				
				WordMap.put(key, new Vector <String> (Arrays.asList(val)));
			}
		}
		catch (FileNotFoundException ex) {
			System.err.println(WordBaseName + ": word base " + fileName + " was not found");
		}
	}
	
	public boolean exists (String word)
	{	
		return WordSet.contains(word);
	}
	
	//lists all words with the given set of letters - null ipf none
	public Vector <String> listAll (Vector <Character> letters)
	{
		Collections.sort(letters);
		String key = letters.toString();
		return WordMap.get(key);
	} 
	
	//This method will create a Wordbase using contents of files passed as arguments
	public static void main (String [] args) throws FileNotFoundException 
	{
		File text;
		Scanner sc; 
		String word;
		WordBase dictionary = new WordBase(args[0]);
		
		for (int i=1; i<args.length; i++) {
		
			text = new File(args[i]);
			try	{
				sc = new Scanner(text);
				
				while (sc.hasNext()) {
				word=sc.next();
				dictionary.addWord(word);
				
				}
			}
			catch (FileNotFoundException ex) {
				System.err.println(args[0] + ": cannot find file: " + args[i]);
			}
		
		}
		
		dictionary.printWordBase();
		
		dictionary.printToFile();
	}
	
	boolean addWord (String word) 
	{
		
		if (word.length()>20) return false;
		
		char[] letters = new char[20];
		Arrays.fill(letters, '#');
		word.getChars(0,word.length(), letters, 0);
		String entry = new String ();
		boolean began=false, ended=false;
		
		for (Character c: letters) {
			if (Character.isLetter(c)) {
				if (ended) return false;
				began=true;
				if (Character.isUpperCase(c)) {
					return false;
				}
				entry=entry.concat(Character.toString(c));
			}
			else {
				if (began) ended=true;
			}
		}
		
		if (entry.length()>15 || entry.length()==0) return false;
		if (!WordSet.add(entry)) return false;
		char[] order = entry.toCharArray();
		Arrays.sort(order);
		String key = new String(order);
		
		if (!WordMap.containsKey(key)) WordMap.put(key, new Vector <String> ());
		Vector <String> wek = WordMap.get(key);
		wek.add(entry);
		return true;
	}
	
	//human readable
	void printWordBase () 
	{ 
		System.out.println("\nWordBase: " + WordBaseName);
		System.out.println("\nWordSet: \n");
		for (String s: WordSet) System.out.println(s);
		System.out.println("\nWordMap: \n");
		for (Map.Entry <String, Vector <String> > p: WordMap.entrySet()) {
			System.out.printf(p.getKey() + ": ");
			for (String s: p.getValue()) System.out.printf (s + " ");
			System.out.printf("\n");
		}
	}
	
	//creates [WordBaseName].wb file and stores the current word base there
	boolean printToFile ()
	{
		PrintWriter dataFile = null;
		try {
			dataFile = new PrintWriter (WordBaseName+".wb", "UTF-8");
		}
		catch (FileNotFoundException ex) {
			System.err.println("Cannot create file " + WordBaseName + ".wb");
			return false;
		}
		catch (UnsupportedEncodingException ex) {
			System.err.println("Unsupported encoding");
			return false;
		}
		
		dataFile.println(WordBaseName + " data file");
		
		dataFile.println("#WordMap"); //after this line WordMap contents follow
		
		for (Map.Entry <String, Vector <String> > p: WordMap.entrySet()) {
			dataFile.printf (p.getKey() +" "+p.getValue().size()+" ");
				for (String s: p.getValue()) dataFile.printf(s + " ");
			dataFile.printf ("\n");
		}
		
		dataFile.close();
		return true;
	}
		
		
}
