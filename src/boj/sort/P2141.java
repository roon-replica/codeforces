package boj.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P2141 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		var arr = new int[N][2];
		long totalPeople = 0;

		for (int i = 0; i < N; i++) {
			arr[i][0] = scanner.nextInt();
			arr[i][1] = scanner.nextInt();
			totalPeople += arr[i][1];
		}

		Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

		var peopleSum = new long[N + 1];
		int idx = 0;

		for (int i = 0; i < N; i++) {
			peopleSum[i + 1] = peopleSum[i] + arr[i][1];
		}

		long minDiff = Long.MAX_VALUE;
		long minLoc = 0;

		for (int i = 1; i <= N; i++) {
			// sum, total /2
			// sum * 2, total
			// sum1 *2, sum2 * 2
			if(peopleSum[i] *2 >= totalPeople){
				minLoc = arr[i-1][0];
				break;
			}
		}

		System.out.println(minLoc);
	}
}

//5
//1 1
//2 2
//3 3
//4 4
//5 5


//2
//		1 1
//		2 2