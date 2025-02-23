package boj.after2502.p11404;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		var reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
//		var scanner = new java.util.Scanner(System.in);

//		int N = scanner.nextInt();
//		int M = scanner.nextInt();
		int N = Integer.parseInt(reader.readLine());
		int M = Integer.parseInt(reader.readLine());

		int[][] cost = new int[N][N];

		int max = 1_000_000_00;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j) {
					cost[i][j] = max;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			var input = reader.readLine().split(" ");
			int from = Integer.parseInt(input[0]) - 1;
			int to = Integer.parseInt(input[1]) - 1;
			int c = Integer.parseInt(input[2]);

			cost[from][to] = Math.min(cost[from][to], c);
		}

		for (int h = 0; h < N; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cost[i][j] = Math.min(cost[i][j], cost[i][h] + cost[h][j]);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print((cost[i][j] == max ? 0 : cost[i][j]) + " ");
			}
			System.out.println();
		}
	}

}
