package review;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;

public class ReconstructItinerary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReconstructItinerary sol = new ReconstructItinerary();
		String[][] test = new String[][] {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		System.out.println(sol.findItinerary(test));
	}

//	public List<String> findItinerary(String[][] tickets) {
//	    List<String> res = new ArrayList<>();
//		    Map<String, PriorityQueue<String>> map = new HashMap<>();
//		    // Add all the tickets to map
//		    for (int i = 0; i < tickets.length; i++) {
//		    	if (!map.containsKey(tickets[i][0])) {
//		    		map.put(tickets[i][0], new PriorityQueue<String>());
//		    	}
//		    	map.get(tickets[i][0]).offer(tickets[i][1]);
//		    }
////		    for(String[] t : tickets) {
////		        if(!map.containsKey(t[0])) map.put(t[0], new PriorityQueue<String>());
////		        map.get(t[0]).offer(t[1]);
////		    }
//		    res.add("JFK");
//		    while (!map.isEmpty() && map.get(res.get(res.size() - 1)) != null && !map.get(res.get(res.size() - 1)).isEmpty()) {
//		      //String cur = map.get(res.get(res.size() - 1)).poll();
//		    	PriorityQueue<String> cur = map.get(res.get(res.size() - 1));
//		    	String s = cur.poll();
//		    	if (s != null) {
//		    		res.add(s);
//		    	}
//		    }
//		    return res;
//	  }
	public List<String> findItinerary(String[][] tickets) {
	    List<String> res = new ArrayList<>();
	    Map<String, PriorityQueue<String>> map = new HashMap<>();
	    for (String[] s : tickets) {
	      if (!map.containsKey(s[0])) {
	        map.put(s[0], new PriorityQueue<String>());
	      }
	      map.get(s[0]).offer(s[1]);
	    }
	    res.add("JFK");
	    dfs(res, tickets.length, map, 0);
	    return res;
	  }

	  private void dfs(List<String> res, int row, Map<String, PriorityQueue<String>> map, int level) {
	    if (level == row) {
	      return;
	    }
	    String cur = res.get(res.size() - 1);
	    String s = map.get(cur).poll();
	    res.add(s);
	    dfs(res, row, map, level + 1);
	    res.remove(res.size() - 1);
	    map.get(cur).offer(s);
	  }
}
