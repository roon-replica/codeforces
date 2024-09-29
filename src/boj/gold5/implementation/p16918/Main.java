package boj.gold5.implementation.p16918;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		// 0 설치
		// 1 -
		// 2 빈칸에 다 설치
		// 3 3초 전에 설치한거 다 폭발
		// 4 폭발한데 다 설치
		// 5 3초 전에 설치한거 다 폭발
		// 6 폭발한데 다 설치
		// 7 3초 전에 설치한거 다 폭발
		// ...

		// 걍 구현하면 될텐데..
		// 남은 시간을 각 칸에 적어두기?

		// 보니까 시간 감소시키고 액션을 한다. 폭발하거나 설치하거나

		int r = scanner.nextInt();
		int c = scanner.nextInt();
		int n = scanner.nextInt();

		var map = new int[r][c];
		var dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {0, 0}};

		for (int i = 0; i < r; i++) {
			var input = scanner.next();
			for (int j = 0; j < c; j++) {
				map[i][j] = (input.charAt(j) == 'O') ? 2 : 0;
			}
		}

		for (int t = 2; n >= 2 && t <= n; t++) {
			// 감소
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] == 0) {
						continue;
					}
					map[i][j]--;
				}
			}

			if (t % 2 == 0) {
				// 빈 칸에 설치
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (map[i][j] == 0) {
							map[i][j] = 3;
						}
					}
				}
			} else {
				// 0초된거 폭발
				var explodeList = new ArrayList<Integer>(); // r*i+j
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						if (map[i][j] != 0) {
							continue;
						}
						explodeList.add(r * i + j);
					}
				}

				for (var explode : explodeList) {
					int i = explode / r;
					int j = explode % r;

					for (var dir : dirs) {
						int ni = i + dir[0];
						int nj = j + dir[1];

						if (ni < 0 || ni >= r || nj < 0 || nj >= c) {
							continue;
						}
						map[ni][nj] = 0;
					}
				}
			}

//			System.out.println("time: " + t);
//			print(map);
		}

		print(map);

		// 왜 틀림??

	}

	static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] > 0 ? 'O' : '.');
//				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
