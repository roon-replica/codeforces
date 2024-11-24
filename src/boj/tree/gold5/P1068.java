package boj.tree.gold5;

import java.util.LinkedList;
import java.util.Scanner;

public class P1068 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		var parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = scanner.nextInt();
		}
		int remove = scanner.nextInt();

		var q = new LinkedList<Integer>();
		q.add(remove);
		var removed = new boolean[n];

		for (; !q.isEmpty(); ) {
			int here = q.poll();
			removed[here] = true;

			for (int i = 0; i < n; i++) {
				if (parent[i] == here) {
					q.add(i);
				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			System.out.print(removed[i] + " ");
//		}
//		System.out.println();

		int answer = 0;

		for (int i = 0; i < n; i++) {
			if (removed[i]) {
				continue;
			}

			boolean isLeaf = true;
			for (int j = 0; j < n; j++) {
				if(removed[j]) continue;

				if (parent[j] == i) {
					isLeaf = false;
					break;
				}
			}

			if(isLeaf){
				answer++;
			}
		}

		System.out.println(answer);


	}

	static class Node {

		int node;
		int count;

		public Node(int node, int count) {
			this.node = node;
			this.count = count;
		}
	}

}
