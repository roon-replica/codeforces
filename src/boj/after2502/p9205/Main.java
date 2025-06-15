package boj.after2502.p9205;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);

		// 점들을 잇는 간선의 최대 길이가 1000 이하여야 하는데.
		// 이런 간선 길이로 전부 연결될 수 있냐는 거니까 UnionFind로도 되겠고
		// 그냥 n이 작으니까 n제곱으로 다 따져봐도 될거같은데

		int T = scanner.nextInt();
		for (int tt = 0; tt < T; tt++) {
			int N = scanner.nextInt();
			var set = new UnionFind(N + 2);

			var list = new ArrayList<Pair>();
			for (int i = 0; i < N + 2; i++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				list.add(new Pair(x, y));
			}

			for (int i = 0; i < list.size(); i++) {
				var here = list.get(i);
				for (int j = 0; j < list.size(); j++) {
					if (i == j) {
						continue;
					}
					var next = list.get(j);

					if (Math.abs(here.x - next.x) + Math.abs(here.y - next.y) > 1000) {
						continue;
					}

					set.union(i, j);
				}
			}

			System.out.println(set.find(0) == set.find(N + 1) ? "happy" : "sad");
		}
	}

	private static class Pair {

		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class UnionFind {

		int[] group;
		int[] depth;

		public UnionFind(int size) {
			group = new int[size + 1];
			depth = new int[size + 1];
			for (int i = 0; i < group.length; i++) {
				group[i] = i;
			}
		}

		public int find(int v) {
			if (group[v] != v) {
				group[v] = find(group[v]);
			}

			return group[v];
		}

		public boolean union(int v1, int v2) {
			int group1 = find(v1);
			int group2 = find(v2);
			if (group1 == group2) {
				return false; // already same group
			}

			if (depth[group1] < depth[group2]) {
				group[group1] = group2;
			} else if (depth[group1] > depth[group2]) {
				group[group2] = group1;
			} else {
				group[group1] = group2;
				depth[group2]++;
			}

			return true; // newly merged into same group
		}
	}
}
