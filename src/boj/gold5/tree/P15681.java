package boj.gold5.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class P15681 {

	static int MAX = 100001;
	static List<Integer>[] adjList = new ArrayList[MAX];
	static int[] count = new int[MAX];

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int ROOT = scanner.nextInt();
		int Q = scanner.nextInt();

		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList();
		}

		for (int i = 0; i < n - 1; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			adjList[u].add(v);
			adjList[v].add(u);
		}

		dfs(ROOT, -1);

		for (int i = 0; i < Q; i++) {
			int root = scanner.nextInt();
			System.out.println(count[root]);
		}
	}

	static int dfs(int here, int parent) {
		count[here] = 1;

		for (int child : adjList[here]) {
			if (child == parent) {
				continue;
			}

			if (count[child] != 0) {
				count[here] += count[child];
			} else {
				count[here] += dfs(child, here);
			}
		}

		return count[here];
	}
}
