package boj.sort;

import java.util.Scanner;

public class P1083 {

	public static void main(String[] args) {
		// 앞에서부터 현재 숫자보다 큰 수를 나보다 앞으로 보낼 수 있는지 여부?
		// 가장 큰 수부터 검사
		// 숫자별 idx 저장해둬야 하려나
		// map(값,idx)
		// 시간복잡도: 50 * 100만

		// 1 2 3 4, 3 -> 1 2 4 3 -> 1 4 2 3 -> 4 1 2 3
		var scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scanner.nextInt();
		}
		int S = scanner.nextInt();

		for (int i = 0; i < N; i++) {
			int maxIdx = i;
			for (int j = i + 1; j < N; j++) {
				if (j - i > S) {
					break;
				}
				if (arr[j] > arr[maxIdx]) {
					maxIdx = j;
				}
			}

			S -= (maxIdx - i);

			for (int j = maxIdx; j > i; j--) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}
		}

		for (int num : arr) {
			System.out.print(num + " ");
		}
	}
}
