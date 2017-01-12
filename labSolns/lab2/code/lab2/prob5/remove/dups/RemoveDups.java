package lab2.prob5.remove.dups;

import java.util.*;

public class RemoveDups {
	public static List<Integer> removeDups(List<Integer> list) {
		final Integer ZERO = new Integer(0);
		HashMap<Integer, Integer> h = new HashMap<>();
		List<Integer> retval = new LinkedList<>();
		for(Integer x : list) {
			if(!h.containsKey(x)) {
				h.put(x, ZERO);
				retval.add(x);
			}
		}
		return retval;
	}
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(50, 23, 12, 2, 3, 4, 4, 3, 2, 1, 1, 1);
		System.out.println(RemoveDups.removeDups(list));
	}
}
