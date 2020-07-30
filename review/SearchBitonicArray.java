package review;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SearchBitonicArray {
	
	@Test
	public void test1() {
		SearchBitonicArray sol = new SearchBitonicArray();
		assertEquals(5, sol.search(new int[] {1, 4, 7, 11, 6, 2, -3, -8}, 2));
		
	}
	
	@Test
	public void test2() {
		SearchBitonicArray sol = new SearchBitonicArray();
		assertEquals(3, sol.search(new int[] {0,1,6,9,10,3,2,-2,-4,-5}, 9));
	}
	
	public int search(int[] arr, int target) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int peak = findPeak(arr);
		int res = -1;
		
		if (arr[0] < target) {
			res = search(arr, target, peak, true);
			if (res != -1) {
				return res;
			}
		}
		
		if (arr[arr.length - 1] < target) {
			res = search(arr, target, peak, false);
			if (res != -1) {
				return res;
			}
		}
		
		return -1;
	}
	
	private int search(int[] arr, int target, int index, boolean direction) {
		int left = direction ? 0 : index;
		int right = direction ? index : arr.length - 1;
		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] > target) {
				if (direction) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			} else {
				if (direction) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		
		return -1;
	}
	
	private int findPeak(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (left == mid) {
				return arr[left] > arr[right] ? left : right;
			} else if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return mid;
			} else if (arr[mid] > arr[mid + 1]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
