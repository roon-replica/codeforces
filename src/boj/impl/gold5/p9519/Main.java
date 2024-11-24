package boj.impl.gold5.p9519;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {

		var scanner = new Scanner(System.in);
		long x = scanner.nextLong();
		var s = scanner.next();

		len = s.length();
		int prefixLen = (len - 1) / 2;
		moveStartIdx = len - prefixLen;
		idxs = new ArrayList<Integer>();
		originIdxs = new ArrayList<Integer>();

		IntStream.range(0, len).forEach(i -> {
			idxs.add(i);
			originIdxs.add(i);
		});

		move();
		int cycle = 1;

		for (; ; ) {
			boolean same = true;
			for (int i = 0; i < len; i++) {
				if (!idxs.get(i).equals(originIdxs.get(i))) {
					same = false;
					break;
				}
			}

			if (same) {
				break;
			}
			cycle++;
			move();
		}

//		System.out.println(cycle);
		x %= cycle;
		x = cycle - x;

		for (int i = 0; i < x; i++) {
			move();
		}

//		System.out.println(idxs);
		for (int i = 0; i < idxs.size(); i++) {
			System.out.print(s.charAt(idxs.get(i)));
		}
	}

	static List<Integer> originIdxs;
	static List<Integer> idxs;
	static int moveStartIdx;
	static int len;

	static void move() {
		/*
		 * abcdef
		 * aFbEcd => abcdef
		 *
		 * aDfCbe
		 * aEdBfc
		 * aCeFdb
		 * aBcDef
		 */
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < len; i++) {
			ret.add(idxs.get(i));
		}

		var moves = new ArrayList<Integer>();

		for (int i = len - 1; i >= moveStartIdx; i--) {
			moves.add(idxs.get(i));
		}

		for (int i = 1, j = 0; j < moves.size(); i += 2, j++) {
			ret.add(i, moves.get(j));
		}
		ret = ret.subList(0, len);
		idxs = ret;

//		System.out.println(ret);
	}

	/**
	 * 관찰
	 * abcdef
	 * aFbEcd. 동시에 이동
	 * aDfCbe
	 * aEdBfc
	 * aCeFdb
	 * aBcDef : 원래대로. 5회전:l-1 회전 => 원래대로
	 * x:10억. 역시뮬 불가
	 * 길이 <= 1000
	 * x=x% 길이
	 * 시뮬 가능. 역연산?
	 * abcd
	 * aDbc
	 * aCdb
	 * aBcd
	 * (l-1)/2만큼의 prefix만 이동
	 * abcde, l=5 => l-2
	 * aEbDc
	 * aCeDb
	 * aBcDe
	 * abcdefg, l=7 => l-1
	 * agbfced
	 * adgebcf
	 * afdcgbe
	 * aefbdgc
	 * acegfdb
	 * abcdefg
	 *
	 * 인덱스 리스트?
	 * abcdef: 0123456
	 * aFbEcd: 162534
	 * 146325
	 * 154263
	 * 135642
	 * 123456
	 * 135642
	 * 154263
	 * 146325
	 * 162534
	 * 123456

	 * abcdefgh, l=8
	 * ahbgcfde
	 * aehdbfgc
	 * aceghfdb
	 * abcdefgh : 4번?
	 * (l-1)/2

	 * abcdefghij, l=10
	 * ajbichdgef
	 * afjebgidch
	 * ahfcjdeibg
	 * aghbficejd
	 * adgjhebcfi
	 * aidfgcjbhe

	 * 관찰 결과 주기가 수학적으로 떨어지는건 아니고 시뮬하면서 찾아야하는거였다
	 *
	 */
}


