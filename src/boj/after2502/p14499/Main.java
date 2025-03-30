package boj.after2502.p14499;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);

		N = scanner.nextInt();
		M = scanner.nextInt();
		int R0 = scanner.nextInt();
		int C0 = scanner.nextInt();
		int K = scanner.nextInt();

		map = new int[N + 1][M + 1];
		for (int rr = 0; rr < N; rr++) {
			for (int cc = 0; cc < M; cc++) {
				map[rr][cc] = scanner.nextInt();
			}
		}

		var dice = new Dice(R0, C0);

		for (int kk = 0; kk < K; kk++) {
			int cmd = scanner.nextInt();
			var moved = dice.move(cmd);
//			var digits = Arrays.stream(dice.digits).mapToObj(d -> String.valueOf(d)).collect(Collectors.joining(" "));
//			System.out.println(dice.digits[6]+", "+digits);
			if (moved) {
				System.out.println(dice.digits[6]);
			}
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] dirMap = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //동서북남

	static class Dice {

		int[] digits;
		int r, c;

		public Dice(int r, int c) {
			this.digits = new int[7]; // from index 1. 주어진 주사위 배치대로
			this.r = r;
			this.c = c;
		}

		public boolean move(int dir) {
			int nr = r + dirMap[dir - 1][0];
			int nc = c + dirMap[dir - 1][1];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				return false;
			}

			var tmp = new int[7];
			for (int i = 1; i <= 6; i++) {
				tmp[i] = digits[i];
			}

			switch (dir) {

				case 1: // 동
					digits[1] = tmp[3];
					digits[2] = tmp[2];
					digits[3] = tmp[6];
					digits[4] = tmp[1];
					digits[5] = tmp[5];
					digits[6] = tmp[4];
					break;
				case 2: // 서
					digits[1] = tmp[4];
					digits[2] = tmp[2];
					digits[3] = tmp[1];
					digits[4] = tmp[6];
					digits[5] = tmp[5];
					digits[6] = tmp[3];
					break;
				case 3: // 북
					digits[1] = tmp[2];
					digits[2] = tmp[6];
					digits[3] = tmp[3];
					digits[4] = tmp[4];
					digits[5] = tmp[1];
					digits[6] = tmp[5];
					break;
				case 4: // 남
					digits[1] = tmp[5];
					digits[2] = tmp[1];
					digits[3] = tmp[3];
					digits[4] = tmp[4];
					digits[5] = tmp[6];
					digits[6] = tmp[2];
					break;
			}

			if (map[nr][nc] == 0) {
				map[nr][nc] = digits[1];
//				digits[1] = 0;
			} else {
				digits[1] = map[nr][nc];
				map[nr][nc] = 0;
			}

			r = nr;
			c = nc;
			return true;
		}
	}
}
