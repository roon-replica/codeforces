package boj.after2502.p2636;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);
		// 공백도 밖,안을 구분해야함
		// 그게 아니라 검정칸에서 시작해서 바깥라인을 구별해야겠다?
		// 각 row의 min max c, 각 col의 min max r,

		// 아 걍 사이드에서부터 접근해가면 되네..
		// 다른 방법으로 구현하려면 복잡도가 미친듯이 올라가네..

		R = scanner.nextInt();
		C = scanner.nextInt();

		map = new int[R + 1][C + 1];
		meltCountMap = new int[R + 1][C + 1];
		visited = new boolean[R + 1][C + 1];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[r][c] = scanner.nextInt();
			}
		}

		int t = 0;
		int lastCount = 0;
		for (; ; t++) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					meltCountMap[r][c] = 0;
					visited[r][c] = false;
				}
			}

			// calc melt count
			for (int r = 0; r < R; r++) {
				if (map[r][0] == 0) {
					dfs(r, 0);
				}
				if (map[r][C - 1] == 0) {
					dfs(r, C - 1);
				}
			}

			for (int c = 0; c < C; c++) {
				if (map[0][c] == 0) {
					dfs(0, c);
				}
				if (map[R - 1][c] == 0) {
					dfs(R - 1, c);
				}
			}

			int count = 0;

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (meltCountMap[r][c] == 1 && map[r][c] != 0) {
						map[r][c] = 0;
						count++;
					}
				}
			}

			// 각 덩이에서 가장자리 추출
			if (count == 0) {
				break;
			}
			lastCount = count;

//			for (int r = 0; r < R; r++) {
//				for (int c = 0; c < C; c++) {
//					System.out.print(meltCountMap[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		System.out.println(t);
		System.out.println(lastCount);
	}

	static int R, C;
	static int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] map, meltCountMap;
	static boolean[][] visited;

	static void dfs(int r, int c) {
		visited[r][c] = true;

		for (var dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) {
				continue;
			}

			if (map[nr][nc] == 1) {
				meltCountMap[nr][nc] = 1;
				continue;
			}

			dfs(nr, nc);
		}
	}
}
