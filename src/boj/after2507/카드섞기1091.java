package boj.after2507;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class 카드섞기1091 {
	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		// 아마 몇번 섞어보면 처음이랑 똑같이될걸. 그게 N번 안에 될걸 xxx
		// 뭐 몇백번 안엔 되지않겠나.. N^2?

		N = scanner.nextInt();
		List<Integer> cards = new ArrayList<>();
		List<Integer> seqs = new ArrayList<>();
		arr = new int[N];
		int LIMIT = 1_000_000; // N*N 틀림

		for (int i = 0; i < N; i++) {
			cards.add(scanner.nextInt());
		}
		List<Integer> originCards = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			originCards.add(cards.get(i));
		}

		for (int i = 0; i < N; i++) {
			seqs.add(scanner.nextInt());
		}

		boolean possible = false;
		int count = 0;

		for (int i = 0; i < LIMIT; i++) {
			possible = check(cards);
			if (possible) {
				break;
			}

			cards = shuffle(cards, seqs);

			boolean isOrigin = false;
			for (int j = 0; j < N; j++) {
				if (!Objects.equals(cards.get(j), originCards.get(j))) {
					isOrigin = false;
					break;
				}
			}
			if (isOrigin) {
				break;
			}

			count++;
		}

		System.out.println(possible ? count : -1);
	}

	static int N;
	static int[] arr;

	private static boolean check(List<Integer> cards) {
		int checkValue = 0;

		for (Integer card : cards) {
			if (card != checkValue) {
				return false;
			}
			checkValue += 1;
			checkValue %= 3;
		}

		return true;
	}

	private static List<Integer> shuffle(List<Integer> cards, List<Integer> seqs) {
		for (int i = 0; i < N; i++) {
			arr[i] = cards.get(i);
		}

		for (int i = 0; i < seqs.size(); i++) {
			arr[seqs.get(i)] = cards.get(i);
		}

		cards.clear();;
		for (int i = 0; i < N; i++) {
			cards.add(arr[i]);
		}
		return cards;
	}

}
