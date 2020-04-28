package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import solution.ListNode;

public class Test {

	public static void main(String[] args) {
		Test sol = new Test();
		/*
		 * [[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]]
		 */
		//String[][] test = new String[][] {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		
		char[] s = new char[] {'a','b','c','d'};
		sol.reverseString(s);
		System.out.println(s);
	}
	
	public void reverseString(char[] s) {
        helper(s, 0, s.length - 1);
    }
    private void helper(char[] s, int left, int right) {
        if (s == null || left >= right) {
            return;
        }
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        helper(s, ++left, --right);
    }

}
