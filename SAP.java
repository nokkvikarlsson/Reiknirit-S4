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
	
	
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		
		//Checks if the input is out of bounds.
		checkOneInput(v, w);
		
		BreadthFirstDirectedPaths BFD_V = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths BFD_W = new BreadthFirstDirectedPaths(digraph, w);
		
		//Finds the shortest ancestor between the two vertices.
		int shortestAncestor = ancestor(v, w);

		//If there is no ancestor return -1.
		if(shortestAncestor == -1) {
			return -1;
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

	// a shortest common common ancestor of v and w; -1 if no such path.
	//Same functionality as in ancestor(int v, int w)
	public int ancestor (int v, int w) {
		
		//Checks if the input is out of bounds.
		checkOneInput(v, w);
		
		BreadthFirstDirectedPaths BFD_V = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths BFD_W = new BreadthFirstDirectedPaths(digraph, w);
		
		int shortestCurrentAncestor = -1;
		int lengthSCA = Integer.MAX_VALUE; //Length of the current ancestor
		int length = 0;
		
		//Find the shortest common ancestor.
		for(int i = 0; i < digraph.V(); i++) {
			//Checks if there is a path from vertex i to v and w.
			if(BFD_V.hasPathTo(i) && BFD_W.hasPathTo(i)) {
				//Sum the distance from i to vertex v and w and store it in length.
				length = BFD_V.distTo(i) + BFD_W.distTo(i);
				//Checks if the length is smaller than the length of the current ancestor.
				//If it is then lengthSCA gets the value of length and shortestCurrentAncestor gets the value of i.
				if(length <= lengthSCA) {
					lengthSCA = length;
					shortestCurrentAncestor = i;
				}
			}
		}
		return index;
	}
	
	// length of shortest ancestral path of vertex subsets A and B ; -1 if no such path
	//Same functionality as in length(int v, int w)
	public int length(Iterable<Integer> A, Iterable<Integer> B) {
		
		//Checks if the input is out of bounds.
		checkMultipleInputs(A, B);
		
		BreadthFirstDirectedPaths BFD_A = new BreadthFirstDirectedPaths(digraph, A);
		BreadthFirstDirectedPaths BFD_B = new BreadthFirstDirectedPaths(digraph, B);
		
		int shortestAncestor = ancestor(A, B);
		if(shortestAncestor == -1) {
			return -1;
		}
		
		return output;
	}
	
	// a shortest common ancestor of vertex subsets A and B; -1 if no such path
	//Same functionality as in ancestor(int v, int w)
	public int ancestor(Iterable<Integer>A, Iterable<Integer> B) {
		
		//Checks if the input is out of bounds.
		checkMultipleInputs(A, B);
		
		BreadthFirstDirectedPaths BFD_A = new BreadthFirstDirectedPaths(digraph, A);
		BreadthFirstDirectedPaths BFD_B = new BreadthFirstDirectedPaths(digraph, B);
		
		int shortestCurrentAncestor = -1;
		int lengthSCA = Integer.MAX_VALUE;
		int length = 0;
		
		for(int i = 0; i < digraph.V(); i++) {
			if(BFD_A.hasPathTo(i) && BFD_B.hasPathTo(i)) {
				length = BFD_A.distTo(i) + BFD_B.distTo(i);
				if(length <= lengthSCA) {
					lengthSCA = length;
					shortestCurrentAncestor = i;
				}
			}
		}
		
		return output;
	}
	
	//Checks if the vertices are in the graph
	private void checkOneInput(int v, int w) {
		if(v < 0 || w < 0 || v >= digraph.V() || w >= digraph.V()) {
			throw new IndexOutOfBoundsException();
		}
		
		return sap.length(indexA, indexB);
	}
	
	//Checks if the vertices are in the graph
	private void checkMultipleInputs(Iterable<Integer>A, Iterable<Integer> B) {
		for(int a: A) {
			if(a < 0  || a >= digraph.V()) {
				throw new IndexOutOfBoundsException();
			}
		}
		
		int ancestorid = sap.ancestor(indexA, indexB);
		String output = "";
		for(String n: words[ancestorid].nouns) {
			output += " ";
			output += n;
		}
		
		return output.substring(1);
	}
	
	// do unit testing of this class
	public static void main (String [] args) {
       
		
	}
}
