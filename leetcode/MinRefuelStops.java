package leetcode;

import java.util.*;

public class MinRefuelStops {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinRefuelStops sol = new MinRefuelStops();
		int[][] stations1 = new int[][] {{10,60}, {20, 30}, {30,30},{60,40}};
		int[][] stations2 = new int[][] {{10,100}};
		int[][] stations3 = new int[][] {{50,50}};
		int[][] stations4 = new int[][] {{10,100}};
		int[][] stations5 = new int[][] {};
		int[][] stations6 = new int[][] {{25,27},{36,187},{140,186},{378,6},{492,202},{517,89},{579,234},{673,86},{808,53},{954,49}};
		
		
		System.out.println("shoule be: 2, " + sol.minRefuelStops(100, 10, stations1));
		System.out.println("shoule be: -1, " + sol.minRefuelStops(100, 1, stations2));
		System.out.println("shoule be: 1, " + sol.minRefuelStops(100, 50, stations3));
		System.out.println("shoule be: -1, " + sol.minRefuelStops(100, 1, stations4));
		System.out.println("shoule be: -1, " + sol.minRefuelStops(100, 1, stations5));
		System.out.println("shoule be: -1, " + sol.minRefuelStops(1000, 83, stations6));
	}
	
	// 871
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		Queue<Integer> queue = new PriorityQueue<>();
        long dist = startFuel;
        int res = 0;
        int idx = 0;
        while (true) {
            while (idx < stations.length && stations[idx][0] <= dist) {
                queue.offer(-stations[idx][1]);
                idx++;
            }
            
            if (dist >= target) return res;
            if (queue.isEmpty()) return -1;
            dist += -queue.poll();
            res++;
        }
    }

}
