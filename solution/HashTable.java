package solution;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class HashTable {
	public static void main(String[] args) {
		// Q1 test:
		HashMap<Integer, Integer> Q1 = new HashMap<>();
		Q1.put(1, 1);
		Set<Map.Entry<Integer, Integer>> res1 = Q1.entrySet();
		String[] Q1String = {"ad", "ad", "bc", "bc", "bc", "ca", "ca"};
		String[] res = Q1.topKFrequent(Q1String, 2);
		for (String i : res) {
			System.out.println(i);
		}
		String s = "abc";
		System.out.println(s.length());
		
	}
	public int miss (int[] arr) {
		
		if (arr.length == 0) {
		      return 0;
		    }
		    int res = 0, sum = 0;
		    for (int i = 0; i <= arr.length; i++) {
		      res += i + 1;
		    }

		    for (int i = 0; i < arr.length; i++) {
		      sum += arr[i];
		    }
		    return res - sum;
	}
	
	// Q1: top K grequent words
	public String[] topKFrequent(String[] combo, int k) {
		if (combo.length == 0) {
			return new String[0];
		}
		Map<String, Integer> freqMap = getFreqMap(combo);
		PriorityQueue<Map.Entry<String, Integer>> minHeap = 
				new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>(){
			@ Override
			public int compare
			(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
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
	private Map<String, Integer> getFreqMap(String[] combo) {
		Map<String, Integer> freqMap = new HashMap<>();
		for (String s : combo) {
			Integer freq = freqMap.get(s);
			if (freq == null) {
				freqMap.put(s, 1);
			} else {
				freqMap.put(s, freq + 1);
			}
		}
		return freqMap;
	}
	private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
		String[] res = new String[minHeap.size()];
		for (int i = minHeap.size() - 1; i >= 0; i--) {
			res[i] = minHeap.poll().getKey();
		}
		return res;
	}
	
}
