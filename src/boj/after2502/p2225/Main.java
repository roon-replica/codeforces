package boj.after2502.p2225;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		// nHr = n+r-1Cr = (n+r-1)! / r!(n-1)!
		var scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();

		// dp[k][val] = for i in 0~n, sum of dp[k-1][val-i]

		dp[0][0] = 1;
//		for (int i = 0; i <= n; i++) {
//			dp[i][0] = 1;
//		}

		for (int kk = 1; kk <= k; kk++) {
			for (int vv = 0; vv <= n; vv++) {
				for (int i = 0; i <= vv; i++) {
					dp[kk][vv] += dp[kk - 1][vv - i];
					dp[kk][vv] %= MOD;
				}
			}
		}

		System.out.println(dp[k][n]);
	}

	static long dp[][] = new long[201][201];
	static long MOD = 1_000_000_000;

//	static long ans;

//	static void solve(int n, int k, int val) {
//		if (k == 0) {
//			if (val == n) {
//				ans++;
//				ans %= MOD;
//			}
//			return;
//		}
//		for (int i = 0; i <= n; i++) {
//			solve(n, k - 1, val + i);
//		}
//	}
}
