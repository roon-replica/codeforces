package boj.gold5.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P15681 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int ROOT = scanner.nextInt();
		int Q = scanner.nextInt();

		List<Integer>[] adjList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adjList[i] = new ArrayList();
		}

		for (int i = 0; i < n - 1; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			adjList[u].add(v);
			adjList[v].add(u);
		}

		var visited = new boolean[n + 1];
		var q = new LinkedList<Integer>();
		q.add(ROOT);

		var parent = new int[n + 1];

		// 1. 트리 구성
		for (; !q.isEmpty(); ) {
			var here = q.poll();

			for (int child : adjList[here]) {
				if (visited[child]) {
					continue;
				}

				parent[child] = here;
				q.add(child);
			}

			visited[here] = true;
		}

//		for (int i = 1; i <= n; i++) {
//			System.out.print(parent[i] + " ");
//		}
//		System.out.println();

		var hasChild = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			hasChild[parent[i]] = true;
		}

		var q2 = new PriorityQueue<Node>();
		var count = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			count[i] = 1;
			if (!hasChild[i]) {
				q2.add(new Node(n + 1, i));
			}
		}

		for (int i = 1; i <= n; i++) {
			visited[i] = false;
		}

		// 2. 미리 계산. 트리를 타고 올라가며
		while (!q2.isEmpty()) {
			var here = q2.poll();

			int par = parent[here.node];
			count[par] += count[here.node];

			if (par == ROOT) {
				continue;
			}

			if (!visited[par]) {
				q2.add(new Node(here.depth - 1, par));
				visited[par] = true;
			}

			visited[here.node] = true;
		}

//		for(int i=1;i<=n;i++){
//			System.out.print(count[i]+" ");
//		}
//		System.out.println();

		for (int i = 0; i < Q; i++) {
			int root = scanner.nextInt();
			System.out.println(count[root]);
		}
	}

	private static class Node implements Comparable<Node> {

		int depth;
		int node;

		Node(int depth, int node) {
			this.depth = depth;
			this.node = node;
		}

		@Override
		public int compareTo(Node o) {
			return -Integer.compare(this.depth, o.depth);
		}
	}

}
