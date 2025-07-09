package boj.after2507;

import java.util.Scanner;

public class 암호코드 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		s = scanner.next();
		LEN = s.length();
		memo = new int[LEN + 2];
		memo[0] = 1;

		for (int i = 0; i < LEN; i++) {
			if(s.charAt(i) != '0') {
				memo[i + 1] += memo[i];
				memo[i + 1] %= MOD;
			}

			if (i < LEN - 1) {
				int value = 10 * (s.charAt(i) - '0') + (s.charAt(i + 1) - '0');
				if (value >= 10 && value <= 26) {
					memo[i + 2] += memo[i];
					memo[i + 2] %= MOD;
				}
			}
		}

		System.out.println(memo[LEN]);
	}

	static int LEN;
	static String s;
	static int MOD = 1000000;
	static int[] memo;
	static boolean valid = true;

//	static int solve(int idx) {
//		if (memo[idx] != 0) {
//			return memo[idx];
//		}
//
//		if (idx >= LEN) {
//			return 1;
//		}
//
//		var here = s.charAt(idx);
//
//		if (idx + 2 <= LEN) {
//			int value = 10 * (s.charAt(idx) - '0') + (s.charAt(idx + 1) - '0');
//			if (value > 26 || value < 10) {
//				return memo[idx] = solve(idx + 1) % MOD;
//			} else {
//				return memo[idx] = (solve(idx + 1) % MOD + solve(idx + 2) % MOD) % MOD;
//			}
//		} else {
//			if (here != '0') {
//				return memo[idx] = solve(idx + 1) % MOD;
//			} else {
//				return 0;
//			}
//		}
//	}

}
