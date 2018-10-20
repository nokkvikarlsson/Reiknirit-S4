package s4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class WordNet {

	private SAP sap;
	private Word[] words;
	
	private static class Word {
		private int id;
		private Queue<String> nouns;
		private String desc;
	}
	
	//Constructor takes the names of the two input files
	public WordNet(String synsets, String hypernyms) {
		In synsetfile = new In(synsets);
		In hypernymfile = new In(hypernyms);
		
		fillWords(synsetfile);
		fillSAP(hypernymfile);
	}
	
	private void fillWords(In synsetfile) {
		String line = "";
		Queue<Word> queueOfWords = new Queue<Word>();
		while(synsetfile.hasNextLine()) {
			line = synsetfile.readLine();
			Word newWord = new Word();
			String[] splitstring = line.split(",");
			int newId = Integer.parseInt(splitstring[0]);
			String[] lineNouns = splitstring[1].split(" ");
			Queue<String> newNouns = new Queue<String>();
			for(String l: lineNouns) {
				newNouns.enqueue(l);
			}
			newWord.id = newId;
			newWord.nouns = newNouns;
			newWord.desc = splitstring[2];
			queueOfWords.enqueue(newWord);
		}
		
		words = new Word[queueOfWords.size()];
		for(Word w: queueOfWords) {
			words[w.id] = w;
		}
	}
	
	private void fillSAP(In hypernymfile) {
		Bag<Queue<String>> edges = new Bag<Queue<String>>();
		String line = "";
		while(hypernymfile.hasNextLine()) {
			line = hypernymfile.readLine();
			String[] splitstring = line.split(",");
			Queue<String> newEdges = new Queue<String>();
			for(String s: splitstring) {
				newEdges.enqueue(s);
			}
			edges.add(newEdges);
		}
		
		Digraph d = new Digraph(words.length);
		for(Queue<String> q: edges) {
			int connectedFrom = Integer.parseInt(q.dequeue());
			for(String s: q) {
				d.addEdge(connectedFrom, Integer.parseInt(s));
			}
		}
		sap = new SAP(d);
	}
	
	//returns the index of the word. returns -1 if not found
	private int getIndex(String word) {
		int index = -1;
		for(int i = 0; i < words.length; i++) {
			for(String n: words[i].nouns) {
				if(word.equals(n)) {
					index = i;
				}
			}
		}
		return index;
	}
	
	//Returns all WordNet nouns
	public Iterable<String> nouns(){
		Bag<String> output = new Bag<String>();
		for(Word w: words) {
			for(String n: w.nouns) {
				output.add(n);
			}
		}
		
		return output;
	}
	
	//Is the word a WordNet noun?
	public boolean isNoun(String word) {
		
		boolean output = false;
		for(Word w: words) {
			for(String n: w.nouns) {
				if(word.equals(n)) {
					output = true;
					break;
				}
			}
		}
		
		return output;
	}
	
	//Distance between Noun A and Noun B (defined below)
	public int distance(String nounA, String nounB) {
		int indexA = getIndex(nounA);
		int indexB = getIndex(nounB);
		
		if(indexA == -1 || indexB == -1) {
			throw new IllegalArgumentException();
		}
		
		return sap.length(indexA, indexB);
	}
	
	//A synset (second field of synsets.txt) that is the shortest common ancestor
	//of Noun A and Noun B
	public String sap(String nounA, String nounB) {
		int indexA = getIndex(nounA);
		int indexB = getIndex(nounB);
		
		if(indexA == -1 || indexB == -1) {
			throw new IllegalArgumentException();
		}
		
		int ancestorid = sap.ancestor(indexA, indexB);
		String output = "";
		for(String n: words[ancestorid].nouns) {
			output += " ";
			output += n;
		}
		
		return output.substring(1);
	}
	
	//Do unit testing of this class
	public static void main(String[] args) {
		WordNet w = new WordNet(args[1], args[2]);
	}
}
