package review;

import java.util.List;
import java.util.ArrayList;

public class MissingRanges {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * 491. Missing Ranges
	 * 
	 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
	 */
	
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
	    List<String> result = new ArrayList<>();
	    int start = lower;        
	 
	    if(lower==Integer.MAX_VALUE){
	        return result;
	    }
	 
	    for(int i=0; i<nums.length; i++){
	        //handle duplicates, e.g., [1,1,1] lower=1 upper=1
	        if(i<nums.length-1 && nums[i]==nums[i+1]){ 
	            continue;
	        }
	 
	        if(nums[i] == start){
	            start++;
	        }else{
	            result.add(getRange(start, nums[i]-1));
	            if(nums[i]==Integer.MAX_VALUE){
	                return result;
	            }
	            start = nums[i]+1;
	        }            
	    }
	 
	    if(start<=upper){
	        result.add(getRange(start, upper));
	    }
	 
	    return result;
	}
	 
	private String getRange(int n1, int n2) {
	    return n1 == n2 ? String.valueOf(n1) : String.format("%d->%d" , n1, n2);
	}
}
