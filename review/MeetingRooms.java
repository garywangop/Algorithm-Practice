package review;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class MeetingRooms {
	
	public static void main(String[] args) {
		MeetingRooms sol = new MeetingRooms();
		int[][] arr = new int[][] {{1,3},{2,6},{8,10},{15,18}};
		int[][] res = sol.merge(arr);
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i][0] + ","+res[i][1]);
		}
	}

	
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
	
	/*
	 * 56. Merge Intervals
	 */
	public int[][] merge(int[][] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
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
        List<int[]> res = new ArrayList<>();
        int[] temp = arr[0];
        res.add(temp);
        for (int i = 1; i < arr.length; i++) {
            if (temp[1] >= arr[i][0]) {
                temp[1] = Math.max(arr[i][1], temp[1]);
            } else {
                temp = arr[i];
                res.add(temp);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
