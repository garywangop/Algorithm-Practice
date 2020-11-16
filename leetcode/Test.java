package leetcode;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(Test.checkPossibility(new int[]{3,4,2,3}));
	}
	
	public static boolean checkPossibility(int[] nums) {
		 boolean flag = false;
		 int num = nums[0];
		 for (int i = 1; i < nums.length; i++) {
			 if (nums[i] < nums[i - 1]) {
				 if (flag) {
					 return false;
				 } else {
					 flag = true;
					 
				 }
			 }
		 }
		 return true;
	}
}
