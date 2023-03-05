package div2.e142;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int n = fs.nextInt();
            int[] p = fs.readArray(n);

            // permutation p
            // operations.
            // sort p. min operations count

            // try-1
            // 제 위치..
            // 1,n을 젤 나중에 해야?
            // 1 5 4 2 3
            // (4,2) -> 2 1 5 3 4
            // (1,5) -> 1 2 3 4 5
            // 5 2 4 1 6 3
            // (5,3) -> 3 2 4 1 6 5
            // (2,5) -> 2 3 4 1 6 5
            // (1,6) -> 1 2 3 4 5 6
            // 최대 n/2번 만에 가능한거 같은데?
            // 전반부 정렬 여부
            // 1 2 3 6 5 4
            // (1,5) -> 1 2 3 6 4 5
            // (1,6) -> 1 2 3 4 5 6
            // 1 2 6 5 4 3
            // (1,4) -> 1 2 6 5 3 4
            // (1,5) -> 1 2 6 3 4 5
            // (1,6) -> 1 2 3 4 5 6
            // 6 5 1 2 3 4
            // (1,5) -> 1 6 2 3 4 5
            // (1,6) -> 1 2 3 4 5 6
            // 일부 정렬 여부?
            // 연속된?
            // 6 5 2 1 4 3
            // (1,4) -> 1 6 5 2 3 4
            // (1,5) -> 1 6 2 3 4 5
            // (1,6) -> 1 2 3 4 5 6
            // LIS 길이?
            // n - LIS, n/2 둘 중 min 값?

            // LIS nlogn 방법
            // 뭐더라?
            // 근데 2 1 4 3은 lis로 설명 안되네
            // 4 3 1 2
            // 1,3 -> 1 4 2 3
            // 1,4 -> 1 2 3 4
            // 모르겠네

            //try-2
            // 브루트포스 풀이는?
            // 브루트포스로 하려면 모든 경우 다 해봐야 하니까 n2c * (n-2)c2 * ...  = 거의 n!임. 불가
            // 실마리도 안잡힐정도로 모르겠다

            // location mapping
            // 순서 안맞는 수들
            // ex) 5 2 4 1 6 3
            // 절반으로 나누면
            // 평균 위는 오른쪽 편, 평균 아래는 왼쪽 편으로 가야함
            // 모르겠다..

            // try3- 튜토리얼 보면서
            // if sorted -> answer = 0
            // last operation = (1,n)
            // answer++하면 2~ n-1 까지만 고려할 수 있음
            // 2~n-1에 대해 정렬되었는지에 따라 동일한 논리 적용 가능?
            // 아.. 이런 식으로 접근하면 답 나오겠네.
            // 이걸 이제 빠르게 계산하면 되는건데
            // sorted 검사는 매번 O(n)?
            // linkedlist로 구성하면?
            // 그래도 탐색 O(n)을 O(n)번 해서 n제곱
            // 모르겠다..
            // 아 여기서 시뮬레이션 하며 binary search하면 되네
            // 그럼 O(n * log n)

            // ?? 왜틀림?
            // 8 7 6 5 4 3 2 1
            // 1 3 4 2
            // 구현 실수.. positions 왜 쓰려고 했지..

            int answer = n / 2;
//            int[] positions = new int[n + 1];
//            for (int seq = 0; seq < n; seq++) {
//                int value = p[seq];
//                positions[value] = seq + 1;
//            }

            for (int l = 0, r = n - 1; l <= r; ) {
                int m = (l + r) / 2;

                // check
                boolean possible = check(p, m);
                if (possible) {
                    answer = Math.min(answer, m);
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }

            System.out.println(answer);
        }
    }

    private static boolean check(int[] p, int filterCount) {
        List<Integer> filtered = new ArrayList<>();
        int n = p.length;
        for (int i = 0; i < n; i++) { // ->
            int value = p[i];
            if (filterCount < value && value <= n - filterCount) {
                filtered.add(value);
            }
        }

        boolean isAscending = true;
        for (int i = 0; i < filtered.size() - 1; i++) {
            int here = filtered.get(i);
            int next = filtered.get(i+1);
            if(here > next) {
                isAscending = false;
                break;
            }
        }

        return isAscending;
    }

    /***********************************************************************/
    static int gcd(int a, int b) {
        if (b != 0) return gcd(b, a % b);
        else return a;
    }

    static <T> void swap(T[] a, int idx1, int idx2) {
        T tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }

    static void sort(int[] a) {
        List<Integer> list = new ArrayList<>();
        for (var i : a) list.add(i);
        Collections.sort(list);
        for (int i = 0; i < a.length; i++) a[i] = list.get(i);
    }

    static void rsort(int[] a) {
        List<Integer> list = new ArrayList<>();
        for (var i : a) list.add(i);
        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < a.length; i++) a[i] = list.get(i);
    }

    static int lowerBound(int[] a, int x) {
        int s = 0, e = a.length;
        while (s < e) {
            int mid = (s + e) / 2;
            if (a[mid] >= x) e = mid;
            else s = mid + 1;
        }
        return e;
    }

    static <T extends Comparable<T>> int lowerBound(List<T> a, T x) {
        int s = 0, e = a.size();
        while (s < e) {
            int mid = (s + e) / 2;
            if (a.get(mid).compareTo(x) >= 0) e = mid;
            else s = mid + 1;
        }
        return e;
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        ArrayList<Integer> readList(int n) {
            ArrayList<Integer> a = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) a.add(nextInt());
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
