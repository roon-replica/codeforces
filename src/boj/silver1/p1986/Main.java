package boj.silver1.p1986;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// Pawn은 상대팀의 말은 잡을 수 없다고 하자(즉, 장애물의 역할만 한다는 것이다)
		// 안전한 칸이 몇 칸
		// p > k > q
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();

		int qc = scanner.nextInt();
		List<Integer> qs = new ArrayList<>();
		for (int i = 0; i < qc; i++) {
			qs.add(scanner.nextInt() - 1);
			qs.add(scanner.nextInt() - 1);
		}

		int kc = scanner.nextInt();
		List<Integer> ks = new ArrayList<>();
		for (int i = 0; i < kc; i++) {
			ks.add(scanner.nextInt() - 1);
			ks.add(scanner.nextInt() - 1);
		}

		int pc = scanner.nextInt();
		List<Integer> ps = new ArrayList<>();
		for (int i = 0; i < pc; i++) {
			ps.add(scanner.nextInt() - 1);
			ps.add(scanner.nextInt() - 1);
		}

		var map = new int[n][m];

		int[][] dirsK = new int[][]{{-1, -2}, {-2, -1}, {1, -2}, {2, -1}, {1, 2}, {2, 1}, {-1, 2}, {-2, 1}};
		int[][] dirsQ = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

		for (int i = 0; i < ps.size(); i += 2) {
			int r = ps.get(i);
			int c = ps.get(i + 1);

			map[r][c] = -1; // 장애물
		}

		for (int i = 0; i < qs.size(); i += 2) {
			int r = qs.get(i);
			int c = qs.get(i + 1);
			map[r][c] = -1;
		}

		for (int i = 0; i < ks.size(); i += 2) {
			int r = ks.get(i);
			int c = ks.get(i + 1);
			map[r][c] = -1;

			for (var dir : dirsK) {
				if (r + dir[0] >= n || c + dir[1] >= m || r + dir[0] < 0 || c + dir[1] < 0) {
					continue;
				}
				if (map[r + dir[0]][c + dir[1]] == -1) {
					continue;
				}
				map[r + dir[0]][c + dir[1]] = 1;
			}
		}

		for (int i = 0; i < qs.size(); i += 2) {
			int r = qs.get(i);
			int c = qs.get(i + 1);
//			map[r][c] = -1;

			for (var dir : dirsQ) {
//				System.out.println("rc: "+r+" "+c);
				for (int j = 1; j <= Math.max(m, n); j++) {
					if (r + dir[0] * j >= n || c + dir[1] * j >= m || r + dir[0] * j < 0 || c + dir[1] * j < 0) {
						continue;
					}
					if (map[r + dir[0] * j][c + dir[1] * j] == -1) {
						break;
					}
//					System.out.println((r + dir[0] * j) + " " + (c + dir[1] * j)+" "+map[r + dir[0]][c + dir[1]]);

					map[r + dir[0] * j][c + dir[1] * j] = 1;
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					ans++;
				}
//				System.out.print(map[i][j]);
			}
//			System.out.println();
		}

		System.out.println(ans);


	}

}
