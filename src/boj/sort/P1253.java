package boj.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class P1253 {

	public static void main(String[] args) {
		var scanner = new java.util.Scanner(System.in);
		int N = scanner.nextInt();
		var arr = new int[N];
		var positives = new ArrayList<Integer>();
		var negatives = new ArrayList<Integer>();
		var zeroCount = 0;
		var positiveCount = new HashMap<Integer, Integer>();
		var negativeCount = new HashMap<Integer, Integer>();

		for (int i = 0; i < N; i++) {
			var here = scanner.nextInt();
			arr[i] = here;
			if (here > 0) {
				positives.add(here);
				positiveCount.put(here, positiveCount.getOrDefault(here, 0) + 1);
			} else if (here == 0) {
				zeroCount++;
			} else {
				negatives.add(here);
				negativeCount.put(here, negativeCount.getOrDefault(here, 0) + 1);
			}
		}

		var positiveSums = new HashSet<Integer>();
		var positiveNegativeSums = new HashSet<Integer>();
		var negativeSums = new HashSet<Integer>();

		for (int i = 0; i < positives.size(); i++) {
			for (int j = i + 1; j < positives.size(); j++) {
				positiveSums.add(positives.get(i) + positives.get(j));
			}
		}

		for (int i = 0; i < positives.size(); i++) {
			for (int j = 0; j < negatives.size(); j++) {
				positiveNegativeSums.add(positives.get(i) + negatives.get(j));
			}
		}

		for (int i = 0; i < negatives.size(); i++) {
			for (int j = i + 1; j < negatives.size(); j++) {
				negativeSums.add(negatives.get(i) + negatives.get(j));
			}
		}

		int ans = 0;
		for (var num : arr) {
			if (num > 0) {
				if (positiveSums.contains(num) || positiveNegativeSums.contains(num)) {
					ans++;
				} else if (positiveCount.getOrDefault(num, 0) >= 2 && zeroCount >= 1) {
					ans++;
				}
			} else if (num == 0) {
				if (zeroCount >= 3 || positiveNegativeSums.contains(0)) {
					ans++;
				}
			} else {
				if (negativeSums.contains(num) || positiveNegativeSums.contains(num)) {
					ans++;
				} else if (negativeCount.getOrDefault(num, 0) >= 2 && zeroCount >= 1) {
					ans++;
				}
			}
		}

		System.out.println(ans);

		// <양수, 0, 음수>
		// 양수:
		// - 양수 + 양수 : 조합 set
		// - 양수 + 음수 : 조합 set
		// - (다른 위치 같은 값) 양수 + 0: count => 양수 개수 >=2 , 0 개수 >=1
		// 0:
		// - (다른 위치) 0 + 0 : 0 개수 count => 3개 이상
		// - (값이 같은) 양수 + 음수: 조합 set
		// 음수:
		// - 양수 + 음수: 조합 set
		// - 음수 + 음수: 조합 set
		// - (다른 위치 같은 값) 음수 + 0 :  count => 음수 개수 >=2 , 0 개수 >=1

	}
}

//		3
//		-1 0 1
