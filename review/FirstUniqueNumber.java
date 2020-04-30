package review;

import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.ArrayDeque;


public class FirstUniqueNumber {

	public static void main(String[] args) {
		int[] nums = new int[] {2,3,5};
		FirstUniqueNumber sol = new FirstUniqueNumber(nums);
		System.out.println(sol.showFirstUnique());
		sol.add(5);
		System.out.println(sol.showFirstUnique());
		sol.add(2);
		System.out.println(sol.showFirstUnique());
	}
	// K: number, V: count of number
    Map<Integer, Integer> map;
    Queue<Integer> q;
    
	public FirstUniqueNumber(int[] nums) {
        map = new HashMap<>();
        q = new ArrayDeque<>();
        for (int i : nums) {
            Integer count = map.get(i);
            if (count == null) {
                q.offer(i);
                map.put(i, 1);
            } else {
                map.put(i, ++count);
            }
        }
    }
    
    public int showFirstUnique() {
        while (!q.isEmpty() && map.get(q.peek()) != 1) {
            q.poll();
        }
        return q.isEmpty() ? -1 : q.peek();
    }
    
    public void add(int value) {
        Integer cur = map.get(value);
        if (cur == null) {
            q.offer(value);
            map.put(value, 1);
        } else {
            map.put(value, ++cur);
        }
    }
}
