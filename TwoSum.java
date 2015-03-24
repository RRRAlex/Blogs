package algo;

//一组整数 选择任意两个数 他们的和为给定的数值
public class TwoSum {

	public static void main(String[] args) {
		int[] ins = new int[]{4,3,6,7,2,1,9,8};
		find(ins, 5);
	}
	
	private static void find(int[] container, int expect){
		//先快速排序 时间复杂度为o(lgN)
		quickSort(container, 0, container.length - 1);
		
		int low = 0;
		int high = container.length -1;
		
		while(container[low] + container[high] != expect){
			if(low > high) break;
			
			while(container[high] > expect && 
					(container[low] + container[high] != expect) ) high--;
			
			if(container[low] < expect && 
					(container[low] + container[high] != expect) ) low++;
		}
		
		if(low <= high) System.out.println("\n" + container[low] + " and " + container[high]);
		else System.out.println("NOT FOUND");
	}
	
	private static int partition(int[] original, int start, int end){
		int pivot = original[start];
		while(start < end){
			while(start < end && original[end] > pivot){
				end--;
			}
			while(start < end && original[start] < pivot){
				start++;
			}
			if(start < end) swap(original, start,end);
		}
		swap(original, start, end);
		return end;
	}
	
	private static void quickSort(int[] original, int start, int end){
		if(start < end){
			int pivot = partition(original, start, end);
			quickSort(original, start, pivot - 1);
			quickSort(original, pivot + 1, end);
		}
	}
	
	private static void swap(int[] original, int i, int j){
		int tmp = original[j];
		original[j] = original[i];
		original[i] = tmp;
	}
	
	
	private static void show(Object o){
		System.out.print(o + " ");
	}
}
