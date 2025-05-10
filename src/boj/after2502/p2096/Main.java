package boj.after2502.p2096;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		// 단순한 dp같은데
		// dp[r][next c] += dp[r-1][c]

		int N = scanner.nextInt();
		int[] maxDp = new int[3];
		int[] minDp = new int[3];
		int[] arr = new int[3];
		int[] tmpMaxDp = new int[3];
		int[] tmpMinDp = new int[3];

		for (int r = 0; r < N; r++) {
			arr[0] = scanner.nextInt();
			arr[1] = scanner.nextInt();
			arr[2] = scanner.nextInt();

			for (int c = 0; c < 3; c++) {
				tmpMaxDp[c] = maxDp[c];
				if (r == 0) {
					tmpMinDp[c] = arr[c];
				}else{
					tmpMinDp[c] = N*10;
				}
			}

			for (int c = 0; c < 3; c++) {
				for (int d = -1; d <= 1; d++) {
					int nc = c + d;
					if (nc < 0 || nc >= 3) {
						continue;
					}
					tmpMaxDp[nc] = Math.max(tmpMaxDp[nc], maxDp[c] + arr[nc]);
					tmpMinDp[nc] = Math.min(tmpMinDp[nc], minDp[c] + arr[nc]);
				}
			}

			for (int c = 0; c < 3; c++) {
				maxDp[c] = tmpMaxDp[c];
				minDp[c] = tmpMinDp[c];
			}
		}


		int max = 0;
		int min = Integer.MAX_VALUE;
		for (int c = 0; c < 3; c++) {
			max = Math.max(max, maxDp[c]);
			min = Math.min(min, minDp[c]);
		}

		System.out.println(max + " " + min);
	}
}
