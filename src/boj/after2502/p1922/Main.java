package boj.after2502.p1922;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();

		List<Edge> edges = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int cost = scanner.nextInt();
			edges.add(new Edge(a, b, cost));
		}

		Collections.sort(edges);
		int connections = 0;
		int minCost = 0;
		var unionFind = new UnionFind(N + 1);

		for (int i = 0; i < M && connections < N - 1; i++) {
			var edge = edges.get(i);
			int a = edge.a;
			int b = edge.b;
			int cost = edge.cost;

			boolean newMerge = unionFind.union(a, b);

			if(newMerge){
				minCost += cost;
				connections++;
			}
		}

		System.out.println(minCost);
	}

	static class UnionFind {

		int[] parent;
		int[] depth;

		public UnionFind(int size) {
			parent = new int[size + 1];
			depth = new int[size + 1];

			for (int i = 0; i < size + 1; i++) {
				parent[i] = i;
			}
		}

		boolean union(int v1, int v2) {
			int root1 = find(v1);
			int root2 = find(v2);
			if (root1 == root2) {
				return false; // already same
			}

			if (depth[root1] < depth[root2]) {
				parent[root1] = root2;
			} else if (depth[root1] > depth[root2]) {
				parent[root2] = root1;
			} else {
				parent[root2] = root1;
				depth[root1]++;
			}

			return true;
		}

		int find(int v1) {
			if (parent[v1] != v1) {
				parent[v1] = find(parent[v1]);
			}

			return parent[v1];
		}
	}

	static class Edge implements Comparable<Edge> {

		int a, b, cost;

		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge other) {
			return cost - other.cost;
		}
	}
}
