package boj.after2507;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class 회장뽑기2660 {
	private static int MAX = 1000;

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		int N = scanner.nextInt();

		int[][] minCost = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j) {
					minCost[i][j] = MAX;
				}
			}
		}

		while (true) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();

			if (a == -1) {
				break;
			}

			minCost[a][b] = 1;
			minCost[b][a] = 1;
		}

		// todo: f,t,m 으로 하면 안되고 m,f,t 순으로 반복문 돌려야하네....
		for (int m = 1; m <= N; m++) {
			for (int f = 1; f <= N; f++) {
				for (int t = 1; t <= N; t++) {
					if (f == t) {
						continue;
					}

					minCost[f][t] = Math.min(minCost[f][t], minCost[f][m] + minCost[m][t]);
				}
			}
		}

		int minCostOverall = MAX;
		for (int i = 1; i <= N; i++) {
			int maxCost = 0;
			for (int j = 1; j <= N; j++) {
				if (minCost[i][j] == MAX) {
					continue;
				}
				maxCost = Math.max(maxCost, minCost[i][j]);
			}

			minCostOverall = Math.min(minCostOverall, maxCost);
		}

		var minNodes = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			int maxCost = 0;
			for (int j = 1; j <= N; j++) {
				maxCost = Math.max(maxCost, minCost[i][j]);
			}

//			System.out.println(i+" "+maxCost);
			if (maxCost == minCostOverall) {
				minNodes.add(i);
			}
		}

		System.out.println(minCostOverall + " " + minNodes.size());
		System.out.println(minNodes.stream().map(String::valueOf).collect(Collectors.joining(" ")));

//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= N; j++) {
//				System.out.print(minCost[i][j] + " ");
//			}
//			System.out.println();
//		}

	}
}
