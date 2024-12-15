package boj.sort;

import java.util.ArrayList;
import java.util.Collections;

public class P1744 {

	public static void main(String[] args) {
		// sort
		// 1. 음수는 절대값이 큰 것부터 묶어서 더한다.
		// 2. 양수는 큰 것부터 묶어서 더한다.
		// 3. 1은 묶지 않고 더한다.
		// 4. 음수가 홀수개이고 0이 있으면 젤 작은 음수는 합계에서 제외한다

		var scanner = new java.util.Scanner(System.in);
		int N = scanner.nextInt();
		var pos = new ArrayList<Integer>();
		var oneCnt = 0;
		var zeroCnt = 0;
		var neg = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			int num = scanner.nextInt();
			if (num > 1) {
				pos.add(num);
			} else if (num == 1) {
				oneCnt++;
			} else if (num == 0) {
				zeroCnt++;
			} else {
				neg.add(num);
			}
		}

		var ans = 0;
		pos.sort(Collections.reverseOrder());
		neg.sort(null);

//		System.out.println(pos);
//		System.out.println(neg);

		for (int i = 0; i < pos.size(); i += 2) {
			if (i + 1 < pos.size()) {
				ans += pos.get(i) * pos.get(i + 1);
			} else {
				ans += pos.get(i);
			}
		}

		ans += oneCnt;

		if (neg.size() % 2 == 1) {
			if (zeroCnt != 0) {
				neg.remove(neg.size() - 1);
			}
		}

		for (int i = 0; i < neg.size(); i += 2) {
			if (i + 1 < neg.size()) {
				ans += neg.get(i) * neg.get(i + 1);
			} else {
				ans += neg.get(i);
			}
		}

		System.out.println(ans);

	}

}
