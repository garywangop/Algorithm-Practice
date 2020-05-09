package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import solution.ListNode;

public class Test {

	public static void main(String[] args) {
		Test sol = new Test();
		/*
		 * {{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}}}
		 */
		//String{}{} test = new String{}{} {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		int[][] arr = new int[][] {{-7,-3},{-7,-1},{-2,-2},{0,-8},{2,-2},{5,-6},{5,-5},{1,7}};
		
		System.out.println(sol.checkStraightLine(arr));
		
		
	}
	
	public boolean checkStraightLine(int[][] coordinates) {
		int slope = 0;
		if (coordinates[0][0] - coordinates[1][0] == 0) {
			slope = Integer.MAX_VALUE;
		} else {
			slope = (coordinates[0][1] - coordinates[1][1]) / (coordinates[0][0] - coordinates[1][0]);
		}
        
        for (int i = 2; i < coordinates.length; i++) {
        	if (slope == Integer.MAX_VALUE) {
        		if (coordinates[i][0] != coordinates[i - 1][0]) {
        			return false;
        		}
        	} else {
        		if (coordinates[i][1] - coordinates[i - 1][1] != slope * (coordinates[i][0] - coordinates[i - 1][0])) {
                    return false;
                }
        	}
            
        }
        return true;
    }

}
