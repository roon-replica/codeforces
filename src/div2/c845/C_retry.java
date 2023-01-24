package div2.c845;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class C_retry {

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = fs.nextInt();
			int m = fs.nextInt();
			int[] a = fs.readArray(n);

			// 굳이 맵에 넣어놓지 말고.. 너무 많으니까
			// 그때 그때 factor 구해서? -> sqrt(10만)?
			// 배열로 빈도 카운트? -> 10만?
			// 아 sum of n,m이 10만 안넘음
			// 그럼 총 시간복잡도
			// 최악: m = 10만, 각 a= 10만, n= 10만
			// 각각의 a 팩토링: sqrt(10만) * 10만?, freq 배열 검사: 10만?
			// 초관데?
			// 아.. divisor 찾은 개수 live하게 유지하면
			// 찾은개수 = m
			// 찾은 개수!= m
			// freq 0에서 1로 바뀌면 count++
			// freq가 0 되면 count--
			// 이렇게하면 sqrt(10만) * 10만?

			// 이제 구현
			// 정렬
			// freq
			// iter a
			// f,r / f<n
			// 단순하게
			// factor, freq, count
			// f,r 전진 조건
			// f,r =0
			// do, check, ++ ?
			// rear-1

			sort(a);
			int[] freq = new int[m + 1];
			int count = 0;

			int MAX = 100_001;
			int answer = MAX;

			for (int f = 0, r = 0; f < n; ) {
				if (r == n) {
					int front = a[f]; //뺄거
					count = subtract(freq, front, m, count);

					if (count == m) { // 빼도 되더라
						answer = Math.min(answer, a[n - 1] - a[f + 1]);
					}

					f++;
					continue;
				}

				if (count == m) { //빼보기
					int front = a[f]; //뺄거
					count = subtract(freq, front, m, count);

					if (count == m) { // 빼도 되더라
						answer = Math.min(answer, a[r - 1] - a[f + 1]);
					}
					f++;
				} else { //추가하기
					int rear = a[r];
					count = add(freq, rear, m, count);

					if (count == m) { // 추가하니 되더라
//						System.out.println("ok " + f + " " + r + " " + count);
						answer = Math.min(answer, a[r] - a[f]);
					}
					r++;
				}
			}

			if (answer == MAX) {
				System.out.println(-1);
			} else {
				System.out.println(answer);
			}

		}
	}

	private static int subtract(int[] freq, int front, int m, int count) {
		for (int d = 1; d * d <= front; d++) {
			if(front%d !=0) continue;

			if (d <= m) {
				freq[d]--;
				if (freq[d] == 0) {
					count--;
				}
			}

			int d2 = front / d;
			if (d2 <= m) {
				freq[d2]--;
				if (freq[d2] == 0) {
					count--;
				}
			}
		}

		return count;
	}

	private static int add(int[] freq, int rear, int m, int count) {
		for (int d = 1; d * d <= rear; d++) {
			if(rear%d !=0) continue;

			if (d <= m) {
				freq[d]++;
				if (freq[d] == 1) {
					count++;
				}
			}

			int d2 = rear / d;
			if (d2 <= m) {
				freq[d2]++;
				if (freq[d2] == 1) {
					count++;
				}
			}
		}

		return count;
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
