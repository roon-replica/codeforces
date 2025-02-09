package boj.after2502.p14500;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// 10몇가지 경우에 대해 다 시뮬레이션해보면 될텐데 좀 많다
		var scanner = new java.util.Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = scanner.nextInt();
			}
		}

		int ans = 0;

		var shapes = new ArrayList<int[][]>();
		shapes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}});
		shapes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}});

		shapes.add(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}});

		// 8가지
		shapes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 2}});
		shapes.add(new int[][]{{2, 0}, {2, 1}, {1, 1}, {0, 1}});
		shapes.add(new int[][]{{0, 0}, {1, 0}, {1, 1}, {1, 2}});
		shapes.add(new int[][]{{0, 0}, {0, 1}, {1, 0}, {2, 0}});

		shapes.add(new int[][]{{1, 0}, {1, 1}, {1, 2}, {0,2}});
		shapes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}}); // o
		shapes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {2, 1}}); // o
		shapes.add(new int[][]{{0, 0}, {0, 1}, {1, 1}, {2, 1}}); // o

		// 4가지
		shapes.add(new int[][]{{0, 1}, {1, 1}, {1, 0}, {2, 0}});
		shapes.add(new int[][]{{0, 0}, {1, 0}, {1, 1}, {2, 1}});
		shapes.add(new int[][]{{1, 0}, {1, 1}, {0, 1}, {0, 2}});
		shapes.add(new int[][]{{0, 0}, {0, 1}, {1, 1}, {1, 2}});

		//4가지
		shapes.add(new int[][]{{1, 0}, {0, 1}, {1, 1}, {2, 1}});
		shapes.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}});
		shapes.add(new int[][]{{0, 1}, {1, 0}, {1, 1}, {1, 2}});
		shapes.add(new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 1}});

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				for (int[][] shape : shapes) {
					int sum = 0;
					boolean isOk = true;
					for (int[] pos : shape) {
						int nr = r + pos[0];
						int nc = c + pos[1];
						if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
							isOk = false;
							break;
						}
						sum += map[nr][nc];
					}
					if (isOk) {
						ans = Math.max(ans, sum);
					}
				}
			}
		}

		System.out.println(ans);

	}

}
