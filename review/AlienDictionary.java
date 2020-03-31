package review;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AlienDictionary {

	public static void main(String[] args) {
		String[] words = new String[] { "wrt", "wrf", "er", "ett", "rftt" };
		AlienDictionary sol = new AlienDictionary();
		System.out.println(sol.alienOrder(words));

	}

	/*
	 * 501. Alien Dictionary
	 * 
	 * There is a new alien language which uses the latin alphabet. However, the
	 * order among letters are unknown to you. You receive a list of non-empty words
	 * from the dictionary, where words are sorted lexicographically by the rules of
	 * this new language. Derive the order of letters in this language.
	 * 
	 * Example 1: Given the following words in dictionary,
	 * 
	 * [ "wrt", "wrf", "er", "ett", "rftt" ] The correct order is: "wertf".
	 * 
	 * Example 2: Given the following words in dictionary,
	 * 
	 * [ "z", "x" ] The correct order is: "zx".
	 * 
	 * Example 3: Given the following words in dictionary,
	 * 
	 * [ "z", "x", "z" ] The order is invalid, so return "".
	 * 
	 * Note:
	 * 
	 * You may assume all letters are in lowercase. You may assume that if a is a
	 * prefix of b, then a must appear before b in the given dictionary. If the
	 * order is invalid, return an empty string. There may be multiple valid order
	 * of letters, return any one of them is fine.
	 * 
	 */
	
	static class Graph {
		private List<Graph> adj;
		private char vertax;

		public Graph(char a) {
			this.vertax = a;
			this.adj = new ArrayList<Graph>();
		}

		public void addEdge(Graph b) {
			System.out.printf("edge added from %s to %s\n", this.vertax, b.vertax);
			this.adj.add(b);
		}
	}

	public String topoSort(Map<Character, Graph> nodeMap) {

		Map<Character, Integer> inDegree = new HashMap<Character, Integer>();

		for (Character key : nodeMap.keySet()) {
			inDegree.put(key, 0);
		}

		for (Map.Entry<Character, Graph> entry : nodeMap.entrySet()) {
			Character c = (Character) entry.getKey();
			Graph g = (Graph) entry.getValue();
			for (Graph adj : g.adj) {
				Character key = adj.vertax;
				inDegree.put(key, inDegree.get(key) + 1);
			}
		}

		PriorityQueue<Character> q = new PriorityQueue<Character>();

		for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
			Character c = (Character) entry.getKey();
			Integer degree = (Integer) entry.getValue();
			if (degree == 0) {
				q.add(c);
			}
		}

		StringBuilder sb = new StringBuilder();

		while (!q.isEmpty()) {
			Character c = q.poll();
			sb.append(c);
			Graph peek = nodeMap.get(c);
			for (Graph g : peek.adj) {
				int degree = inDegree.get(g.vertax);
				inDegree.put(g.vertax, degree - 1);
				if (degree - 1 == 0) {
					q.add(g.vertax);
				}
			}
		}

		if (sb.length() != inDegree.size()) {
			return "";
		}

		return sb.toString();
	}

	public String alienOrder(String[] words) {
		// Write your code

		Map<Character, Graph> nodeMap = new HashMap<Character, Graph>();

		for (int j = 0; j < words.length; j++) {

			char arr[] = words[j].toCharArray();
			for (char c : arr) {
				if (nodeMap.get(c) == null) {
					Graph g = new Graph(c);
					nodeMap.put(c, g);
				}

			}
		}

		for (int j = 0; j < words.length - 1; j++) {

			char[] a = words[j].toCharArray();
			char[] b = words[j + 1].toCharArray();

			int i = 0;

			while (a[i] == b[i] && i < Math.min(a.length, b.length)) {
				i++;
			}

			Graph aGraph = null, bGraph = null;
			aGraph = nodeMap.get(a[i]);
			bGraph = nodeMap.get(b[i]);
			aGraph.addEdge(bGraph);
		}

		return topoSort(nodeMap);
	}
}
