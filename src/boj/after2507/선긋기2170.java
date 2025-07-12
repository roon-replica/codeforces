package boj.after2507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 선긋기2170 {
	public static void main(String[] args) throws IOException {
		var reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		List<int[]> lines = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			var input = reader.readLine().split(" ");
			int s = Integer.parseInt(input[0]);
			int e = Integer.parseInt(input[1]);
			lines.add(new int[]{s, e});
		}

		lines.sort(Comparator.comparingInt(l -> l[0]));

		long now = -1000000000;
		long distSum = 0;

		for (int i = 0; i < N; i++) {
			long s = Math.max(lines.get(i)[0], now);
			long e = lines.get(i)[1];
			if (e <= s) {
				continue;
			}

//			System.out.println(e-s);
			distSum += (e - s);
			now = e;
		}

		System.out.println(distSum);
	}
}
