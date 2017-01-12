package lab7.prob1.remove.extra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RemoveExtras {

	public static List<String> removeExtras(List<String> list) {
		final Integer ONE = new Integer(1);
		final Integer TWO = new Integer(2);
		List<String> retVal = new LinkedList<String>();
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		for (String s : list) {
			if (hashmap.containsKey(s)) {
				Integer val = hashmap.get(s);
				// if < 2
				if (val.compareTo(TWO) < 0) {
					retVal.add(s);
				}
				hashmap.put(s, new Integer(val.intValue() + 1));

			} else {
				retVal.add(s);
				hashmap.put(s, ONE);
			}
		}
		return retVal;
	}
	
	public static void main(String[] args) {
		String[] arr = {"andy", "mike", "andy", "andy","andy","mike"};
		System.out.println(Arrays.asList(arr));
		System.out.println(RemoveExtras.removeExtras(Arrays.asList(arr)));
	}
}
