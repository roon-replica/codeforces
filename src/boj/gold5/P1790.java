package boj.gold5;

import java.util.ArrayList;
import java.util.Scanner;

public class P1790 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		long[] tenPowers = new long[11];
		tenPowers[0] = 1L;
		for (int i = 0; i < 10; i++) {
			tenPowers[i + 1] = tenPowers[i] * 10;
		}

		long[] acc = new long[11];
		acc[0] = 0L;

		for (int i = 1; i < 10; i++) {
			var here = 9L * tenPowers[i - 1] * i;
//			acc[i] += here;
			acc[i] = acc[i - 1] + here;
//			System.out.println(acc[i]);
		}

		int p = 0;
		while (k > acc[p]) {
			p++;
		}

		System.out.println(p);

		int beforeP = 0;
		for (int i = 1; i < 10; i++) {
			if (acc[i] >= k) {
				beforeP = i - 1;
				break;
			}
		}

		long sumBefore = acc[beforeP];
//		System.out.println(beforeP + " " + sumBefore);

		long mok = (k - sumBefore) / p;
		long remain = (k - sumBefore) % p;

		System.out.println(mok + " " + remain);

		long lastNum = (tenPowers[p - 1] - 1 + mok + (remain > 0 ? 1 : 0));
//		System.out.println(lastNum);

		if (lastNum > n) {
			System.out.println(-1);
			return;
		}

		var numbers = new ArrayList<Long>();
		do {
			numbers.add(lastNum % 10);
			lastNum /= 10;
		} while (lastNum > 0);

//		Collections.reverse(numbers);
		System.out.println(numbers);
		System.out.println(numbers.get((int) (p - remain - p * mok)));
	}
}
