package boj.after2502.p20056;

import boj.Main2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);
		// fireball 만들고 시뮬?

		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int K = scanner.nextInt();

		Map[][] map = new Map[N][N];
		for (int rr = 0; rr < N; rr++) {
			for (int cc = 0; cc < N; cc++) {
				map[rr][cc] = new Map();
			}
		}
		var fireballs = new ArrayList<Fireball>();

		for (int i = 0; i < M; i++) {
			int r = scanner.nextInt() - 1;
			int c = scanner.nextInt() - 1;
			int m = scanner.nextInt();
			int s = scanner.nextInt();
			int d = scanner.nextInt();

			fireballs.add(new Fireball(r, c, m, s, d));
		}

		int ans = 0;

		for (int i = 0; i < K; i++) {
			ans = 0;

			for (var fb : fireballs) {
				int r = fb.r;
				int c = fb.c;
				int m = fb.m;
				int s = fb.s;
				int d = fb.d;

				int nr = (r + s * dirs[d][0] + N * s) % N;
				int nc = (c + s * dirs[d][1] + N * s) % N;

				map[nr][nc].add(new Fireball(nr, nc, m, s, d));
			}

			var newFireballs = new ArrayList<Fireball>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					var fireballsHere = map[r][c].fireballs;
					if (fireballsHere.isEmpty()) {
						continue;
					}

					if (fireballsHere.size() == 1) {
						newFireballs.add(fireballsHere.get(0));
						continue;
					}

					int mSum = 0;
					int sSum = 0;
					boolean dirOdd = true;
					boolean dirEven = true;
					int rr = fireballsHere.get(0).r;
					int cc = fireballsHere.get(0).c;

					for (var f : fireballsHere) {
						mSum += f.m;
						sSum += f.s;
						if (f.d % 2 == 0) {
							dirOdd = false;
						}
						if (f.d % 2 == 1) {
							dirEven = false;
						}
					}

					mSum /= 5;
					sSum /= fireballsHere.size();

					if (mSum == 0) {
						continue;
					}

					int[] dirs;
					if (dirEven || dirOdd) {
						dirs = new int[]{0, 2, 4, 6};
					} else {
						dirs = new int[]{1, 3, 5, 7};
					}

					for (int j = 0; j < 4; j++) {
						newFireballs.add(new Fireball(rr, cc, mSum, sSum, dirs[j]));
					}
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c].clear();
				}
			}

			fireballs = newFireballs;

			for(var f: fireballs){
				ans += f.m;
			}

		}

		System.out.println(ans);
	}

	static int[][] dirs = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

	private static class Map {

		List<Fireball> fireballs;

		public Map() {
			this.fireballs = new ArrayList<>();
		}

		public void add(Fireball fireball) {
			this.fireballs.add(fireball);
		}

		public void clear() {
			this.fireballs.clear();
		}
	}

	private static class Fireball {

		int r, c, m, s, d;

		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
