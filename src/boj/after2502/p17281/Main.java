package boj.after2502.p17281;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);

		// 완탐이겠네. 이걸 다해봐야 알 수 있지 어떤 규칙을 찾긴 힘들겠지. n도 작으니
		// 모든 조합의 타순을 다 만들고 (4번 타자는 1번)
		// 각 이닝 시뮬레이션?

		INNING = scanner.nextInt();

		for (int i = 0; i < INNING; i++) {
			for (int j = 0; j < 9; j++) {
				hits[i][j] = scanner.nextInt();
			}
		}

		permutation();
		System.out.println(ans);
	}

	static int n = 9;
	static boolean[] selected = new  boolean[n];
	static List<Integer> picked = new ArrayList<>();

	static int ans;
	static int INNING;
	static int idx;
	static int[][] hits = new int[50][9];

	static void permutation() {
		if (picked.size() == n) {
			if (picked.get(4 - 1) != 0) {
				return;
			}

			int score = 0;
			idx=0;
			for (int i = 0; i < INNING; i++) {
				score += simulateInning(i);
			}
			ans = Math.max(ans, score);

			return;
		}

		for (int i = 0; i < n; i++) {
			if (selected[i]) {
				continue;
			}

			picked.add(i);
			selected[i] = true;

			permutation();

			picked.remove(picked.size() - 1);
			selected[i] = false;
		}
	}

	static int simulateInning(int inning) {
		int score = 0;
		int outCount = 0;
		int[] bases = new int[4]; // -,1,2,3루,홈

		for (int number = picked.get(idx%9); outCount < 3; number = picked.get(((++idx) % 9))) {
			int hit = hits[inning][number];

			if (hit == 0) {
				outCount++;
			} else {
				if (hit == 1) {
					score += bases[3];

					bases[3] = bases[2];
					bases[2] = bases[1];
					bases[1] = 1;
				} else if (hit == 2) {
					score += (bases[3] + bases[2]);

					bases[3] = bases[1];
					bases[2] = 1;
					bases[1] = 0;
				} else if (hit == 3) {
					score += (bases[3] + bases[2] + bases[1]);
					bases[3] = 1;
					bases[2] = 0;
					bases[1] = 0;
				} else if (hit == 4) {
					score += (bases[3] + bases[2] + bases[1] + 1);
					for (int i = 0; i < 4; i++) {
						bases[i] = 0;
					}
				}
			}

		}

		return score;

	}
}
