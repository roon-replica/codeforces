package boj.gold5.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class P1790 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		long[] power = new long[12];
		power[0] = 1L;
		int digit = 1;

		for (long temp = 0; temp < k; ) {
			power[digit] = power[digit - 1] * 10;
			temp += (power[digit] - power[digit - 1]) * digit;
			digit++;
		}

		digit--;
//		System.out.println(digit);

		int number = 0;

		for (int d = 1; d < digit; d++) {
			long addSeq = power[d] - power[d - 1];
			long addVal = d * addSeq;

			if (addVal > k) {
				break;
			}

			number += addSeq;
			k -= addVal;
		}

//		System.out.println(number + " " + k);

		number += k / digit;
		k %= digit;

//		System.out.println(number + " " + k);
		int answer = -1;

		if (k > 0) {
			k -= 1;
			number += 1;
			if (number <= n) {
				List<Integer> numbers = new ArrayList<>();
				for (int d = 0; d < digit; d++) {
					numbers.add(number % 10);
					number /= 10;
				}

				Collections.reverse(numbers);

				answer = numbers.get(k);
			}
		} else {
			if (number <= n) {
				answer = number % 10;
			}
		}

		System.out.println(answer);

	}
}
