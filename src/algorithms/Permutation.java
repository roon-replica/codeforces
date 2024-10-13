package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

	public static void main(String[] args) {
		n = 4;
		selected = new boolean[n];

		permutation(0);
	}

	static int n;
	static boolean[] selected;
	static List<Integer> picked = new ArrayList<>();

	static void permutation(int idx) {
		if (picked.size() == n) {
			System.out.println(picked);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (selected[i]) {
				continue;
			}

			picked.add(i);
			selected[i] = true;

			permutation(i);

			picked.remove(picked.size() - 1);
			selected[i] = false;
		}
	}
}
