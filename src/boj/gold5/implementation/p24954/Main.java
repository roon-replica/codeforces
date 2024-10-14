package boj.gold5.implementation.p24954;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//		N 종류의 물약을 모두 구매
//		O(n! * n)
//		permutation

		var scanner = new Scanner(System.in);
		n = scanner.nextInt();
		c = new int[n];
		for (int i = 0; i < n; i++) {
			c[i] = scanner.nextInt();
		}

		p = new int[n][n];
		for (int i = 0; i < n; i++) {
			int pcnt = scanner.nextInt();
			for (int j = 0; j < pcnt; j++) {
				int idx = scanner.nextInt() - 1;
				int d = scanner.nextInt();
				p[i][idx] = d;        // i를 샀을 때 idx 할인은 d만큼
			}
		}

		for (int i = 0; i < n; i++) {
			source.add(i);
		}

		permu(0);

		System.out.println(ans);

	}

	static int n;
	static int[] c;
	static int[][] p;
	static List<Integer> picked = new ArrayList<>();
	static List<Integer> source = new ArrayList<>();
	static boolean[] visited = new boolean[11];

	static int ans = Integer.MAX_VALUE;

	private static void permu(int nowIdx) {
		if (picked.size() == n) {
//			System.out.println(picked);
			simul();
			return;
		}

		for (int idx = 0; idx < source.size(); idx++) {
			if (visited[idx]) {
				continue;
			}

			picked.add(source.get(idx));
			visited[idx] = true;

			permu(idx + 1);

			picked.remove(picked.size() - 1);
			visited[idx] = false;
		}
	}

	static void simul() {
		int n = picked.size();
		var costHere = new int[n];
		System.arraycopy(c, 0, costHere, 0, n);

		int totalCoin = 0;

		for (int i = 0; i < n; i++) {
			int idx = picked.get(i);
			totalCoin += costHere[idx];
			for (int j = 0; j < n; j++) {
				costHere[j] = Math.max(costHere[j] - p[idx][j], 1);
			}
		}

		ans = Math.min(ans, totalCoin);
//		System.out.println(totalCoin);
	}
}
