
public class RemoveMin {

	public static int[] removeMin(int a[]) {

		int n = a.length;
		a[0] = a[n - 1];
		n--;

		if (n > 0)
			siftDown(0, a);

		return a;
	}

	private static void siftDown(int index, int[] a) {

		int leftChildIndex, rightChildIndex, minIndex, tmp, n;

		leftChildIndex = 2 * index + 1;
		rightChildIndex = 2 * index + 2;
		n = a.length - 1;

		if (rightChildIndex >= n) {

			if (leftChildIndex >= n)

				return;

			else

				minIndex = leftChildIndex;

		} else {

			if (a[leftChildIndex] <= a[rightChildIndex])

				minIndex = leftChildIndex;

			else

				minIndex = rightChildIndex;

		}

		if (a[index] > a[minIndex]) {

			tmp = a[minIndex];

			a[minIndex] = a[index];

			a[index] = tmp;

			siftDown(minIndex, a);

		}

	}

	public static void main(String[] args) {
		int[] data = new int[] {  3, 6, 5, 9, 8 };
		
		int[] a = removeMin(data);
		for (int i = 0; i < a.length; i++)
			System.out.print(" " + a[i]);
		

	}

}
