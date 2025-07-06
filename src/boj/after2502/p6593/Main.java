package boj.after2502.p6593;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		scanner = new Scanner(System.in);

		// bfs in a layer. 갈수있는곳 마킹?
		// 아님 한방에 bfs

		while (true) {
			L = scanner.nextInt();
			R = scanner.nextInt();
			C = scanner.nextInt();

			if (L == 0 && R == 0 && C == 0) {
				break;
			}

			int escape = solve();

			System.out.println(escape == MAX ? "Trapped!" : "Escaped in " + escape + " minute(s).");
		}
	}

	static Scanner scanner;
	static int L, R, C;
	static int MAX = 1000000;

	static int solve() {
		var map = new char[L][R][C];
		var distMap = new int[L][R][C];
		for (int l = 0; l < L; l++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					distMap[l][r][c] = MAX; //max
				}
			}
		}

		var queue = new LinkedList<Data>(); // (l,r,c?), visited marking on map

		for (int l = 0; l < L; l++) {
			for (int r = 0; r < R; r++) {
				var line = scanner.next();
				for (int c = 0; c < C; c++) {
					map[l][r][c] = line.charAt(c);

					if (map[l][r][c] == 'S') {
						queue.add(new Data(l, r, c, 0));
						distMap[l][r][c] = 0;
					}
				}
			}
		}

		int escape = MAX;

		while (!queue.isEmpty()) {
			var here = queue.poll();
			int l = here.l;
			int r = here.r;
			int c = here.c;
			int dist = here.dist;

			if (map[l][r][c] == 'E') {
//				escape = distMap[l][r][c];
				escape = dist;
				break;
			}

			for (var dir : dirs) {
				int nl = l + dir[0];
				int nr = r + dir[1];
				int nc = c + dir[2];

				if (nl < 0 || nl >= L || nr < 0 || nr >= R || nc < 0 || nc >= C) {
					continue;
				}

				if (map[nl][nr][nc] == '#' || distMap[nl][nr][nc] <= dist + 1) {
					continue;
				}

				queue.add(new Data(nl, nr, nc, dist + 1));
				distMap[nl][nr][nc] = dist + 1;
			}
		}

		return escape;
	}

	static int[][] dirs = new int[][]{
			{1, 0, 0}, {-1, 0, 0},
			{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}
	};

	static class Data {

		public int l, r, c, dist;

		public Data(int l, int r, int c, int dist) {
			this.l = l;
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

}