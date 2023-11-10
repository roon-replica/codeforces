package boj.gold5.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class P1722 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		int type = scanner.nextInt();

		if (type == 1) {
			solve1(scanner, n);
		} else {
			solve2(scanner, n);
		}

	}

	static void solve1(Scanner scanner, int n) {
		long k = scanner.nextLong();

		long[] factorials = new long[n + 1];
		factorials[0] = 1L;
		for (int i = 0; i < n; i++) {
			factorials[i + 1] = factorials[i] * (i + 1);
		}

		int[] answer = new int[n + 1];
		var digits = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			digits.add(i);
		}

		for (int i = 0; i < n; i++) {
			if (k == 0) {
				Collections.reverse(digits);

				for (int idx = i; idx < n; idx++) {
					answer[idx + 1] = digits.remove(0);
				}
				break;
			}

			int suf = n - 1;
			for (; suf > 0 && factorials[suf] > k; suf--) {
			}
			long base = factorials[n - 1 - i];
//			System.out.println(suf+" "+base+ " " +k);
//			System.out.println(digits);

			if (k <= base) {
				var digit = digits.remove(0);
				answer[i + 1] = digit;
			} else {
				int d = (int) (k / base);
				k -= base * d;

				d = Math.max(0, d + (k % base > 0 ? 1 : 0));
				var digit = digits.get(d - 1);
				answer[i + 1] = digit;
				digits.removeIf(item -> item == digit);
			}
		}

		for (int i = 1; i <= n; i++) {
			System.out.print(answer[i] + " ");
		}

	}

	static void solve2(Scanner scanner, int n) {
		var permu = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			permu.add(scanner.nextInt());
		}

		long[] factorials = new long[n + 1];
		factorials[0] = 1L;
		for (int i = 0; i < n; i++) {
			factorials[i + 1] = factorials[i] * (i + 1);
		}

		long answer = 1L;
		var digits = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			digits.add(i);
		}

		for (int i = 0; i < n; i++) {
			int p = permu.get(i);
			int smaller = 0;
			for (int j = 0; p > digits.get(j); j++) {
				smaller++;
			}

			answer += factorials[n - 1 - i] * smaller;
			digits.removeIf(item -> item == p);
//			System.out.println(smaller+" "+answer+" "+digits);
		}

		System.out.println(answer);
	}

}
