package lab1.prob3.subsetsum;

import java.util.*;

public class SubsetSumBruteForceOptimizationValues {
	/**
	 * 
	 * @param S - array of positive integers
	 * @param k - nonnegative integer
	 * @return - array T whose sum is k, if found, otherwise null
	 */
	public static int[] subsetSum(int[] nums, int k) {
		int n = nums.length;
		List<Integer> listOfNums = new ArrayList<Integer>();
		for(int i = 0; i < n; ++i) {
			listOfNums.add(nums[i]);		
		}
		List<Set<Integer>> subsets = PowerSet.powerSet(listOfNums);
		for(Set<Integer> subset : subsets) {
			if(sum(subset) == k) {
				return asArray(subset);
			}
		}
		return null;	
	}

	private static int sum(Set<Integer> subset) {
		int sum = 0;
		for(int i : subset)
			sum += i;
		return sum;
	}

	private static int[] asArray(Set<Integer> aSet) {
		int[] retval = new int[aSet.size()];
		int i = 0;
		for(int x : aSet) {
			retval[i++] = x;
		}
		return retval;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 4, 7, 5, 3};
		int k = 11;
		int[] nums2 = {1, 4, 7, 5, 3};
		int k2 =2;
		int[] val = null;
		if( (val = subsetSum(nums, k)) != null) {
			System.out.println(Arrays.toString(val));
		} else {
			System.out.println("null");
		}
		if( (val = subsetSum(nums2, k2)) != null) {
			System.out.println(Arrays.toString(val));
		} else {
			System.out.println("null");
		}
		
		//test like the recursive versions (leads to out of memory error)
		int[] nums3 = { 2, 5, 123, 48, 29, 19, 34, 10, 20, 88, 47, 19, 21,
				42, 4, 8, 3, 7, 5, 9, 14, 26, 25, 31, 33, 18, 44, 35, 48, 53, 51,
				61, 69, 72 };
		int k3 = 611;
		if( (val = subsetSum(nums3, k3)) != null) {
			System.out.println(Arrays.toString(val));
		} else {
			System.out.println("null");
		}
	}
}
