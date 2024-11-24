package boj.impl.gold5.p20207;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

//	static boolean[][] map = new boolean[366][1000+ 1];

	public static void main(String[] args) {
		// sort. pq?
		// 2차원 배열? =>  365* 1000칸
		// right & down

		// dfs?, visited

		// 1. 색칠 단계
		// 2. 계산 단계: dfs. 덩어리 구분때문에. => dfs 안써도 될듯

		// 높이배열만 유지?
		var scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		PriorityQueue<Time> times = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			int s = scanner.nextInt();
			int e = scanner.nextInt();
			times.add(new Time(s, e));
		}

		while (!times.isEmpty()) {
			var here = times.poll();
			int s = here.s;
			int e = here.e;

			for (int r = 1; r <= n; r++) {
				boolean possible = true;
				for (int c = s; c <= e; c++) {
					if (map[r][c] == 1) {
						possible = false;
						break;
					}
				}

				if (possible) {
					for (int c = s; c <= e; c++) {
						map[r][c] = 1;
					}
					break;
				}
			}
		}

		int ans = 0;

		for (int r = 0; r <= n; r++) {
			for (int c = 0; c <= 12; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}

		for (int r = 0; r <= n; r++) {
			for (int c = 0; c <= 365; c++) {
				if (map[r][c] == 0 || visited[r][c]) {
					continue;
				}

				maxR = r;
				minR = r;
				maxC = c;
				minC = c;

				dfs(r, c);
				ans += (maxR - minR + 1) * (maxC - minC + 1);
//				System.out.println(maxR + " " + minR + " " + maxC + " " + minC + " " + ans);
			}
		}

		System.out.println(ans);
	}

	static int[][] map = new int[1000 + 1][365 + 1];
	static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static boolean[][] visited = new boolean[1000 + 1][365 + 1];
	static int maxR, minR, maxC, minC;

	static void dfs(int r, int c) {
//		visited[r][c] = true;

		for (var dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			if (nr < 0 || nr > 1000 || nc < 0 || nc > 365 || visited[nr][nc] || map[nr][nc] == 0) {
				continue;
			}

			maxR = Math.max(maxR, nr);
			minR = Math.min(minR, nr);

			maxC = Math.max(maxC, nc);
			minC = Math.min(minC, nc);
			visited[nr][nc] = true;
			dfs(nr, nc);
		}
	}

	static class Time implements Comparable<Time> {

		public int s, e;

		Time(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Time o) {
//			System.out.println(this.s+" "+o.s);
			if (this.s == o.s) {
				return -Integer.compare(this.e, o.e);
			} else {
				return Integer.compare(this.s, o.s);
			}
		}

		@Override
		public String toString() {
			return this.s + "," + this.e + " ";
		}
	}


}
