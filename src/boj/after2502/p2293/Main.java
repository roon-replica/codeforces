package boj.after2502.p2293;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		//n*k. 동전 개수 늘려가면서 값 계산하고 ans++
		// dfs

		var scanner = new java.util.Scanner(System.in);
		n = scanner.nextInt();
		k = scanner.nextInt();
		arr = new int[n];
		var list = new ArrayList<Integer>();
		list.add(0);

		for (int i = 0; i < n; i++) {
//			arr[i] = scanner.nextInt();
			list.add(scanner.nextInt());
		}


		int[][] dp = new int[n + 1][k + 1];
		dp[0][0]=1;

		for (int i = 1; i <= n; i++) {
//			for(int val=0;val<=k;val++) {
//				dp[i+1][val]+=dp[i][val];
//			}
			
			for(int bi=0;bi<i;bi++){
				for (int a : list) {
					for (int val = 0; val <= k; val++) {
						if (val - a >= 0) {
							dp[i][val] += dp[bi][val - a];
						}
					}
				}
			}


		}

		System.out.println(dp[n][k]);

		for (int i = 0; i <= n; i++) {
			for (int v = 0; v <= k; v++) {
				System.out.print(dp[i][v] + " ");
			}
			System.out.println();
		}

//		System.out.println(ans);

	}

	static int n, k, value, ans;
	static int[] arr;
}
