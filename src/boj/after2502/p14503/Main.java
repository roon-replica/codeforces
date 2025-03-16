package boj.after2502.p14503;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);

		int R = scanner.nextInt();
		int C = scanner.nextInt();

		int r = scanner.nextInt();
		int c = scanner.nextInt();
		int dir = scanner.nextInt();

		int[][] map = new int[R][C];
		for (int rr = 0; rr < R; rr++) {
			for (int cc = 0; cc < C; cc++) {
				map[rr][cc] = scanner.nextInt();
			}
		}

		int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int count = 0;

		while (true) {
//			System.out.println(r + " " + c);
			if (map[r][c] == 0) {
				map[r][c] = 2;
				count++;
				continue;
			}

			int nextDir = -1;

			for (int i = 0; i < 4; i++) {
				var d = dirs[(dir + i) % 4];
				int nr = r + d[0];
				int nc = c + d[1];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] != 0) {
					continue;
				}

				nextDir = (dir + i) % 4; // 북 동 남 서 = 0 1 2 3
			}

			if (nextDir == -1) {
				r -= dirs[dir][0];
				c -= dirs[dir][1];
				if (r < 0 || r >= R || c < 0 || c >= C || map[r][c] == 1) {
					break;
				}
			} else {
				dir = nextDir;
				r += dirs[dir][0];
				c += dirs[dir][1];
				map[r][c] = 2;
				count++;
			}
		}

		System.out.println(count);


	}
}
