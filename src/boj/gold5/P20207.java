package boj.gold5;

import java.util.Scanner;

public class P20207 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		// cnt[c], add range
		// 0 -> sum, init
		// sum : maxCnt * (rr-lr+1)
		// 0 이상 -> setting

		int N = scanner.nextInt();
		int[] cnt = new int[367];

		for (int tt = 0; tt < N; tt++) {
			int l = scanner.nextInt();
			int r = scanner.nextInt();

			for (int i = l; i <= r; i++) {
				cnt[i]++;
			}
		}

		int maxCnt = 0;
		int lr = 366, rr = 0;
		int sum = 0;

		for (int i = 1; i <= 365; i++) {
			if (cnt[i] == 0) {
				sum += maxCnt * (rr - lr + 1);

				maxCnt = 0;
				lr = 366;
				rr = 0;
			} else {
				maxCnt = Math.max(maxCnt, cnt[i]);
				lr = Math.min(lr, i);
				rr = Math.max(rr, i);
			}
		}

		sum += maxCnt * (rr - lr + 1);
		System.out.println(sum);


	}

}
