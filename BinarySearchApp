//Different kinds of binary search application
public class BinarySearchApp {

	public static void main(String[] args) {
		
		int[] origin = new int[]{1,3,4,5,7,8,9,10,11,12};
		int res = binarySearch01_rec(origin, 11, 0, origin.length - 1);
		show("Result of binarySearch01_rec : " + res);
		
		res = binarySearch01(origin, 11);
		show("binarySearch01 : " + res);
		
		origin = new int[]{1,2,3,4,4,4,5,6,6,6,7};
		/*res = findLowerBound(origin, 4);
		show("lower bound of number 4: " + res);
		res = findUpperBound(origin, 4);
		show("upper bound of number 4: " + res);*/
		show("the frequency of number 4: " + findFrequency(origin, 4));
		
		origin = new int[]{1,3,4,5,7,8,9,10,11,12};
		show("the first larger number of 6 is: " + findFirstLarger(origin, 6));
	}
	
	/**
	 * 
	 * Simple binary search basing on recursiveness
	 *
	 * @param container
	 * @param target
	 */
	public static int binarySearch01_rec(int[] container, int target, int start, int end){
		if(null == container || container.length == 0 || end >= container.length || start < 0) return Integer.MIN_VALUE;
		
		int mid = start + (end - start + 1)/2;
		if(target < container[mid]){
			//go search in left half
			return binarySearch01_rec(container, target, start, mid - 1);
		}else if (target > container[mid]){
			//go search in right half
			return binarySearch01_rec(container, target, mid + 1, end);
		}else{
			return mid;
		}
	}
	
	/**
	 * 
	 * Simple binary search without recursiveness
	 *
	 * @param container
	 * @param target
	 * @return
	 */
	public static int binarySearch01(int[] container, int target){
		if(null == container || container.length == 0) return Integer.MIN_VALUE;
		int low = 0;
		int high = container.length - 1;
		int m = 0;
		
		while(low < high){
			m = low + (high - low + 1)/2;
			if(target < container[m]){
				//move to left
				high = m - 1;
			}else if (target > container[m]){
				//move to right
				low = m + 1;
			}else{
				//found
				return m;
			}
		}
		//not found, because low pointer and high pointer area crossed
		return Integer.MIN_VALUE;
	}
	
	
	/**
	 * 
	 * Find the frequency of target number from an array
	 *
	 * @param container
	 * @param target
	 * @return
	 */
	public static int findFrequency(int[] container, int target){
		int lower = findLowerBound(container, target);
		int upper = findUpperBound(container, target);
		return upper - lower + 1;
	}
	
	/**
	 * 
	 * example: [1 2 3 4 4 4 5 6 6 6 7]
	 * the lower bound of 4 is 3
	 * 
	 * @param container
	 * @param target
	 * @return
	 */
	private static int findLowerBound(int[] container, int target){
		int low = 0;
		int high = container.length - 1;
		int mid = 0;
		
		while(low + 1< high){
			mid = (low + high)/2;
			//from left
			if(container[mid] >= target){
				//go left
				high = mid;
			}else{
				low = mid + 1;
			}
		}
		
		if(container[low] == target) return low;
		if(container[high] == target) return high;
		return -1;
	}
	
	/**
	 * 
	 * example: [1 2 3 4 4 4 5 6 6 6 7]
	 * the upper bound of 4 is 5
	 * 
	 * @param origin
	 * @param target
	 * @return
	 */
	private static int findUpperBound(int[] origin, int target) {
		int low = 0;
		int high = origin.length - 1;
		int mid = 0;
		
		while(low + 1 < high){
			mid = (low + high)/2;
			if(origin[mid] <= target){
				//from bigger part
				low = mid;
			}else{
				high = mid - 1;
			}
		}
		
		if(origin[low] == target) return low;
		if(origin[high] == target) return high;
		return -1;
	}
	
	/**
	 * 
	 * example: [1 2 3 7 8 12 15 20]
	 * the nearest and bigger one for 9 is 12
	 *
	 * @param origin
	 * @param target
	 * @return
	 */
	public static int findFirstLarger(int[] origin, int target){
		int left = 0;
		int right = origin.length - 1;
		int mid = 0;
		
		while(left <= right){
			mid = (left + right)/2;
			if(origin[mid] > target){
				//go left
				right = mid - 1;
			}else if(origin[mid] < target){
				left = mid + 1;
			}
		}
		
		return origin[left];
	}
	
	private static void show(Object o){
		System.out.println(o);
	}
}
