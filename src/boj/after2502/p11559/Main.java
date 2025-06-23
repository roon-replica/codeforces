package boj.after2502.p11559;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		var scanner = new Scanner(System.in);

		// check, down 반복하면 되겠네

		for (int r = 0; r < R; r++) {
			var line = scanner.nextLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
			}
		}

		int count = 0;

		for (; checkAndRemove(); ) {
//			for (int r = 0; r < R; r++) {
//				for (int c = 0; c < C; c++) {
//					System.out.print(map[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();

			down();
			count++;
		}

		System.out.println(count);
	}

	static int R = 12, C = 6;
	static char EMPTY = '.';
	static char[][] map = new char[R][C];
	static int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static int[][] visited = new int[R][C];

	static boolean checkAndRemove() {
		boolean remove = false;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == EMPTY) {
					continue;
				}

				char here = map[r][c];
				remove |= bfs(here, r, c);
			}
		}

//		System.out.println(remove);
		return remove;
	}

	static boolean bfs(char alphabet, int r0, int c0) {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				visited[r][c] = 0;
			}
		}

		var q = new LinkedList<Integer>(); // r*R + c
		q.add(r0 * R + c0);
		visited[r0][c0] = alphabet;

		int count = 0;

		while (!q.isEmpty()) {
			var here = q.poll();
			var r = here / R;
			var c = here % R;
			count++;

			for (var dir : dirs) {
				var nr = r + dir[0];
				var nc = c + dir[1];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] != alphabet || visited[nr][nc] == alphabet) {
					continue;
				}

				visited[nr][nc] = alphabet;
				q.add(nr * R + nc);
			}
		}

		if (count >= 4) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (visited[r][c] == alphabet) {
						map[r][c] = EMPTY;
					}
				}
			}
		}

//		System.out.println("COUNT "+ alphabet+" "+count);
		return count >= 4;
	}

	static void down() {
		for (int c = 0; c < C; c++) {
			var tempRow = new ArrayList<Character>();
			for (int r = R - 1; r >= 0; r--) {
				tempRow.add(map[r][c]);
			}

			tempRow.removeIf(item -> item == EMPTY);

			for(int r = 0; r < R; r++) {
				map[r][c] = EMPTY;
			}

			int tempRowIdx = 0;
			for (int r = R - 1; r >= 0 && tempRowIdx < tempRow.size(); r--) {
				map[r][c] = tempRow.get(tempRowIdx++);
			}
		}
	}
}
