package boj.silver1.p3107;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// 원래 IPv6 (32자리의 16진수)로 복원
		// ::이 있을 때 어케 처리하지
		// ::이..
		// ->로 읽으며 :에서 부족할 경우 padding붙여가며 ans 만들어가며
		// ::이면 ans에서 위치 기록해 두고 일단 건너 뛰고?

		// : 나오면 paddingCnt만큼 0 붙여주며 답 만들어가고
		// :: 있으면 그 위치 기록해두고
		// 답 만들어놓고 길이 부족하면 :: 위치 기록해둔 곳에 {0000:0000}+ 추가해주기

		var scanner = new Scanner(System.in);
		var s = scanner.next();
		var paddingCnt = 4;
		var padding = "0000";
		var ans = "";
		var token = "";
		var doubleColonIdx = -1;
		var totalTokenCnt = 8;
		var tokenCnt = 0;

		for (int i = 0; i < s.length(); i++) {
			var here = s.charAt(i);

			if (here == ':') {
				token = padding.substring(0, paddingCnt) + token;
				ans += token;
				ans += ':';

				if (i < s.length() - 1) {
					var next = s.charAt(i + 1);
					if (next == ':' && here == ':') {
						doubleColonIdx = ans.length() - 1;
					}
				}

				token = "";
				paddingCnt = 4;
				tokenCnt++;
			} else {
				token += here;
				paddingCnt--;
			}
		}

		token = padding.substring(0, paddingCnt) + token;
		ans += token;
		tokenCnt++;

		if (doubleColonIdx != -1) {
//			System.out.println(tokenCnt);
			var prefix = ans.substring(0, doubleColonIdx) + ':';
			var suffix = ans.substring(doubleColonIdx + 1);

			for (int i = 0; i < totalTokenCnt - tokenCnt; i++) {
				prefix += padding;
				prefix += ':';
			}

			if (tokenCnt > totalTokenCnt) {
				suffix = suffix.substring(1 + (tokenCnt - totalTokenCnt) * 4);
			}

			ans = prefix + suffix;
		}

		System.out.println(ans);

		// 왜 틀림??
		// 아 알파벳 소문자도 가능한 입력이네 => 상관없는데?
		// 반례: 1:2:3:4:5:6:7:: (::인데 0000 토큰이 2개 이상이 아니라 1개인 경우..)

	}
}
