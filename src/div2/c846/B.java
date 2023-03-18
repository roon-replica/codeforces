package div2.c846;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class B {

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = fs.nextInt();
			int[] a = fs.readArray(n);

			// gcd
			// 약수
			// 브루트포스
			// 부분합
			// dp? moving right calc gcd..
			// gcd 순서 중요치않아
			// 더할지
			// 안더할지
			// 모르겠다....

			// tutorial 봄
			// 와.. 똑똑한 관찰이 필요했다..
			// 2조각으로 나누는게 최선이었다..
			// m>2인 m을 가정하면, gcd(b1,b2,...,bm)와 gcd(b1+b2,...,bm) 비교하면
			// b1,b2도 gcd(b1,b2,...,bm)의 배수
			// b1+b2도 gcd(b1,b2,...,bm)의 배수
			// 그래서 gcd(b1+b2,...,bm) >= gcd(b1,b2,...,bm)...

			// long 챙겼는데 왜 틀림

			/* 리뷰
			* 구현 실수. 인덱스 1개 차이 잘못 생각함
			* 이건 관찰 수준이 좀 높았던 듯..
			* 관찰 못하면 이걸 어케 푸나 싶었음
			* 관찰 잘 했으면 구현은 매우 쉬웠다
			* */

			long[] psum = new long[n + 1];
			for (int i = 0; i < n; i++) {
				psum[i + 1] = psum[i] + a[i];
			}

			long maxGcd = 1L;
			for (int i = 0; i < n-1; i++) {
				long first = psum[i + 1];
				long second = psum[n] - psum[i + 1];

				maxGcd = Math.max(maxGcd, gcd(first, second));
			}

			System.out.println(maxGcd);

		}
	}

	/***********************************************************************/
	static int gcd(int a, int b) {
		if (b != 0) {
			return gcd(b, a % b);
		} else {
			return a;
		}
	}

	static long gcd(long a, long b) {
		if (b != 0L) {
			return gcd(b, a % b);
		} else {
			return a;
		}
	}

	static <T> void swap(T[] a, int idx1, int idx2) {
		T tmp = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = tmp;
	}

	static void sort(int[] a) {
		List<Integer> list = new ArrayList<>();
		for (var i : a) {
			list.add(i);
		}
		Collections.sort(list);
		for (int i = 0; i < a.length; i++) {
			a[i] = list.get(i);
		}
	}

	static void rsort(int[] a) {
		List<Integer> list = new ArrayList<>();
		for (var i : a) {
			list.add(i);
		}
		Collections.sort(list, Collections.reverseOrder());
		for (int i = 0; i < a.length; i++) {
			a[i] = list.get(i);
		}
	}

	static int lowerBound(int[] a, int x) {
		int s = 0, e = a.length;
		while (s < e) {
			int mid = (s + e) / 2;
			if (a[mid] >= x) {
				e = mid;
			} else {
				s = mid + 1;
			}
		}
		return e;
	}

	static <T extends Comparable<T>> int lowerBound(List<T> a, T x) {
		int s = 0, e = a.size();
		while (s < e) {
			int mid = (s + e) / 2;
			if (a.get(mid).compareTo(x) >= 0) {
				e = mid;
			} else {
				s = mid + 1;
			}
		}
		return e;
	}

	static class FastScanner {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}

		ArrayList<Integer> readList(int n) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			for (int i = 0; i < n; i++) {
				a.add(nextInt());
			}
			return a;
		}

		long nextLong() {
			return Long.parseLong(next());
		}
	}
}
