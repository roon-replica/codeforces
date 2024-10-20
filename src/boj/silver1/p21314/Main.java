package boj.silver1.p21314;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// {M+K{0-1}}+
		// 변환될 수 있는 십진수 중 가장 큰 값과 가장 작은 값
		// MKM => M,K,M (151) / MK, M (501)
		// MKKMMK => MK, K, MMK(505500),  / M, K, K, M, M, K (155115) / M, K, K, MM, K (155105)
		// => 최소: M은 최대한 길게, M뒤 K는 절대 안붙이기
		// => 최대: M+에 K를 무조건 붙이기 (있으면)

		// MMK => MM, K / M, M, K / MMK
		//  	  105 / 115 / 500
		// MMKK => MM, K, K / M, M, K, K / M, MK, K / MMK, K /
		//			1055 / 1155 / 1505 / 5005
		// MMKMK => MM, K, M, K / MMK, MK / MMK, M, K
		//			10515 / 50050 / 50015
		// MMKKMK => MM, K, K, M, K /M, M, K, K, M, K /  MMK, K, MK
		//			105515 / 115515 / 500550

		// KMM => K, MM / K, M, M
		//		  510 / 511

		// KMMKMM => K, MM, K, MM / K, MMK, M, M
		//			510510 / 550011

		// -> 읽으며
		// 규칙따라 문자열 붙여가며 답 생성
		// => 최소: M은 최대한 길게, M뒤 K는 절대 안붙이기
		// => 최대: M+에 K를 무조건 붙이기 (있으면), 마지막 M 다 쪼개기

		var scanner = new Scanner(System.in);
		var s = scanner.nextLine();

		// min
		var min = "";
		int mCount = 0;

		for (int i = 0; i < s.length(); i++) {
			var here = s.charAt(i);
			if (here == 'M') {
				mCount++;
			} else if (here == 'K') {
				if (mCount != 0) {
					min += '1';
					for (int c = 1; c < mCount; c++) {
						min += '0';
					}
				}
				min += '5';
				mCount = 0;
			}
		}

		if (mCount != 0) {
			min += '1';
			for (int c = 1; c < mCount; c++) {
				min += '0';
			}
		}

		mCount = 0;
		// max
		var max = "";
		for (int i = 0; i < s.length(); i++) {
			var here = s.charAt(i);
			if (here == 'M') {
				mCount++;
			} else if (here == 'K') {
				max += '5';
				for (int c = 0; c < mCount; c++) {
					max += '0';
				}
				mCount = 0;
			}
		}

		if (mCount != 0) {
			for (int c = 0; c < mCount; c++) {
				max += '1';
			}
		}

		System.out.println(max);
		System.out.println(min);

	}

}
