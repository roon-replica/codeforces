package boj.after2507;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 십자가_2개_놓기17085 {
	public static void main(String[] args) throws IOException {
		// 다해봐야할거같음
		// 모든 십자가1 놓기, 모든 십자가2 놓기
		// 곱 최대값

		var scanner = new Scanner(System.in);
		R = scanner.nextInt();
		C = scanner.nextInt();
		map = new char[R][C];
		tempMap = new char[R][C];
		for (int i = 0; i < R; i++) {
			var line = scanner.next();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		makeCross();

		for (var cross1 : crosses) {
			for (var cross2 : crosses) {
				simul(cross1, cross2);
			}
		}

//		simul(crosses.get(1), crosses.get(1));

		System.out.println(ans);
	}

	static int R, C;
	static char[][] map, tempMap;
	static int ans;

	static void simul(char[][] cross1, char[][] cross2) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				for (int r2 = 0; r2 < R; r2++) {
					for (int c2 = 0; c2 < C; c2++) {
						initMap();
						boolean possible = putCross(r, c, cross1);
						possible &= putCross(r2, c2, cross2);
//						boolean possible=false;

//						if (r == 0 && c == 0 && r2 == 2 && c2 == 3) {
//							System.out.println(possible + " " + cross1.length + " " + cross2.length);
//							for (int x = 0; x < R; x++) {
//								for (int y = 0; y < C; y++) {
//									System.out.print(tempMap[x][y]);
//								}
//								System.out.println();
//							}
//						}

						int area = (cross1.length * 2 - 1) * (cross2.length * 2 - 1);
						if (possible) {
							ans = Math.max(ans, area);
						}
					}
				}
			}
		}
	}

	static void initMap() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				tempMap[r][c] = map[r][c];
			}
		}
	}

	static boolean putCross(int r0, int c0, char[][] cross) {
		int len = cross.length;

		if (r0 + len > R || c0 + len > C) {
			return false;
		}

		for (int dr = 0; dr < len; dr++) {
			for (int dc = 0; dc < len; dc++) {
				if (tempMap[r0 + dr][c0 + dc] == '.' && cross[dr][dc] == '*') {
					return false;
				}

				if (tempMap[r0 + dr][c0 + dc] == '#' && cross[dr][dc] == '*') {
					tempMap[r0 + dr][c0 + dc] = '.';
				}
			}
		}

		return true;
	}

	static List<char[][]> crosses = new ArrayList<char[][]>();

	static void makeCross() {
		// 1 3 5 7 9 11 13 15
		for (int sz = 0; sz <= Math.max(R, C); sz++) {
			int len = sz * 2 + 1;
			var cross = new char[len][len];
			for (int r = 0; r < len; r++) {
				for (int c = 0; c < len; c++) {
					cross[r][c] = '.';
				}
			}

			int mid = len / 2;
			for (int i = 0; i < len; i++) {
				cross[i][mid] = '*';
				cross[mid][i] = '*';
			}

			crosses.add(cross);
		}

		//		var cross = crosses.get(1);
//		for (int r = 0; r < 3; r++) {
//			for (int c = 0; c < 3; c++) {
//				System.out.print(cross[r][c] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
	}
}
