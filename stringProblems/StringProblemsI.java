package stringProblems;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class StringProblemsI {
	public static void main(String[] args) {
		StringProblemsI r = new StringProblemsI();
		String[] input1 = {"Hello", "World"};
		System.out.print(input1[1]);
		
		
	}
	
	// Remove extra space characters
	public String removeSpace(String input) {
		if (input.isEmpty()) {
			return input;
		}
		char[] arr = input.toCharArray();
		int s = 0;
		for (int f = 0; f < arr.length; f++) {
			if (arr[f] != ' ' || (s != 0 && arr[s - 1] != 0)) {
				arr[s] = arr[f];
				s++;
			}
		}
		/**
		for (int f = 0; f < arr.length; f++) {
			if (arr[f] == ' ' && (s == 0 || arr[s - 1] == ' ')) {
				continue;
			}
			arr[s] = arr[f];
			s++;
		}
		**/
		/**
		for (int f = 0; f < arr.length; f++) {
			if (arr[f] == ' ') {
				if (s == 0) {
					continue;
				} else { // s != 0
					if (arr[s - 1] != ' ') {
						arr[s] = arr[f];
						s++;
					} else {
						continue;
					}
				}
			} else { // arr[f] != ' '
				arr[s] = arr[f];
				s++;
			}
		}
		**/
		// Post-processing:
	    if (s > 0 && arr[s - 1] == ' ') {
	      s--;
	    }
		return new String(arr, 0, s);
	}
	
	// Remove Adjacent Repeated Characters IV
	// eg, "abbbaaccz" → "aaaccz" → "ccz" → "z"
	public String removeDuplicateIV(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] arr = input.toCharArray();
		Deque<Character> stack = new ArrayDeque<>();
		/**
		int fast = 0;
		while (fast < arr.length) {
			char cur = arr[fast];
			if (!stack.isEmpty() && stack.peekFirst() == cur) {
				while (fast < arr.length && arr[fast] == stack.peekFirst()) {
					fast++;
				}
				stack.pollFirst();
			} else {
				stack.offerFirst(cur);
				fast++;
			}
		}
		**/
		
		for (int i = 0; i < arr.length; i++) {
			char cur = arr[i];
			if (!stack.isEmpty() && cur == stack.peekFirst()) {
				while (i < arr.length && arr[i] == stack.peekFirst()) {
					i++;
				}
				stack.pollFirst();
				i--;
			} else {
				stack.offerFirst(cur);
			}
		}
		
		int size = stack.size();
		for (int i = stack.size() - 1; i >= 0; i--) {
			arr[i] = stack.pollFirst();
		}
		return new String(arr, 0, size);
		
	}
	
	// Determine If One String Is Another's Substring
	public int strstr(String large, String small) {
		if (small.length() == 0) {
			return 0;
		}
		if (large.length() < small.length()) {
			return -1;
		}
		for (int i = 0; i <= large.length() - small.length(); i++) {
			if (equals(large, i, small)) {
				return i;
			}
		}
		return -1;
	}
	private boolean equals(String large, int start, String small) {
		for (int i = 0; i < small.length(); i++) {
			if (large.charAt(i + start) != small.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	// Top K frequent words
	public String[] topKFrequent(String[] combo, int k) {
		if (combo.length == 0) {
			return new String[0];
		}
		Map<String, Integer> freqMap = getFreq(combo);
		PriorityQueue<Map.Entry<String, Integer>> minHeap = 
				new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare (Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		});
		for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(entry);
			} else if (entry.getValue() > minHeap.peek().getValue()) {
				minHeap.poll();
				minHeap.offer(entry);
			}
		}
		return freqArray(minHeap);
	}
	
	private Map<String, Integer> getFreq(String[] combo) {
		Map<String, Integer> res = new HashMap<>();
		for (String s : combo) {
			Integer count = res.get(s);
			if (count == null) {
				res.put(s, 1);
			} else {
				res.put(s, count + 1);
			}
		}
		return res;
	}
	
	private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
		String[] res = new String[minHeap.size()];
		for (int i = minHeap.size() - 1; i >= 0; i--) {
			res[i] = minHeap.poll().getKey();
		}
		return res;
	}
	
}

























