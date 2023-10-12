package boj.gold5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P2116 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		List<Integer>[] a = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			a[i] = new ArrayList<>();
			for (int j = 0; j < 6; j++) {
				a[i].add(scanner.nextInt());
			}
		}

		// a,f | b,d | c,e 고정
		// 0,5 | 1,3 | 2,4
		// bruteforce 가능?
		// 바닥면 결정되면 그 위부터는 수평방향으로만 돌릴수있다
		// a나 f 바닥으로 하는거 똑같네
		// 나머지는 맘대로 조합가능?
		// 맨 위는 또 반대? -> 걍 a,f 바닥오는걸 다르게 보면 커버됨
		// 그럼 6 * 4^(n-1) 가지 나오는데
		// 더 줄여서 6가지뿐?

		// pair number
		// position, number
		// 제외하고 4개 다 가능

		int[] opponentPos = new int[]{5, 3, 4, 1, 2, 0};

		int maxSum = 0;
		for (int s = 1; s <= 6; s++) { //initial bottom value
			int p = 0; // bot position
			int v = s; // bot value
			int sum = 0;

			//next bot = opponent[p]

			for (int h = 0; h < n; h++) {
				for (int i = 0; i < 6; i++) {
					if (a[h].get(i) == v) {
						p = i;
						break;
					}
				}

				int maxVal = 0;
				for (int i = 0; i < 6; i++) {
					if (i == p || i == opponentPos[p]) {
						continue;
					}
					maxVal = Math.max(maxVal, a[h].get(i));
				}
				sum += maxVal;

				// next bot position, value
				v = a[h].get(opponentPos[p]);
			}

			maxSum = Math.max(maxSum, sum);
		}

		System.out.println(maxSum);


	}
}
