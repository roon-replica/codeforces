package boj.gold5.implementation.p25401;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//		1 5 4 3 2
//		증가: 1 5 6 7 8 = 3개 변경
//		같게: 암거나로 바꾸기 = 4개 변경
//		감소: 6 5 4 3 2 = 1개 변경

//		1 6 5 2 4 3
//		증가 => 1 6 7 8 9 10 : 걍 =>로 가면서 일정하게 +d 되도록 하는수밖에 없을듯?
//		같게 => n-같은거 젤 많은거 개수
//		감소 => <-로 가면서 +d씩 하는수밖에 없다?

//		얼만큼 증가?
//		1~최대 2백만/(n-1)
//		d에 대해 이분탐색?
//		백만,  2, 1 , -백만 :

//		1 100 1000 2000 4000 7000 11000: {99, 900, 1000, 2000, 3000, 4000} 1500 . d=500, 501, 502
//		500 * 500 /2 = 250 * 500 = 10 * 0000

//		50만:몇번, d=백만:몇번
//		d=1
//		500, 501
//		1, 1+501, 1+501*2, 1+ 501*3 +1

		// d: 1~10만?
//		100 * 10000 /500 =100 * 20 = 2000
//		d <= 2백만 / n-1
//		2백만 / n-1 :
//		n=10 -> 2십만,
//		n=100 -> 2만,
//		n =500 -> 4천..

		// n2c
		// v_j - v_i / (j-i) 목록 중에 답이 있다 생각하고 시뮬
//		for(int i=0;i<500;i++){
//			System.out.print(1000000* (int)Math.pow(-1,i%2) +" ");
//		}

		var scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long[] numbers = new long[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scanner.nextInt();
		}

		var diffs = new HashSet<Long>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				long diff = numbers[j] - numbers[i];
				if (diff % (j - i) != 0) {
					continue;
				}
				diffs.add(diff / (j - i));
			}
		}

		diffs.add(0L);

		int ans = n;

		for (long d : diffs) {
			ans = Math.min(ans, simul(numbers, d));
		}

		System.out.println(ans);
	}

	static int simul(long[] numbers, long d) {
		int ret = numbers.length;

		for (int i = 0; i < numbers.length; i++) {
			int changeCnt = 0;

			for (int j = 0; j < numbers.length; j++) {
				if (numbers[j] - numbers[i] != d * (j - i)) {
					changeCnt++;
				}
			}
			ret = Math.min(ret, changeCnt);
		}

		return ret;
	}

}
