package boj.after2502.p21608;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);
		N = scanner.nextInt();
		var map = new int[N][N];
		var likes = new HashSet[N * N + 1];
		for (int i = 0; i < N * N + 1; i++) {
			likes[i] = new HashSet<Integer>();
		}

		for (int i = 0; i < N * N; i++) {
			var student = scanner.nextInt();
			for (int j = 0; j < 4; j++) {
				likes[student].add(scanner.nextInt());
			}

			var loc = sort(student, map, likes[student]);
			map[loc.r][loc.c] = student;
		}

//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}

		int likeSum = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int likeCount = 0;
				for (var dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					if (likes[map[r][c]].contains(map[nr][nc])) {
						likeCount++;
					}
				}
				likeSum += scores[likeCount];
			}
		}

		System.out.println(likeSum);
	}


	static int N;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[] scores = new int[]{0, 1, 10, 100, 1000};

	static Data sort(int student, int[][] map, HashSet<Integer> likes) {
		var candidates = new ArrayList<Data>();

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != 0) {
					continue;
				}
				int likeCount = 0;
				int emptyCount = 0;

				for (var dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
						continue;
					}
					if (likes.contains(map[nr][nc])) {
						likeCount++;
					}
					if (map[nr][nc] == 0) {
						emptyCount++;
					}
				}
				candidates.add(new Data(r, c, likeCount, emptyCount));
			}
		}

		Collections.sort(candidates);

		return candidates.get(0);
	}

	static class Data implements Comparable<Data> {

		int r, c, likeCount, emptyCount;

		public Data(int r, int c, int likeCount, int emptyCount) {
			this.r = r;
			this.c = c;
			this.likeCount = likeCount;
			this.emptyCount = emptyCount;
		}

		@Override
		public int compareTo(Data other) {
			if (likeCount != other.likeCount) {
				return -Integer.compare(likeCount, other.likeCount);
			}

			if (emptyCount != other.emptyCount) {
				return -Integer.compare(emptyCount, other.emptyCount);
			}

			if (r != other.r) {
				return Integer.compare(r, other.r);
			}

			return (c - other.c);
		}
	}
}
