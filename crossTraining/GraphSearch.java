package crossTraining;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

public class GraphSearch {
	public static void main(String args[]) {

	}

	/*
	 * 194. Kth Closest Point To <0,0,0> Given three arrays sorted in ascending
	 * order. Pull one number from each array to form a coordinate <x,y,z> in a 3D
	 * space. Find the coordinates of the points that is k-th closest to <0,0,0>.
	 * 
	 * We are using euclidean distance here.
	 * 
	 * Assumptions
	 * 
	 * The three given arrays are not null or empty, containing only non-negative
	 * numbers K >= 1 and K <= a.length * b.length * c.length Return
	 * 
	 * a size 3 integer list, the first element should be from the first array, the
	 * second element should be from the second array and the third should be from
	 * the third array Examples
	 * 
	 * A = {1, 3, 5}, B = {2, 4}, C = {3, 6}
	 * 
	 * The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)
	 * 
	 * The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)
	 */
	public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
		PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(2 * k, new Comparator<List<Integer>>() {
			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				long d1 = distance(o1, a, b, c);
				long d2 = distance(o2, a, b, c);
				if (d1 == d2) {
					return 0;
				}
				return d1 < d2 ? -1 : 1;
			}
		});
		// Note that List's equal() method has been already overriden,
		// and it is comparing the actual elements in the List.
		Set<List<Integer>> visited = new HashSet<>();
		// The initial state is <0, 0, 0>, meaning picking the smallest elements from
		// the three arrays.
		List<Integer> cur = Arrays.asList(0, 0, 0);
		visited.add(cur);
		minHeap.offer(cur);
		while (k > 0) {
			cur = minHeap.poll();
			List<Integer> n = Arrays.asList(cur.get(0) + 1, cur.get(1), cur.get(2));
			if (n.get(0) < a.length && visited.add(n)) {
				minHeap.offer(n);
			}
			n = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2));
			if (n.get(1) < b.length && visited.add(n)) {
				minHeap.offer(n);
			}
			n = Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
			if (n.get(2) < c.length && visited.add(n)) {
				minHeap.offer(n);
			}
			k--;
		}
		// At last, we replace the index with actual values in a, b, c.
		cur.set(0, a[cur.get(0)]);
		cur.set(1, b[cur.get(1)]);
		cur.set(2, c[cur.get(2)]);
		return cur;
	}

	private long distance(List<Integer> point, int[] a, int[] b, int[] c) {
		long dis = 0;
		dis += a[point.get(0)] * a[point.get(0)];
		dis += b[point.get(1)] * b[point.get(1)];
		dis += c[point.get(2)] * c[point.get(2)];
		return dis;
	}

	/*
	 * 195. Place To Put The Chair I Given a gym with k pieces of equipment and some
	 * obstacles. We bought a chair and wanted to put this chair into the gym such
	 * that the sum of the shortest path cost from the chair to the k pieces of
	 * equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a
	 * cell with equipment, ‘O’ denotes a cell with an obstacle, 'C' denotes a cell
	 * without any equipment or obstacle. You can only move to neighboring cells
	 * (left, right, up, down) if the neighboring cell is not an obstacle. The cost
	 * of moving from one cell to its neighbor is 1. You can not put the chair on a
	 * cell with equipment or obstacle.
	 * 
	 * Assumptions
	 * 
	 * There is at least one equipment in the gym The given gym is represented by a
	 * char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be
	 * not null It is guaranteed that each 'C' cell is reachable from all 'E' cells.
	 * If there does not exist such place to put the chair, just return {-1, -1}
	 * Examples
	 * 
	 * { { 'E', 'O', 'C' },
	 * 
	 * { 'C', 'E', 'C' },
	 * 
	 * { 'C', 'C', 'C' } }
	 * 
	 * we should put the chair at (1, 0), so that the sum of cost from the chair to
	 * the two equipment is 1 + 1 = 2, which is minimal.
	 */
	private static final char EQUIP = 'E';
	  private static final char OB = 'O';

	  public List<Integer> putChair(char[][] gym) {
	    // Assumptions: gym is not null, has size M * N,
	    // where M >= 1 and N >= 1
	    // return null if you can not put the chair anywhere.
	    // There is at least one equipment in the gym.
	    int M = gym.length;
	    int N = gym[0].length;
	    // Use a matrix to record the sum of shortest path cost
	    // from each cell to all the 'E' cells.
	    int[][] cost = new int[M][N];
	    List<Integer> res = Arrays.asList(-1, -1);
	    for (int i = 0; i < M; i++) {
	      for (int j = 0; j < N; j++) {
	        if (EQUIP == gym[i][j]) {
	          // Use BFS to calculate the shortest path cost from
	          // each of the quipments to all the other reachable cells
	          // and add the cost to each corresponding cell.
	          // Note the return boolean value represents if there exists
	          // another 'E' cell not reachable from the current one,
	          // if so, there won't exits a cell to place the chair.
	          if (!addCost(cost, gym, i, j)) {
	            return res;
	          }
	        }
	      }
	    }
	    // Find the cell with smallest sum of shorted path costs
	    // to all the 'E' cells.
	    for (int i = 0; i < M; i++) {
	      for (int j = 0; j < N; j++) {
	        if (EQUIP != gym[i][j] && OB != gym[i][j]) {
	          if (res.get(0) == -1 && res.get(0) == -1) {
	            res = Arrays.asList(i, j);
	          } else if (cost[i][j] < cost[res.get(0)][res.get(1)]) {
	            res.set(0, i);
	            res.set(1, j);
	          }
	        }
	      }
	    }
	    return res;
	  }

	  private boolean addCost(int[][] cost, char[][] gym, int i, int j) {
	    // Use a boolean matrix to make sure each cell will be visited
	    // no more than once.
	    boolean[][] visited = new boolean[gym.length][gym[0].length];
	    // Breadth-First-Search, record the current path cost.
	    int pathCost = 1;
	    Queue<Pair> queue = new ArrayDeque<>();
	    visited[i][j] = true;
	    queue.offer(new Pair(i, j));
	    while (!queue.isEmpty()) {
	      int size = queue.size();
	      for (int l = 0; l < size; l++) {
	        Pair cur = queue.poll();
	        List<Pair> neis = getNeis(cur, gym);
	        for (Pair nei : neis) {
	          if (!visited[nei.i][nei.j]) {
	            visited[nei.i][nei.j] = true;
	            cost[nei.i][nei.j] += pathCost;
	            queue.offer(nei);
	          }
	        }
	      }
	      // Advance the pathCost by 1 for each level.
	      pathCost++;
	    }
	    // If there exists another 'E' cell not reachable from
	    // the path start 'E' cell, we return false.
	    for (int l = 0; l < gym.length; l++) {
	      for (int m = 0; m < gym[0].length; m++) {
	        if (!visited[l][m] && EQUIP == gym[l][m]) {
	          return false;
	        }
	      }
	    }
	    return true;
	  }

	  private List<Pair> getNeis(Pair cur, char[][] gym) {
	    int x = cur.i;
	    int y = cur.j;
	    int M = gym.length;
	    int N = gym[0].length;
	    List<Pair> neis = new ArrayList<>();
	    if (x + 1 < M && OB != gym[x + 1][y]) {
	      neis.add(new Pair(x + 1, y));
	    }
	    if (y + 1 < N && OB != gym[x][y + 1]) {
	      neis.add(new Pair(x, y + 1));
	    }
	    if (x - 1 >= 0 && OB != gym[x - 1][y]) {
	      neis.add(new Pair(x - 1, y));
	    }
	    if (y - 1 >= 0 && OB != gym[x][y - 1]) {
	      neis.add(new Pair(x, y - 1));
	    }
	    return neis;
	  }

	  static class Pair {
	    int i;
	    int j;
	    Pair(int i, int j) {
	      this.i = i;
	      this.j = j;
	    }
	  }
	
}
