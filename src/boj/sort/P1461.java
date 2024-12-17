package boj.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class P1461 {

	public static void main(String[] args) {
		// 11*2 + 28 *2 +29 + 39 = 22 + 56 + 29 + 39 = 146
		// 11 *2  vs 11 + 2*2

		var scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		var positives = new ArrayList<Integer>();
		var negatives = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			var num = scanner.nextInt();
			if (num > 0) {
				positives.add(num);
			} else {
				negatives.add(-num);
			}
		}

		positives.sort(Integer::compareTo);
		negatives.sort(Comparator.comparingInt(Math::abs));

//		System.out.println(positives);
//		System.out.println(negatives);

		int answer = 0;

		for (int i = positives.size() - 1; i >= 0; i -= M) {
			answer += positives.get(i) * 2;
		}

		for (int i = negatives.size() - 1; i >= 0; i -= M) {
			answer += negatives.get(i) * 2;
		}

		int posMax = positives.isEmpty() ? 0 : positives.get(positives.size() - 1);
		int negMax = negatives.isEmpty() ? 0 : negatives.get(negatives.size() - 1);

		System.out.println(answer - Math.max(posMax, negMax));

	}

}
