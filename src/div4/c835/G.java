package div4.c835;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G {
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = fs.nextInt();
			int a = fs.nextInt();
			int b = fs.nextInt();

			for (int i = 0; i < n; i++) {
				int u = fs.nextInt();
				int v = fs.nextInt();
				int w = fs.nextInt();

				// try -1
				// weighted tree
				// travel
				// x -> x XOR w[i]
				// xor 해서 x가 0되는 b
				// 텔레포트 찬스 최대 1번

				// 텔레포트가 엄청 큰데
				// b와 거리가 1인 곳으로 텔포하면?
				// c: {b와 거리가 1인 곳}
				// a -> c 집합까지 , c -> b 까지?
				// 관점.........
				// 단순 시뮬 안되고
				// v1 v2 v3
				// v1 v2 v3 v2 v4 = v1 v3 v4
				// b와 거리 1인 곳은 무조건 방문할 수 밖에 없음
				// 그럼 a->c까지 w_ac =  w_bc 찾는것과 같음
				// c에서 b 제외하고 거리 1인 곳 = d라 하면
				// a -> d까지 w_ad =  w_bc ^ w_cd인 곳 찾는 것과 같음
				// 의미없는 전개?
				// w_av1 ^ w_v1v2 ^ ... w_vb = 0인 경로
				// 텔포 1번 쓰면?
				// 중간을 날려버릴 수 있는거?
				// xor이 0이 되려면 bitmask가 완전 일치해야 하는데
				// 근데 경로가 아니어도?
				// 트리는 다 연결되어 있으니?
				// 근데 연결구조에 따른 불가피한 방문 횟수가 있는데?
				// edge weights
				// 여튼 경로가 중요한게 아님
				// v1 -> v2로 어떻게든 도달가능하니까
				// 방문 횟수도 어케든 조절 가능할거 같은데
				// b에 바로 인접한 노드들만 아니면
				// 텔포가 어떤 역할?
				// 전체 xor?

				// 모르겠다....


			}
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
