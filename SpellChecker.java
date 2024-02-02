
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if (word1.length()==0) {
			return word2.length();
		}
		if(word2.length()==0){
			return word1.length();
		}
		word1.toLowerCase();
		word2.toLowerCase();
		if(word1.charAt(0)==word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		}
		int del = levenshtein(tail(word1), word2);
		int ins = levenshtein(word1, tail(word2));
		int sub = levenshtein(tail(word1), tail(word2));
		return 1+ Math.min(Math.min(del, ins), sub);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i=0; i<dictionary.length; i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = levenshtein(word, dictionary[0]);
		String simillar	= dictionary[0];
		for(int i=1; i<dictionary.length; i++){
			if(min>levenshtein(word, dictionary[i])){
				min = levenshtein(word, dictionary[i]);
				simillar = dictionary[i];
			}
		}
		if(min<threshold){
			return simillar;
		}
		else{
			return word;
		}
	}

}
