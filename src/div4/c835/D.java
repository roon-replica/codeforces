package div4.c835;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class D {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int n = fs.nextInt();
            int[] a = fs.readArray(n);

            // 오직 1개의 valley
            // index 0이 index n-1보다 valley 되기 쉽네
            // l==r 가능
            // 중간 valley -> 푹 꺼진곳
            // 처음 valley -> 평평하다가 우상향. 우하향 안됨.
            // 끝 valley -> 우하향만 됨.

            // 처음 valley를 어떻게 체크하지...

            // 연속된 같은 값들은 뭉쳐야겠다. 중간 valley 잘못 세어짐
            // 중간 valley -> 푹 꺼진곳
            // 처음 valley -> 우상향만 됨
            // 끝 valley -> 우하향만 됨.

            List<Integer> list = new ArrayList<>();
            list.add(a[0]);
            for (int i = 1; i < n; i++) {
                if (list.get(list.size() - 1) != a[i]) list.add(a[i]);
            }
//            System.out.println(list);

            int m = list.size();

            if(m == 1){
                System.out.println("YES");
                continue;
            }

            int midValley = 0;
            for (int i = 1; i < m - 1; i++) {
                int mid = list.get(i);
                int prev = list.get(i-1);
                int next = list.get(i+1);

                if (prev > mid && mid < next) {
                    midValley++;
                }
            }

            int lastValley = 0;
            if (list.get(m-2) > list.get(m - 1)) lastValley = 1;

            int firstValley = 0;
            if(list.get(0) < list.get(1)) firstValley = 1;

            if ((firstValley + midValley + lastValley) == 1) System.out.println("YES");
            else System.out.println("NO");

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

    private static class FastScanner {
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
