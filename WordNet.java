package S4;

public class WordNet {

	//Constructor takes the names of the two input files
	public WordNet(String synsets, String hypernyms) {
		
	}
	
	//Returns all WordNet nouns
	public Iterable<String> nouns(){
	
		return null;
	}
	
	//Is the word a WordNet noun?
	public boolean isNoun(String word) {
		
		return false;
	}
	
	//Distance between Noun A and Noun B (defined below)
	public int distance(String nounA, String nounB) {
		
		return -1;
	}
	
	//A synset (second field of synsets.txt) that is the shortest common ancestor
	//of Noun A and Noun B
	public String SAP(String nounA, String nounB) {
	
		return null;
	}
	
	//Do unit testing of this class
	public static void main(String[] args) {
		
	}
}
