package boj.sort;

import java.util.Arrays;
import java.util.Scanner;

public class P3151 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		var arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scanner.nextInt();
		}

		Arrays.sort(arr);

//		var sumList = new int[N*N/2]; // 4* 5천만 = 백만(mb) * 4* 50 = 200mb

		long ans = 0; // 만C2 = 2*10^7

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int sum = arr[i] + arr[j];
				int lowerBound = lowerBound(arr, -sum, j + 1);
				int upperBound = lowerBound(arr, -sum + 1, j + 1);

				ans += (upperBound - lowerBound);
				if (arr[upperBound] == -sum && upperBound >= j + 1) {
//					System.out.println(-sum+" "+lowerBound+" "+upperBound+" "+i+" "+j);
					ans++;
				}

			}
		}

		System.out.println(ans);
	}

	static int lowerBound(int[] arr, int target, int startIdx) {
		int left = startIdx, right = arr.length - 1;
		for (; left < right; ) {
			int mid = (left + right) / 2;
			if (arr[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

//	static int binarySearch(int[] arr, int target, int startIdx) {
//		int left = startIdx, right = arr.length - 1;
//
//		for (; left <= right; ) {
//			int mid = (left + right) / 2;
//			if (arr[mid] == target) {
//				return mid;
//			} else if (arr[mid] < target) {
//				left = mid + 1;
//			} else {
//				right = mid - 1;
//			}
//		}
//		return -1;
//	}
}

// binarySearch로 안되는 반례
//	6
//	-8 3 3 5 5 5
