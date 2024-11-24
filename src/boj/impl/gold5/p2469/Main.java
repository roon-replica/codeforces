package boj.impl.gold5.p2469;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		K = scanner.nextInt();
		R = scanner.nextInt();
		target = scanner.next();

		C = K - 1;
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			var input = scanner.next();
			for (int c = 0; c < C; c++) {
				map[r][c] = input.charAt(c);
				if (map[r][c] == '?') {
					LINE = r;
				}
			}
		}

		var simulDown = String.valueOf(simulDown());
		var simulUp = String.valueOf(simulUp());

		// ans
		var ans = new char[C];
		for (int i = 0; i < C; i++) {
			ans[i] = '*';
		}
		boolean possible = true;

		for (int i = 0; i < C; i++) {
			char c1 = simulDown.charAt(i);
			char c2 = simulUp.charAt(i);
			if (c1 == c2) {
				ans[i] = '*';
			} else {
				if (simulUp.indexOf(c1) != i + 1) {
					possible = false;
					break;
				} else {
					ans[i] = '-';
					i++;
				}
			}
		}

		if (possible) {
			System.out.println(ans);
		} else {
			for (int i = 0; i < C; i++) {
				ans[i] = 'x';
			}
			System.out.println(ans);
		}
	}

	static int K, R, C, LINE;
	static char[][] map;
	static String target;

	static char[] simulDown() {
		// 자기 왼쪽,현재 인덱스가 탈 수 있는 사다리

		var ret = new char[K];

		// -1에 사다리 => -1, 0에 사다리=>+1
		for (char here = 'A'; here < 'A' + K; here++) {
			int c = here - 'A';

			for (int r = 0; r < LINE; r++) {
				int left = c - 1;
				int right = c;

				if (left >= 0 && map[r][left] == '-') {
					c--;
				}
				if (right < C && map[r][right] == '-') {
					c++;
				}
			}

//			System.out.println(here + " " + c);
			ret[c] = here;
		}

		System.out.println(ret);
		return ret;
	}

	static char[] simulUp() {
		var ret = new char[K];

		// -1에 사다리 => -1, 0에 사다리=>+1
		for (int i = 0; i < K; i++) {
			char here = target.charAt(i);
//			System.out.println(here+" "+c);
			int c = i;

			for (int r = R - 1; r > LINE; r--) {
				int left = c - 1;
				int right = c;

				if (left >= 0 && map[r][left] == '-') {
					c--;
				}
				if (right < C && map[r][right] == '-') {
					c++;
				}
			}

//			System.out.println(here + " " + c);
			ret[c] = here;
		}

		System.out.println(ret);
		return ret;
	}

//	5
//	3
//	ABCDE
//	****
//	????
//	****

//	5
//	3
//	ABCDE
//	-***
//	????
//	*-**

}
