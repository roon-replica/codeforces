package boj.after2502.p7569;

public class Main {

	public static void main(String[] args) {
		// r,c,h
		// bfs, max time
		// dir: 3차원

		var scanner = new java.util.Scanner(System.in);
		int C = scanner.nextInt();
		int R = scanner.nextInt();
		int H = scanner.nextInt();

		int[][][] map = new int[H][R][C];
		int[][][] time = new int[H][R][C];

		for (int hh = 0; hh < H; hh++) {
			for (int rr = 0; rr < R; rr++) {
				for (int cc = 0; cc < C; cc++) {
					map[hh][rr][cc] = scanner.nextInt();
//					time[hh][rr][cc] = Integer.MAX_VALUE;
					if(map[hh][rr][cc] == 0){
						time[hh][rr][cc] = Integer.MAX_VALUE;
					}
				}
			}
		}

		var queue = new java.util.LinkedList<int[]>();

		for (int hh = 0; hh < H; hh++) {
			for (int rr = 0; rr < R; rr++) {
				for (int cc = 0; cc < C; cc++) {
					if (map[hh][rr][cc] == 1) {
						queue.add(new int[]{hh, rr, cc, 0});
					}
				}
			}
		}

		int[][][] dirs = new int[][][]{
				{{0, 0, 1}},
				{{0, 0, -1}},
				{{0, 1, 0}},
				{{0, -1, 0}},
				{{1, 0, 0}},
				{{-1, 0, 0}}
		};

		int ans = 0;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int h = cur[0];
			int r = cur[1];
			int c = cur[2];
			int t = cur[3];

			for (var dir : dirs) {
				int nh = h + dir[0][0];
				int nr = r + dir[0][1];
				int nc = c + dir[0][2];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C || nh < 0 || nh >= H) {
					continue;
				}

				if (t + 1 >= time[nh][nr][nc]) {
					continue;
				}

				if(map[nh][nr][nc] == -1){
					continue;
				}

				time[nh][nr][nc] = t+1;
				queue.add(new int[]{nh, nr, nc, t + 1});
//				ans = Math.min(ans, t + 1);
			}
		}

		for (int hh = 0; hh < H; hh++) {
			for (int rr = 0; rr < R; rr++) {
				for (int cc = 0; cc < C; cc++) {
//					if(map[hh][rr][cc] == -1 ){
//						continue;
//					}
					if (map[hh][rr][cc] == 0 && time[hh][rr][cc] == Integer.MAX_VALUE) {
						ans = Integer.MAX_VALUE;
						break;
					}
					ans = Math.max(ans, time[hh][rr][cc]);
//					System.out.print(time[hh][rr][cc] + " ");
				}
//				System.out.println();
			}
//			System.out.println();
		}

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

}
