package boj.sort;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P2141 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		var arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			arr[i][0] = scanner.nextInt();
			arr[i][1] = scanner.nextInt();
		}

		Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

		var peopleSum = new long[N + 1];
		for (int i = 0; i < N; i++) {
			peopleSum[i + 1] = peopleSum[i] + arr[i][1];
		}

		BigInteger cost = BigInteger.ZERO;
		for (int i = 1; i < N; i++) {
//			cost += (long) (arr[i][0] - arr[0][0]) * arr[i][1];
			cost = cost.add(BigInteger.valueOf((long) (arr[i][0] - arr[0][0]) * arr[i][1]));
		}

		BigInteger minCost = cost;
		int minLocation = arr[0][0];

		for (int i = 1; i < N; i++) {
			// 현재 사람 기준 왼쪽편은 거리 손해볼거고 오른쪽편은 이득
			long dist = arr[i][0] - arr[i - 1][0];
			long left = peopleSum[i] * dist;
			long right = (peopleSum[N] - peopleSum[i]) * dist;

//			cost += (-right + left);
			cost = cost.add(BigInteger.valueOf(-right + left));

			if (cost.compareTo(minCost) < 0) {
				minCost = cost;
				minLocation = arr[i][0];
			}
		}

		System.out.println(minLocation);
	}
}

//5
//1 1
//2 2
//3 3
//4 4
//5 5
