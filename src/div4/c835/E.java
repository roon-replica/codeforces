package div4.c835;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class E {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int n = fs.nextInt();
            int[] a = fs.readArray(n);

            //try 1 - 틀림...
            // 1개만 뒤집기
            // 1 뒤에 0 개수 합의 최대가 답인데
            // 젤 앞의 0을 1로 바꾸는게 젤 낫나?
            // 0이 없으면, 젤 뒤의 1을 0으로 바꾸는게 최적?
            // 너무 간단한데..
            // 이게 그냥 계산하면 n제곱이라 10^10되서 TLE 나네. 그래서 좀 어려운거
            // 0이 없을 때는 공식 쉽고
            // 0이 있으면.. 누적합?

            // 그냥 두 경우 다 누적합으로 계산 가능
            // 젤 앞의 0을 1로 바꾸는게 항상 최적은 아님
            // 1을 0으로 바꾸는게 나을 때도 있음
            // 젤 뒤의 1을 0으로 바꿔보자

            // 뭐가 잘못됐지....
            // 젤 앞의 0을 1로 바꾸거나, 젤 뒤의 1을 0으로 바꾸는게 최적일거 같은데
            // 모르겠다...


            // try-2
            // 아 operation을 안 쓸 수도 있는건가.. at most once니까...
            // 아 맞네ㅐ.....ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ 하...
            int[] b = new int[n];
            for (int i = 0; i < n; i++) b[i] = a[i];

            long answer = 0;
            int snowball = 0;

            for (int i = 0; i < n; i++) {
                if (a[i] == 1) snowball++;
                else answer += snowball;
            }

            answer = Math.max(answer, solve1(a, n));
            answer = Math.max(answer, solve2(b, n));
            System.out.println(answer);

        }
    }

    private static long solve2(int[] a, int n) {
        int countZero = 0;
        for (int i = 0; i < n; i++) countZero += (a[i] == 0 ? 1 : 0);

        if (countZero == 0) {
            a[n - 1] = 0;
        } else {
            for (int i = n - 1; i >= 0; i--) {
                if (a[i] == 0) continue;

                a[i] = 0;
                break;
            }
        }

        long answer = 0;
        int snowball = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] == 1) snowball++;
            else answer += snowball;
        }

        return answer;
    }

    private static long solve1(int[] a, int n) {
        int countZero = 0;
        for (int i = 0; i < n; i++) countZero += (a[i] == 0 ? 1 : 0);

        if (countZero == 0) {
            a[n - 1] = 0;
        } else {
            for (int i = 0; i < n; i++) {
                if (a[i] == 1) continue;

                a[i] = 1;
                break;
            }
        }

        long answer = 0;
        int snowball = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] == 1) snowball++;
            else answer += snowball;
        }

        return answer;
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
