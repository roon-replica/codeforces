package div2.e142;

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
            int[] a = fs.readArray(4);
            int total = a[0] + a[1] + a[2] + a[3];


            // try -2
            // 왜 틀림? 조잡하게 풀긴했다만
            // 특징 파악
            // a1 >=1 -> a2,a3 서로 상쇄 가능
            // a1 ==0 -> ans = 1
            // 3 0 0 5 -> 3 + 4
            // 3 0 5 5 -> 3 + 4
            // 10 0 5 5 -> 10 + 5 + 5
            // 10 1 5 5 -> 10 + 5 + 5 + 1
            // 10 5 5 5 -> 10 + 5*2 + 5
            // 구현 실수였다....


            // try -1
            // greedy?
            // 2 5 10 6
            // 2 10 5 6
            // a b c d
            // min bc = min(b,c). min bc * 2 (if a>=1)
            // b - min bc, c-min bc
            // a
            // min(a, max(b-min bc, c-min bc)). t = max(b-min bc, c-min bc)

            // b,c 상쇄
            // adhoc 같은

            int answer = a[0];
            if (answer == 0) {
                System.out.println(1);
                continue;
            }

            int minBc = Math.min(a[1], a[2]);
            a[1] -= minBc;
            a[2] -= minBc;

            answer += 2 * minBc;

            int maxBc = Math.max(a[1], a[2]);
            int minA_BC = Math.min(a[0], maxBc);
            answer += minA_BC;

            a[0] -= minA_BC;
            if (a[1] > a[2]) a[1] -= minA_BC;
            else a[2] -= minA_BC;

            int sum_BCD = Math.max(a[1], a[2]) + a[3];
            int minA_BCD = Math.min(a[0], sum_BCD);
            answer += minA_BCD;

            answer += (sum_BCD >= 1 ? 1 : 0);

            answer =  Math.min(answer, total);
            System.out.println(answer);
        }
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
