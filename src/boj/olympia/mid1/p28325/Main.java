package boj.olympia.mid1.p28325;

public class Main {

	public static void main(String[] args) {
		var scanner = new java.util.Scanner(System.in);
		int n = scanner.nextInt();
		var arr = new long[n];
		var ans = 0L;
		var ans2 = 0L;
		int[] v = new int[n];
		int[] v2 = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextLong();

			if (arr[i] != 0) {
				ans += arr[i];
				v[i] = -1;
				v2[i] = -1;
			}
		}

		ans2 += ans;

		for (int i = 0; i < n; i++) {
			if (v[i] == -1) {
				continue;
			}

			if (v[(n + i - 1) % n] == 1 || v[(i + 1) % n] == 1) {
				continue;
			}

			ans++;
			v[i] = 1;
		}

		for (int i = 1; i < n; i++) {
			if (v2[i] == -1) {
				continue;
			}

			if (v2[(n + i - 1) % n] == 1 || v2[(i + 1) % n] == 1) {
				continue;
			}

			ans2++;
			v2[i] = 1;
		}

//		int s = 0, e = 0;
//		for (; e < n; ) {
//			for (; s < n && arr[s] != 0; s++) {
//
//			}
//
//			for (e = s; e < n && arr[e] == 0; e++) {
//
//			}
//
//			ans += (e - s) / 2 + (e - s) % 2;
////			System.out.println(s+" "+e);
//			s = e + 1;
//		}

		System.out.println(Math.max(ans, ans2));
	}

//	5
//	0 1 0 1 0

//	2
//	0 0
}
