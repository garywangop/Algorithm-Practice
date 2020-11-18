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
		int[][] stations7 = new int[][] {{47,220},{65,1},{98,113},{126,196},{186,218},{320,205},{686,317},{707,325},{754,104},{781,105}};
		int[][] stations8 = new int[][] {{25,25},{50,25},{75,25}};
		
		System.out.println("shoule be: 2, " + sol.minRefuelStops(100, 10, stations1));
		System.out.println("shoule be: -1, " + sol.minRefuelStops(100, 1, stations2));
		System.out.println("shoule be: 1, " + sol.minRefuelStops(100, 50, stations3));
		System.out.println("shoule be: -1, " + sol.minRefuelStops(100, 1, stations4));
		System.out.println("shoule be: -1, " + sol.minRefuelStops(100, 1, stations5));
		System.out.println("shoule be: -1, " + sol.minRefuelStops(1000, 83, stations6));
		System.out.println("shoule be: 4, " + sol.minRefuelStops(1000, 83, stations7));
		System.out.println("shoule be: 3, " + sol.minRefuelStops(100, 25, stations8));
	}
	
	// 871
	
	// 这是LC上别人写的答案，短小精悍贼牛逼
	public int minRefuelStops2(int target, int startFuel, int[][] stations) {
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
	
	// 自己的土办法，难看但是想法清晰
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		if (startFuel >= target) {
            return 0;
        }
        
        if (stations == null || stations.length == 0 || startFuel < stations[0][0]) {
            return -1;
        }
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int distance = stations[0][0];
        startFuel -= stations[0][0];
        maxHeap.offer(stations[0][1]);
        for (int i = 1; i < stations.length; i++) {
            distance = stations[i][0];
            startFuel -= stations[i][0] - stations[i - 1][0];
            
            while (startFuel < 0) {
                if (maxHeap.isEmpty()) {
                    return -1;
                }
                startFuel += maxHeap.poll();
            }
            
            if (distance + startFuel >= target) {
                return i - maxHeap.size();
            }
            maxHeap.offer(stations[i][1]);
        }
        
        distance += startFuel;
        
        while (distance < target) {
            if (maxHeap.isEmpty()) {
                return -1;
            }
            distance += maxHeap.poll();
        }
        
        return stations.length - maxHeap.size();
	}

}
