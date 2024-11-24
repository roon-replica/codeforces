package boj.impl.gold5.p16926;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/**
		 *
		 껍질 직사각형
		 min(N, M) mod 2 = 0 => 짝수

		 돌리기
		 <- : c--
		 down : r++
		 -> : c++
		 up: r--

		 껍질별 리스트 시작 idx만 정해서 채워넣기?
		 fromIdx~ last, 0~fromIdx-1 : for(from, cnt; cnt< len; from= (from+1)%len)

		 r,c : 0,0
		 r-2,c-2 : 1,1
		 r-4,c-4: 2,2
		 ...

		 우, 하, 좌, 상 순서
		 */

		var scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int R = scanner.nextInt();

		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = scanner.nextInt();
			}
		}

		List<Integer>[] layers = new ArrayList[Math.min(n, m) / 2];
		for (int i = 0; i < Math.min(n, m) / 2; i++) {
			layers[i] = new ArrayList<>();
		}

		// 껍질별 우,하,좌,상 순으로 담기
		for (int i = 0; i < Math.min(n, m) / 2; i++) {
			int r = i;
			int c = i;

			for (; c < m - i; c++) { //우
				layers[i].add(map[r][c]);
			}

			r++;
			c--;
			for (; r < n - i; r++) { // 하
				layers[i].add(map[r][c]);
			}

			r--;
			c--;
			for (; c >= i; c--) { // 좌
				layers[i].add(map[r][c]);
			}

			c++;
			r--;
			for (; r > i; r--) { // 상
				layers[i].add(map[r][c]);
			}
		}

//		for (int i = 0; i < Math.min(n, m) / 2; i++) {
//			System.out.println(layers[i]);
//		}

		// 껍질별 회전시킨 인덱스부터 채워넣기. 우,하,좌,상 순으로
		var ans = new int[n][m];
		for (int i = 0; i < layers.length; i++) {
			int size = layers[i].size();
			int idx = R % size;

			int r = i, c = i;

			for (; c < m - i; c++, idx = (idx + 1) % size) { //우
				ans[r][c] = layers[i].get(idx);
			}

			r++;
			c--;
			for (; r < n - i; r++, idx = (idx + 1) % size) { // 하
				ans[r][c] = layers[i].get(idx);
			}

			r--;
			c--;
			for (; c >= i; c--, idx = (idx + 1) % size) { // 좌
				ans[r][c] = layers[i].get(idx);
			}

			c++;
			r--;
			for (; r > i; r--, idx = (idx + 1) % size) { // 상
				ans[r][c] = layers[i].get(idx);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println();
		}
	}

//	4 6 2
//	1 2 3 4 -1 -2
//	5 6 7 8 -3 -4
//	9 10 11 12 -5 -6
//	13 14 15 16 -7 -8

}
