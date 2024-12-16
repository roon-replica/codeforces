package boj.sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class P17140 {

	public static void main(String[] args) {
		// 하란대로 시뮬하면 되겠다
		var scanner = new java.util.Scanner(System.in);

		R = scanner.nextInt();
		C = scanner.nextInt();
		K = scanner.nextInt();

		for (int r = 1; r <= 3; r++) {
			for (int c = 1; c <= 3; c++) {
				map[r][c] = scanner.nextInt();
			}
		}

		for (; time <= 100 && !check(); time++) {
			if (r >= c) {
				operationR();
			} else {
				operationC();
			}

//			if (time <= 3) {
//				printMap();
//			}

		}

		System.out.println(time > 100 ? -1 : time);
	}

	static int R, C, K, time;
	static int r = 3, c = 3;
	static int[][] map = new int[101][101];

	static void operationR() {
		int maxC = c;
		for (int i = 1; i <= r; i++) {
			var countMap = new HashMap<Integer, Integer>();
			for (int j = 1; j <= c; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				countMap.put(map[i][j], countMap.getOrDefault(map[i][j], 0) + 1);
			}

			var list = getList(countMap);

			for (int j = 1; j <= c; j++) {
				map[i][j] = 0;
			}

			int cIdx = 1;
			for (var data : list) {
				map[i][cIdx++] = data.num;
				map[i][cIdx++] = data.count;
			}

			maxC = Math.max(maxC, list.size() * 2);
		}

		c = maxC;

	}

	static void operationC() {
		int maxR = r;

		for (int i = 1; i <= c; i++) {
			var countMap = new HashMap<Integer, Integer>();
			for (int j = 1; j <= r; j++) {
				if (map[j][i] == 0) {
					continue;
				}
				countMap.put(map[j][i], countMap.getOrDefault(map[j][i], 0) + 1);
			}

			var list = getList(countMap);

			for (int j = 1; j <= r; j++) {
				map[j][i] = 0;
			}

			int rIdx = 1;
			for (var data : list) {
				map[rIdx++][i] = data.num;
				map[rIdx++][i] = data.count;
			}

			maxR = Math.max(maxR, list.size() * 2);
		}

		r = maxR;
	}

	static boolean check() {
		return map[R][C] == K;

	}

	static class Data {

		int num, count;

		Data(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}

	static List<Data> getList(Map<Integer,Integer> countMap){
		var list = countMap.entrySet().stream()
				.map(e -> new Data(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		list.sort((a, b) -> {
			if (a.count == b.count) {
				return a.num - b.num;
			} else {
				return a.count - b.count;
			}
		});

		return list;

	}

	static void printMap() {
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
