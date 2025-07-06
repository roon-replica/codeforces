package boj.after2507;

import java.io.IOException;
import java.util.Scanner;

public class 지뢰찾기 {

	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);

		// 테두리밖에 판단못할듯?
		// 지뢰아닌걸로 밝혀짐: '-'
		// 지뢰인걸로 밝혀짐: '*'
		// 모름: '#'

		// 주변 칸의 수 == 지뢰수 -> 무조건 지뢰

		N = scanner.nextInt();
		map = new char[N][N];
		for (int r = 0; r < N; r++) {
			var line = scanner.next();
			for (int c = 0; c < N; c++) {
				map[r][c] = line.charAt(c);
			}
		}

		int r, c;

		r = 0;
		c = 0;

		for (; c < N; c++) {
			check(r, c);
		}
		c = N - 1;

		for (; r < N; r++) {
			check(r, c);
		}
		r = N - 1;

		for (; c > 0; c--) {
			check(r, c);
		}
		c = 0;

		for (; r > 0; r--) {
			check(r, c);
		}

		int count = 0;
		for (int rr = 0; rr < N; rr++) {
			for (int cc = 0; cc < N; cc++) {
				if (map[rr][cc] == '#' || map[rr][cc] == '*') {
					count++;
				}
			}
		}

		System.out.println(count);
	}

	static int N;
	static char[][] map;
	static int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

	private static void check(int r, int c) {
		int here = map[r][c] - '0';
		var count = count(r, c);

		if (count == here) {
			mark(r, c, '*');
		} else if (count > here) {
			mark(r, c, '-');
		}
	}

	private static int count(int r, int c) {
		int count = 0;
		for (var dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || (map[nr][nc] >= '0' && map[nr][nc] <= '9') || map[nr][nc] == '-') {
				continue;
			}
			count++;
		}
		return count;
	}

	private static void mark(int r, int c, char ch) {
		for (var dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || (map[nr][nc] >= '0' && map[nr][nc] <= '9') || map[nr][nc] != '#') {
				continue;
			}

			map[nr][nc] = ch;
		}
	}
}
