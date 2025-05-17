package boj.after2502.p16928;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);
		// dp[][]  = 도달하기까지 최소 주사위 굴린 횟수?
		// 2차원 맵이란 정보는 아무 필요없는듯?
		// 걍 반복문 1번 돌리면 되나?

		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int[] jumps = new int[101];

		for (int i = 0; i < N; i++) {
			int from = scanner.nextInt();
			int to = scanner.nextInt();
			jumps[from] = to;
		}

		for (int i = 0; i < M; i++) {
			int from = scanner.nextInt();
			int to = scanner.nextInt();
			jumps[from] = to;
		}

		int[] minMove = new int[101];
		for (int i = 2; i <= 100; i++) {
			minMove[i] = 101;
		}

		var q = new LinkedList<Data>();
		q.add(new Data(1, 0));

		while (!q.isEmpty()) {
			var here = q.poll();
			int loc = here.loc;
			int move = here.move;

			for (int nextLoc = loc + 1; (nextLoc <= (loc + 6)) && (nextLoc <= 100); nextLoc++) {
				if (jumps[nextLoc] == 0) { // 이 분기문 빼먹어서 틀렸었다...
					if (minMove[nextLoc] >= move + 1) {
						minMove[nextLoc] = move + 1;
						q.add(new Data(nextLoc, minMove[nextLoc]));
					}
				} else {
					int jump = jumps[nextLoc];
					if (jump != 0) {
						if (minMove[jump] >= move + 1) {
							minMove[jump] = move + 1;
							q.add(new Data(jump, minMove[jump]));
						}
					}
				}
			}
		}

//		for(int i=1;i<=100;i++){
//			System.out.print(minMove[i]+" ");
//		}
//		System.out.println();

		System.out.println(minMove[100]);
	}

	static class Data {

		int loc;
		int move;

		public Data(int loc, int move) {
			this.loc = loc;
			this.move = move;
		}
	}
}
