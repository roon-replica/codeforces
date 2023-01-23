package div2.c845;

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

			// 나열해보자
			// n=2, [1,2], [2,1]
			// => [1,2,2,1], [2,1,1,2]
			// => (0+1+1+0) (2+0+0+0)
			// n=3, [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
			// => [1,2,3,3,2,1], [1,3,2,2,3,1], [2,1,3,3,1,2], [3,1,2,2,1,3], [3,2,1,1,2,3]
			// (0+0+2+2+1+0), (0+3+1+1+1), (2+0+2+2+0+0) ,,,

			// 1-> 항상 기여 0
			// 2-> 뒤에 1이 있으면 기여 가능 (0~2)
			// 3-> 뒤에 1,2 (0~4)
			// 4-> 뒤에 1,2,3 (0~6)
			// x -> 뒤에 1~(x-1) (0~2*(x-1))

			// 브루트포스 안됨
			// 관점..
			// 각 숫자 위치
			// 경우의 수?
			// ___ / _ / ___
			// [1,2,3,4,5]
			// 2____ 4
			// _2___ 3
			// __2__ 2
			// ___2_ 1

			// 3____ 2 * 4!
			// _3___ 2 * 3P2 + 2*3P1
			// __3__ 2 * 2P2 + 2*2P1
			// ___3_ 2 * 2P1

			// 모르겠다...
			// 나보다 크다/작다
			// 최소 보장
			// 1로 인해 유발되는
			// 2로 인해 유발되는
			// 3으로 인해 유발되는...
			// 모르겠다..

			// 해설 봄 (umnik)
			// inversion 개수는 순열의 형태에 의존하지 않는다?
			// 3가지 종류의 inversion: inversion in 앞의 원래 순열, inversion in 뒤집어서 뒤에 붙인 순열, inversion between 앞의 원래 순열과 뒤집어서 뒤에 붙인 순열

			// (https://blog.naver.com/dong_gas/222990793884)
			// a가 어떻게 생기든 각 수 x마다 inversion 개수는 x-1개???
			// 와... a가 어떻게 생기든 1->0, 2-> 2, 3->4 , 4-> 6이네.....
			// {0+1+... + (n-1)} * 2 * n!
			// => n*(n-1) * n!

			long answer = (long) n * (n - 1); //
			answer %= mod;
			answer *= fact(n); //순열 개수
			answer %= mod;

			System.out.println(answer);

		}
	}

	private static final int mod = 1000000007;

	static long fact(int n) {
		long fact = 1;
		for (int i = 2; i <= n; i++) {
			fact *= i;
			fact %= mod;
		}

		return fact;
	}

	/***********************************************************************/
	static int gcd(int a, int b) {
		if (b != 0) {
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

	private static class FastScanner {

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
