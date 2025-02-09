package boj.after2502.p2293;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		var scanner = new java.util.Scanner(System.in);
		n = scanner.nextInt();
		k = scanner.nextInt();
		coins = new int[n];

		for (int i = 0; i < n; i++) {
			coins[i] = scanner.nextInt();
		}
		Arrays.sort(coins);

		// dp[값] = 경우의 수로 하면
		// dp[k] = dp[k-coin1] + dp[k-coin2] + dp[k-coin3] + ... + dp[k-coinN] 일텐데
		// 겹치는 경우를 어떻게 하지? (2,1)과 (1,2)을 구분하면 안되는데
		// dp[0] = 0
		// coin이 정렬되어 있으면 dp[coin1] = 1로 둬도 되겠고
		// dp[coin2] = 1 + dp[coin2-coin1]
		// dp[coin3] = 1 + dp[coin3-coin1] + dp[coin3-coin2]

		// 못풀겠어서 풀이 찾아봄
		// https://velog.io/@jxlhe46/%EB%B0%B1%EC%A4%80-2293%EB%B2%88.-%EB%8F%99%EC%A0%84-1-bfi120m5
		// 여기보니까 {1,2}만 쓰는 경우 직접 나열해보면 규칙보이는거 같기도...

		int[] dp = new int[k + 1];
		dp[0] = 1;

		for (int coin : coins) {
			for (int val = 0; val < k; val++) {
				if (val + coin > k) {
					break;
				}
				dp[val + coin] += dp[val];
			}
		}

//		for (int i = 1; i <= k; i++) {
//			System.out.print(dp[i] + " ");
//		}

		System.out.println(dp[k]);
	}

	static int n, k, value, ans;
	static int[] coins;
}
