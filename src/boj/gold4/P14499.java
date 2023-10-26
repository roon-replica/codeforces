package boj.gold4;

import java.util.Scanner;

public class P14499 {

	public static void main(String[] args) {
		var sc = new Scanner(System.in);

		// 진짜 구현문제네
		int n = sc.nextInt();
		int m = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int k = sc.nextInt();

		int[][] map = new int[n + 1][m + 1];

		for (int rr = 0; rr < n; rr++) {
			for (int cc = 0; cc < m; cc++) {
				map[rr][cc] = sc.nextInt();
			}
		}

		// 동서남북 transition
		// 동,서,북,남 = 1,2,3,4
		int[][] transition = new int[][]{
				{4, 2, 1, 6, 5, 3}, //동
				{3, 2, 6, 1, 5, 4}, //서
				{5, 1, 3, 4, 6, 2}, //북
				{2, 6, 3, 4, 1, 5} //남
		};

		int[][] dir = new int[][]{ // 동서북남
				{0, 1}, {0, -1}, {-1, 0}, {1, 0}
		};

		int[] dice = new int[]{0, 0, 0, 0, 0, 0};
		int r = x, c = y;

		for (int i = 0; i < k; i++) {
			var cmd = sc.nextInt() - 1;
			// simulate

			int nr = r + dir[cmd][0];
			int nc = c + dir[cmd][1];

			if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
				continue;
			}

			int[] t = transition[cmd];
			int[] nextDice = new int[7];

			// copy values
			for (int idx = 0; idx < 6; idx++) {
				int nextIdx = t[idx] - 1;
				nextDice[nextIdx] = dice[idx];
			}

			if (map[nr][nc] == 0) {
				map[nr][nc] = nextDice[0];
//				nextDice[0] = 0; // <-------- 이 라인때문에 1시간 날림... 문제 잘못 이해함ㅠㅠㅠㅠㅠ
			} else {
				nextDice[0] = map[nr][nc];
				map[nr][nc] = 0;
			}

			for (int idx = 0; idx < 6; idx++) {
				dice[idx] = nextDice[idx];
			}

//			System.out.println("cmd = " + cmd+" r,c = "+r+" "+c);
			System.out.println(dice[5]);

//			for (int j = 0; j < 6; j++) {
//				System.out.print(dice[j] + " ");
//			}
//			System.out.println();

			r = nr;
			c = nc;
		}
	}
}
