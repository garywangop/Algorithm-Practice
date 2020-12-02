package leetcode;

import java.util.*;

public class FindMinArrowShots {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindMinArrowShots sol = new FindMinArrowShots();
		
		
		int[][] points1 = new int[][] {{-2147483646,-2147483645},{2147483646,2147483647}};
		int[][] points2 = new int[][] {{10,16},{2,8},{1,6},{7,12}};
		int[][] points3 = new int[][] {{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};
		
		//System.out.println(sol.findMinArrowShots(points1));
		//System.out.println(sol.findMinArrowShots(points2));
		System.out.println(sol.findMinArrowShots1(points3));
		System.out.println(sol.findMinArrowShots2(points3));
	}
	
	// 452
	// Method 1: sort
	public int findMinArrowShots1(int[][] points) {
		if (points.length == 0) {
			return 0;
		}
		//Arrays.sort(points, (e1, e2) -> (e1[0] - e2[0]));
		Arrays.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] e1, int[] e2) {
				if (e1[0] == e2[0]) {
					return 0;
				}
				return e1[0] < e2[0] ? -1 : 1;
			}
		});
		
		int res = 1;
		int end = points[0][1];
		for (int i = 1; i < points.length; i++) {
			int[] cur = points[i];
			if (cur[0] > end) {
				res++;
				end = cur[1];
			} else {
				end = Math.min(end, cur[1]);
			}
		}
		return res;
	}
	
	// Method 2: sort + pq
	public int findMinArrowShots2(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        //Arrays.sort(points, (e1, e2) -> e1[0] - e2[0]);
        //PriorityQueue<int[]> maxHeap = new PriorityQueue<>((e1, e2) -> e2[1] - e1[1]);
        Arrays.sort(points, new MyComparator1());
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new MyComparator2());
        maxHeap.offer(points[0]);
        for (int i = 1; i < points.length; i++) {
            int[] prev = maxHeap.peek();
            int[] cur = points[i];
            if (cur[0] > prev[1]) {
                maxHeap.offer(cur);
            } else {
                maxHeap.poll();
                maxHeap.offer(new int[]{cur[0], Math.min(prev[1], cur[1])});
            }
        }
        return maxHeap.size();
    }
	
	class MyComparator1 implements Comparator<int[]> {
		@Override
		public int compare(int[] e1, int[] e2) {
			if (e1[0] == e2[0]) {
				return 0;
			}
			return e1[0] < e2[0] ? -1 : 1; 
		}
	}
	
	class MyComparator2 implements Comparator<int[]> {
		@Override
		public int compare(int[] e1, int[] e2) {
			if (e1[1] == e2[1]) {
				return 0;
			}
			return e1[1] > e2[0] ? -1 : 1;
		}
	}

}
