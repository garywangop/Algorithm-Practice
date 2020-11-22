package leetcode;

import java.util.*;

public class PrisonAfterNDays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] cells = new int[] {1,0,0,1,0,0,1,0};
		/*int[] res = PrisonAfterNDays.prisonAfterNDays(cells, 1000000000);
		for (int i : res) {
			System.out.print(i + " ");
		}
		*/
		// System.out.println("0,0,1,1,1,1,1,0");
		PrisonAfterNDays sol = new PrisonAfterNDays();
		int[] res = sol.prisonAfterNDays(cells, 1000000000);
		for (int i : res) {
			System.out.print(i + " ");
		}
	}
	
	// 957
	public int[] prisonAfterNDays(int[] cells, int N) {
		int cycle = 0;
		boolean hasCycle = false;
		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			int[] next = nextDay(cells);
			if (set.contains(Arrays.toString(next))) {
				hasCycle = true;
				break;
			}
			set.add(Arrays.toString(next));
			cycle++;
			cells = next;
		}
		System.out.println(cycle);
		
		if (hasCycle) {
			N %= cycle;
			for (int i = 0; i < N; i++) {
				cells = nextDay(cells);
			}
		}
		return cells;
		
    }
	
	private int[] nextDay(int[] cells) {
		int[] res = new int[cells.length];
		for (int i = 0; i < cells.length; i++) {
			if (i == 0 || i == cells.length - 1) {
				res[i] = 0;
			} else {
				if (cells[i - 1] == cells[i + 1]) {
					res[i] = 1;
				} else {
					res[i] = 0;
				}
			}
		}
		return res;
	}

}
