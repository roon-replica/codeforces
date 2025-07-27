package boj.after2507;

import java.util.ArrayList;
import java.util.Scanner;

public class 센서2212 {
	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		var numbers = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			numbers.add(scanner.nextInt());
		}
		numbers.sort(Integer::compareTo);

		var gaps = new ArrayList<Integer>();
		for (int i = 0; i < N - 1; i++) {
			gaps.add(numbers.get(i + 1) - numbers.get(i));
		}

		gaps.sort(Integer::compareTo);
		for (int i = 0; i < Math.min(K - 1, N - 1); i++) {
			gaps.remove(gaps.size() - 1);
		}

		int sum = 0;
		for (int gap : gaps) {
			sum += gap;
		}

		System.out.println(sum);

	}
}
