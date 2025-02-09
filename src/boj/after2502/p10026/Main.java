package boj.after2502.p10026;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		// 구냥 bfs 2번 하면 되겠네
		var scaneer = new java.util.Scanner(System.in);
		N = scaneer.nextInt();
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			var input = scaneer.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		var queue = new LinkedList<int[]>();
		var visited = new boolean[N][N];
		int ans1 = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c]) {
					continue;
				}
				ans1++;

				queue.add(new int[]{r, c});
				while (!queue.isEmpty()) {
					var cur = queue.poll();
					int cr = cur[0];
					int cc = cur[1];
					for (int[] dir : dirs) {
						int nr = cr + dir[0];
						int nc = cc + dir[1];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
							continue;
						}
						if (visited[nr][nc]) {
							continue;
						}
						if (map[nr][nc] != map[cr][cc]) {
							continue;
						}
						visited[nr][nc] = true;
						queue.add(new int[]{nr, nc});
					}
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				visited[r][c] = false;
			}
		}

		int ans2 = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c]) {
					continue;
				}
				ans2++;

				queue.add(new int[]{r, c});
				while (!queue.isEmpty()) {
					var cur = queue.poll();
					int cr = cur[0];
					int cc = cur[1];
					for (int[] dir : dirs) {
						int nr = cr + dir[0];
						int nc = cc + dir[1];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
							continue;
						}
						if (visited[nr][nc]) {
							continue;
						}
						if (map[r][c] == 'R' || map[r][c] == 'G') {
							if (map[nr][nc] == 'B') {
								continue;
							}
						} else {
							if (map[nr][nc] != map[cr][cc]) {
								continue;
							}
						}

						visited[nr][nc] = true;
						queue.add(new int[]{nr, nc});
					}
				}
			}
		}

		System.out.println(ans1 + " " + ans2);


	}

	static int N;
	static char[][] map;
	static int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

}
