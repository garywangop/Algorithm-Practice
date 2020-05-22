package review;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms {

	
	public boolean canAttendMeetings(int[][] intervals) {
	    if (intervals == null || intervals.length <= 1) {
	      return true;
	    }
	    Arrays.sort(intervals, new Comparator<int[]>() {
	    	@Override
	    	public int compare(int[] e1, int[] e2) {
	    		if (e1[0] == e2[0]) {
	    			return 0;
	    		}
	    		return e1[0] < e2[0] ? -1 : 1;
	    	}
	    });
	    
	    for (int i = 1; i < intervals.length; i++) {
	      if (intervals[i][0] < intervals[i - 1][1]) {
	        return false;
	      }
	    }
	    return true;
	  }
	
//	  class myComparator implements Comparator<int[]> {
//	    @Override
//	    public int compare (int[] a1, int[] a2) {
//	      if (a1[0] == a2[0]) {
//	        return 0;
//	      }
//	      return a1[0] < a2[0] ? -1 : 1;
//	    }
//	  }
	
	public int minMeetingRooms(int[][] arr) {
	    if (arr == null || arr.length == 0) {
	      return 0;
	    }
	    Arrays.sort(arr, new Comparator<int[]>(){
	      @Override
	      public int compare(int[] e1, int[] e2) {
	        if (e1[0] == e2[0]) {
	          return 0;
	        }
	        return e1[0] < e2[0] ? -1 : 1;
	      }
	    });

	    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	    minHeap.offer(arr[0][1]);

	    for (int i = 1; i < arr.length; i++) {
	      if (arr[i][0] >= minHeap.peek()) {
	        minHeap.poll();
	      }
	      minHeap.offer(arr[i][1]);
	    }
	    return minHeap.size();
	  }
}
