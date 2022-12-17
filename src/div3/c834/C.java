package div3.c834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C {
    static final int MAX = Integer.MAX_VALUE;
    static final long LMAX = Long.MAX_VALUE;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        Map<Integer, Integer> mp = new TreeMap<>();
        Set<Integer> set = new TreeSet<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        PriorityQueue<pii<Integer, Integer>> pq = new PriorityQueue<>();

        int T = fs.nextInt();

        for (int tc = 0; tc < T; tc++) {
            int l = fs.nextInt(), r = fs.nextInt(), x = fs.nextInt();
            int a = fs.nextInt(), b = fs.nextInt();

            // a->b로 만들기
            // x 이상의 보폭으로만 이동 가능
            // l,r 사이에서만 이동 가능

            // try-1
                // -> abs(b-a) / x  (안됨)

            // try-2
                // a + (x+1) -x = a+1
                // a + (x+2) -x = a+2
                // ...
                // a - (x+1) +x = a-1
                // a - (x+2) -x = a-2
                // ...
                // -> l,r 범위만 안 벗어나면 이동 못하는 곳은 없는듯?

            // try-3(답 봄)
                // 1. a == b -> 0
                // 2. |a-b| >= x -> 1
                // 3. |a-b| < x
                    // max(|r-a|, |l-a|) < x -> -1
                    // max(|r-a|, |l-a|) >= x -> 일단 가능은 함. 답 >= 2
                        // max(|r-b|, |l-b|) >= x -> 2  (관점 헛짚음)
                        // max(|r-b|, |l-b|) < x  -> 이럴 수가 있나? (관점 헛짚음)

                        // |c-a|>=x, |c-b|>=x인 어떤 c가 존재 -> 2
                        // 존재 안하면 -> 3
                        // 근데 c의 존재를 어케 알지.......
                            // x가 20, a=-9,b=9이고 l,r이 -10,10이면 답=-1 아닌가?
                            // -> 양끝으로 가
                                // if max(|l-a|, |r-a|) < x  || max(|l-b|, |r-b|) < x -> 답=-1 ?
                                // else -> 답은 존재
                                    //
            if(a==b) System.out.println(0);
            else if(abs(a-b) >= x) System.out.println(1);
            else{
                if(max(abs(r-a),abs(l-a)) < x) System.out.println(-1);
                else{
                    if((abs(l-a) < x &&  abs(r-a) < x) || (abs(l-b) < x &&  abs(r-b) < x)){
                        System.out.println(-1);
                    }else { // 답은 존재
                        int ans = 3;
                        if(abs(l-a) >= x && abs(l-b) >= x) ans = 2;
                        if(abs(r-a) >= x && abs(r-b) >= x) ans = 2;

                        System.out.println(ans);
                    }
                }
            }

        }
    }


    /***********************************************************************/
    static int gcd(int a, int b) {
        if (b != 0) return gcd(b, a % b);
        else return a;
    }

    static long gcd(long a, long b) {
        if (b != 0) return gcd(b, a % b);
        else return a;
    }

    static void swap(int[] a, int idx1, int idx2) {
        int tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }

    static void sort(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int i : a) l.add(i);
        Collections.sort(l);
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }

    static <T extends Comparable<T>> void sort(List<T> a) {
        Collections.sort(a);
    }

    static void rsort(int[] a) {
        List<Integer> l = new ArrayList<>();
        for (int i : a) l.add(i);
        Collections.sort(l, Collections.reverseOrder());
        for (int i = 0; i < a.length; i++) a[i] = l.get(i);
    }

    static <T extends Comparable<T>> void rsort(List<T> a) {
        a.sort(Collections.reverseOrder());
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

    static int lowerBound(List<Integer> a, int x) {
        int s = 0, e = a.size();
        while (s < e) {
            int mid = (s + e) / 2;
            if (a.get(mid) >= x) e = mid;
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

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
    }

    static class pii<F, S> implements Comparable<pii<F, S>> {
        final F fs;
        final S sc;

        public pii(F fs, S sc) {
            this.fs = fs;
            this.sc = sc;
        }

        static <F, S> pii<F, S> of(F fs, S sc) {
            return new pii<F, S>(fs, sc);
        }

        public int compareTo(pii<F, S> o) {
            if ((int) this.fs < (int) o.fs) return -1;
            else if ((int) this.fs == (int) o.fs) {
                if ((int) this.sc <= (int) o.sc) return -1;
                else return 1;
            }
            return 1;
        }
    }

    static int abs(int a) {
        return Math.abs(a);
    }

    static long abs(long a) {
        return Math.abs(a);
    }

    static int min(int a, int b) {
        return Math.min(a, b);
    }

    static long min(long a, long b) {
        return Math.min(a, b);
    }

    static int min(int[] a) {
        int mn = a[0];
        for (int i = 1; i < a.length; i++) if (mn > a[i]) mn = a[i];
        return mn;
    }

    static int min(List<Integer> a) {
        int mn = a.get(0);
        for (int i = 1; i < a.size(); i++) if (mn < a.get(i)) mn = a.get(i);
        return mn;
    }

    static int max(int a, int b) {
        return Math.max(a, b);
    }

    static long max(long a, long b) {
        return Math.max(a, b);
    }

    static int max(int[] a) {
        int mx = a[0];
        for (int i = 1; i < a.length; i++) if (mx < a[i]) mx = a[i];
        return mx;
    }

    static int max(List<Integer> a) {
        int mx = a.get(0);
        for (int i = 1; i < a.size(); i++) if (mx > a.get(i)) mx = a.get(i);
        return mx;
    }
}