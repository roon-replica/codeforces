package div2.c845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class C {

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		Map<Integer, Set<Integer>> divisorMap = new HashMap<>();

		for (int x=1;x<=100000;x++) {
			Set<Integer> divisors = new HashSet<>();
			for (int d = 1; d * d <= x; d++) {
				if (x % d == 0) {
//					if(d <= m) divisors.add(d);
//					if(x/d <= m) divisors.add(x / d);
					divisors.add(d);
					divisors.add(x/d);
				}
			}
			divisorMap.put(x, divisors);
		}

		int T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = fs.nextInt();
			int m = fs.nextInt();
			int[] a = fs.readArray(n);

			// try-1
			// 나눠 떨어지면 능숙
			// 실력 편차 최대한 적게 팀 구성하기
			// 브루트포스
			// 정렬?
			// 나눠떨어지려면 팀원 값 m 이상이 필요
			// 소수?
			// 큰수부터?
			// set?
			// m보다 큰 팀원?

			// 일단 브루트포스로..
			// 무조건 포함, 선택가능한 것들
			// 모르겠다........

			// try-2: 답 봄
			// 정렬
			// 연속된
			// 2 pointers

			// 인덱스 1개 차이 구현 어렵다
			// f,r 증가시켜가는건데
			// needs 비었다 -> a[f] 약수 지우고 f++
			// 아니면 -> a[r] 약수 추가하고 r++
			// 종료 조건: f<n ?
			// r이 끝이면 f++
			// r이 끝이 아니면 판단해서 f++ or r++

			// 왜 틀렸지........
			// 구현이 좀 주먹구구식이긴한데..
			// 반례: 5 4 / 4 9 (14 15 16)

			// 반례: 3 2 / 1 (2) 3

			// TLE.....
			// 시간복잡도
			// 초기화: 10만^(2/3)


			sort(a);

			Set<Integer> needs = new HashSet<>();
			Map<Integer, Integer> occurrence = new HashMap<>();
			for (int i = 1; i <= m; i++) {
				needs.add(i);
			}

//			Map<Integer, Set<Integer>> divisorMap = new HashMap<>();
//
//			for (int x : a) {
//				Set<Integer> divisors = new HashSet<>();
//				for (int d = 1; d * d <= x; d++) {
//					if (x % d == 0) {
//						if(d <= m) divisors.add(d);
//						if(x/d <= m) divisors.add(x / d);
//					}
//				}
//				divisorMap.put(x, divisors);
//			}

			int front = 0, rear = 0;
			int MAX = 1_000_000;
			int answer = MAX;


			for (; front < n ; ) {
//				System.out.println(front + " " + rear + " " + needs);
//				for(var entry: occurrence.entrySet()){
//					System.out.print(entry.getKey()+","+ entry.getValue()+" ");
//				}
//				System.out.println();

				if (rear == n) {
					var remove = divisorMap.get(a[front]);
					removeFront(remove, occurrence, needs, m);

					if (needs.isEmpty()) {
						front++;
						answer = Math.min(answer, a[rear-1] - a[front]);
						continue;
					}else{
//						System.out.println(needs);
						break;
					}
				}

				if (needs.isEmpty()) {
					var remove = divisorMap.get(a[front]);
					removeFront(remove, occurrence, needs, m);
					front++;

					if (needs.isEmpty()) {
						answer = Math.min(answer, a[rear-1] - a[front]);
					}
				} else {
					var divisors = divisorMap.get(a[rear]);
					addRear(divisors, occurrence, needs, m);

					if (needs.isEmpty()) {
						answer = Math.min(answer, a[rear] - a[front]);
					}

					rear++;
				}
			}

			if (answer == MAX) {
				System.out.println(-1);
			} else {
				System.out.println(answer);
			}

		}
	}

	private static void removeFront(Set<Integer> divisors, Map<Integer, Integer> occurrence, Set<Integer> needs, int m) {
		for (int d : divisors) {
			if(d>m) continue;
			int occur = occurrence.getOrDefault(d, 0);
			occurrence.put(d, occur - 1);
			if (occur - 1 == 0) {
				needs.add(d);
			}
		}
	}

	private static void addRear(Set<Integer> divisors, Map<Integer, Integer> occurrence, Set<Integer> needs, int m) {
		for (int d : divisors) {
			if(d>m) continue;
			int occur = occurrence.getOrDefault(d, 0);
			occurrence.put(d, occur + 1);
			needs.removeIf(x -> x == d);
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
