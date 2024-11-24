package boj.impl.gold5;

import java.util.Scanner;

public class P7682 {

	public static void main(String[] args) {
		// .xx
		// x.x
		// ooo

		// oxo
		// xox
		// oxo

		// x.o
		// o..
		// x..

		// 1. o 승 -> n(o) == n(x) 여야함
		// 2. x 승 -> n(x) = n(o)+1
		// 3. 승부가 안났다
		//  .가 있다 -> invalid
		//  .가 없다 -> n(x) = n(o)+1
		// 4. o승 & x승 -> invalid
		// 3개 연속 판단 함수

		var scanner = new Scanner(System.in);

		for (var s = scanner.next(); !s.equals("end"); s = scanner.next()) {
			char[][] map = new char[3][3];
			int cntO = 0;
			int cntX = 0;

			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					map[r][c] = s.charAt(r * 3 + c);

					if (map[r][c] == 'O') {
						cntO++;
					} else if (map[r][c] == 'X') {
						cntX++;
					}
				}
			}

			boolean dot = (cntO + cntX != 3 * 3);

			int winO = countWin(map, 'O');
			int winX = countWin(map, 'X');

			boolean answer = true;
			if (winO > 0 && winX > 0) {
				answer = false;
			} else if (winO > 0) {
				answer = (cntO == cntX);
			} else if (winX > 0) {
				answer = (cntX == cntO + 1);
			} else { // winO =0 && winX = 0
				if (dot) {
					answer = false;
				} else {
					answer = (cntX == cntO + 1);
				}
			}

			System.out.println(answer ? "valid" : "invalid");


		}
	}

	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}; //좌상부터
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

	static int countWin(char[][] map, char query) {
		int ret = 0;

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				char here = map[r][c];
				if (here != query) {
					continue;
				}

				int sameChCnt;

				for (int dir = 0; dir < dr.length; dir++) {
					sameChCnt = 0;

					for (int d = 0; d <= 2; d++) {
						int nr = r + dr[dir] * d;
						int nc = c + dc[dir] * d;

						if (nr < 0 || nr > 2 || nc < 0 || nc > 2) {
							break;
						}

						if (here != map[nr][nc]) {
							break;
						} else {
							sameChCnt++;
						}
					}

					if (sameChCnt == 3) {
						ret++;
					}
				}
			}
		}

		if (ret % 2 == 1) {
			throw new RuntimeException("never");
		}
		return ret / 2;
	}


}
