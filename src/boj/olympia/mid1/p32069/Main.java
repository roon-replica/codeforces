package boj.olympia.mid1.p32069;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//		 bfs?
//		 visited set? 50만개
//		 {x1,x2,x3} => bfs{x1-1, x1+1, x2-1, x2+1, x3-1, x3+1} until visited 50만개
//		set<Long> visited
//		visited => continue
//		queue?

		var scanner = new Scanner(System.in);
		long L = scanner.nextLong();
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		var arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = scanner.nextLong();
		}

		var queue = new LinkedList<Info>();
		var visited = new HashSet<Long>();

		for (var a : arr) {
			queue.add(new Info(a, 0));
		}

		StringBuilder ans = new StringBuilder();
		for (; !queue.isEmpty() && K > 0; ) {
			var here = queue.poll();
			var location = here.location;
			var dist = here.dist;
			if (visited.contains(location) || location < 0 || location > L) {
				continue;
			}
			visited.add(location);
			ans.append(dist + "\n");
			K--;

			queue.add(new Info(location - 1, dist + 1));
			queue.add(new Info(location + 1, dist + 1));
		}

		System.out.println(ans);

	}

	static class Info {

		long location;
		int dist;

		public Info(long location, int dist) {
			this.location = location;
			this.dist = dist;
		}
	}

}
