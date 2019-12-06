package solution;


public class Sorting_SelectionAndMerge {
	
	public static void main (String[] args) {
		int[] arr = {3, 5, 1, 2, 4, 8};
		int[] result = mergeSort(arr);
		for (int x : result) {
			System.out.println(x);
		}
	}
	
	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int i = 0; i < arr.length - 1; i++) { 
			//out loop: the start of the unsorted area
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				//inner loop: find the min index from the rest elements
				if (arr[j] < arr[minIndex]) {
					//record the index of smallest element
					minIndex = j;
				}
				
			}
			swap(arr, i, minIndex);
		}
	}

	//Swap the minIndex with arr[i]
	private static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	public static int[] mergeSort(int[] array) {
		//Check null array first.
		if(array == null || array.length == 0) {
			return array;
		}
		return mergeSort(array, 0, array.length -1);
	}
	
	private static int[] mergeSort(int[] array, int left, int right) {
		//recursion base case
		if (left == right) {
			//base case has only one element
			return new int[]{array[left]};
		}
		//if array is not base case, start recursion
		int mid = left + (right - left)/2;
		int[] leftResult = mergeSort(array, left, mid);
		int[] rightResult = mergeSort(array, mid + 1, right);
		return merge(leftResult, rightResult);
	}
	
	private static int[] merge(int[] leftResult, int[] rightResult) {
		int[] result = new int[leftResult.length  + rightResult.length];
		int leftIndex = 0, rightIndex = 0, resultIndex = 0;
		while (leftIndex < leftResult.length && rightIndex < rightResult.length) {
			if (leftResult[leftIndex] <= rightResult[rightIndex]) {
				result[resultIndex] = leftResult[leftIndex];
				leftIndex++;
			} else {
				result[resultIndex] = rightResult[rightIndex];
				rightIndex++;
			}
			resultIndex++;
		}
		
		while (leftIndex < leftResult.length) {
			result[resultIndex] = leftResult[leftIndex];
			leftIndex++;
			resultIndex++;
		}
		while (rightIndex < rightResult.length) {
			result[resultIndex] = rightResult[rightIndex];
			rightIndex++;
			resultIndex++;
		}
		return result;
	}
	
}








