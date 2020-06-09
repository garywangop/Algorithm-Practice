package review;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class MeetingRooms {

	public static void main(String[] args) {
		MeetingRooms sol = new MeetingRooms();
		int[][] arr = new int[][] { {12,15},{3,15},{7,11},{13,16},{4,9},{15,16},{12,14},{7,18},{10,17},{1,3},{9,13},{2,15} };

		System.out.println(sol.maximumMeetings(arr));
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
		Arrays.sort(arr, new Comparator<int[]>() {
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
		Arrays.sort(arr, new Comparator<int[]>() {
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

	public int maxNumber(int[][] time) {
		if (time == null) {
			return Integer.MIN_VALUE;
		}
		int[] res = time[0];
		for (int i = 1; i < time.length; i++) {
			if (time[1][0] > res[1]) {
				return Integer.MIN_VALUE;
			}
			res[0] = Math.max(res[0], time[i][0]);
			res[1] = Math.min(res[1], time[i][1]);
		}
		return res[1];
	}

	/*
	 * LaiCode: 599
	 * 
	 * Duration of a meeting could be represented as a time interval using an array
	 * [s, e] (s < e) where s means start time and e mean end time.
	 * 
	 * Given a list of meeting time intervals[[s0, e0],[s1, e1]......], return the
	 * maximum number of meetings a person could attend. A person could attend two
	 * meetings [si, ei] and [sj, ej] only when ei < sj.
	 * 
	 * Example:
	 * 
	 * Input = [[1,2],[2,3],[3,4],[4,5]]
	 * 
	 * Output = 2
	 * 
	 * Explanation: The person could attend two meetings either [[1,2], [3,4]] or
	 * [[2,3], [4,5].
	 */
	public int maximumMeetings(int[][] intervals) {
	    if (intervals == null || intervals.length == 0) {
	      return 0;
	    }

	    Arrays.sort(intervals, new Comparator<int[]>(){
	      @Override
	      public int compare(int[] e1, int[] e2) {
	        if (e1[0] == e2[0]) {
	          return 0;
	        }
	        return e1[0] < e2[0] ? -1 : 1;
	      }
	    });

	    PriorityQueue<Cell> minHeap = new PriorityQueue<>(new Comparator<Cell>() {
	      @Override
	      public int compare(Cell c1, Cell c2) {
	        if (c1.end == c2.end) {
	          return 0;
	        }
	        return c1.end < c2.end ? -1 : 1;
	      }
	    });

	    minHeap.offer(new Cell(intervals[0][0], intervals[0][1], 1));
	    int res = 1;
	    for (int i = 1; i < intervals.length; i++) {
	      if (intervals[i][0] > minHeap.peek().end) {
	        Cell cur = minHeap.poll();
	        int curMeeting = cur.meeting;
	        minHeap.offer(new Cell(intervals[i][0], intervals[i][1], curMeeting + 1));
	        res = Math.max(res, curMeeting + 1);
	      } else {
	        minHeap.offer(new Cell(intervals[i][0], intervals[i][1], 1));
	      }
	    }
	    return res;
	  }

	  static class Cell {
	    int start;
	    int end;
	    int meeting;
	    public Cell(int start, int end, int meeting) {
	      this.start = start;
	      this.end = end;
	      this.meeting = meeting;
	    }
	  }
}
