package solution;
import java.util.*;


public class QuickAndRainbowSort {
	
	public static void main(String args[]) {
		int[] quickSortArray = {3, 5, 7, 13, 4, 2, 16};
		int[] rainbowSort = {0, 0, 0, -1, 1, -1, 1, 0};
		System.out.println(Arrays.toString(quickSort(quickSortArray)));
		System.out.println(Arrays.toString(rainbowSort(rainbowSort)));

	}
	
	public static int[] quickSort(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		
		quickSort(array, 0, array.length - 1);
		return array;
	}
	
	private static void quickSort(int[] array, int left, int right) {
		if (left >= right) { //写==的话，int pivotIndex = left + rand.nextInt(right - left + 1)会报错
			return;
		}
		Random rand = new Random();
		int pivotIndex = left + rand.nextInt(right - left + 1);
		int pivot = array[pivotIndex];
		swap(array, pivotIndex, right);
		
		int leftBound = left, rightBound = right - 1;
		while (leftBound <= rightBound) {
			if (array[leftBound] < pivot) {
				leftBound++;
			} else {
				swap(array, leftBound, rightBound);
				rightBound--;
			}
		}
		swap(array, leftBound, right);
		
		quickSort(array, left, leftBound - 1);
		quickSort(array, leftBound + 1, right);
	}
	
	private static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static int[] rainbowSort(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		int i = 0, j = 0, k = array.length - 1;
		while (j <= k) {
			if (array[j] == -1) {
				swap(array, i, j);
				i++;
				j++;
			} else if (array[j] == 0) {
				j++;
			} else {
				swap(array, j, k);
				k--;
			}
		}
		return array;
	}
}






