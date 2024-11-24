package boj.impl.gold5.p12904;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// a,b만 존재
//		t->s 가능여부?

//		1. 맨 뒤 a 제거
//		2. 맨 뒤 b 제거, 뒤집기

//		solve(T){
//			if(T.length == S.length) => 비교 후 return
//
//			nextT1=T-a if lastChar=a;
//			nextT2=T-b if lastChar=a and flip;
//
//			solve(nextT1)
//			solve(nextT2)
//		}

//		2^1000? xxxx 이정도 안됨
//		ABBA => {ABB,

		Scanner scanner = new Scanner(System.in);
		S = scanner.nextLine();
		T = scanner.nextLine();

		solve(T);
		System.out.println(ans ? 1 : 0);
	}

	static String S, T;
	static boolean ans;

	static void solve(String t) {
		if (t.length() <= S.length()) {
			if (t.equals(S)) {
				ans = true;
			}
			return;
		}

		var len = t.length();
		var lastChar = t.charAt(len - 1);
		if (lastChar == 'A') {
			solve(t.substring(0, len - 1));
		} else if (lastChar == 'B') {
			solve(new StringBuilder(t.substring(0, len - 1)).reverse().toString());
		}
	}
}
