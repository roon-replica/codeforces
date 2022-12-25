package div3.c834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class E {
    static final int MAX = Integer.MAX_VALUE;
    static final long LMAX = Long.MAX_VALUE;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        Map<Integer, Integer> mp = new TreeMap<>();
        Set<Integer> set = new TreeSet<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        PriorityQueue<pii<Integer, Integer>> pq = new PriorityQueue<>();

        int MAX = (int) Math.pow(10, 6) + 10;
        long[][][] dp = new long[MAX][4][3];

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int n = fs.nextInt();
            int power = fs.nextInt();

            int[] arr = fs.readArray(n);
            sort(arr);

            // 되게 익숙한 문제 유형이라 느껴지는데
            // green 쓸지 말지, blue 쓸지 말지 dp로 하면 될거 같은데
            // dp[idx][green 남은 기회:0,1,2][blue 남은 기회: 0,1] = reachable? = (power > a[i[)
            // dp[idx][green][blue] = 최적 power로 다 구해놓고 최후에 비교?
            // dp[idx][g][b] = 최대 인덱스?
            // boolean이 맞을듯
            // power도 저장하고 있어야 하는데.. dp가 최적 power 저장하는게 맞겠다
            // 근데 dp = 최대 power가 최적 맞나?
            // 간신히 넘기는 방식으로 가는게 더 좋을 수도 있는거 아닌가..
            // 이건 확실히 증명은 못하겠는데.. 그냥 구현해보자

            // 아 가까스로 넘기도록 하는게 최적이겠다!!
            // 가까스로 넘기기 위해 arr[i] 보다 큰 최솟값으로 설정하자

            // 아니다 가까스로 넘긴다고 최적인 또 아니네..
            // 그냥 최대값이 최적인가

            for (int i = 0; i <= n; i++) {
                for (int g = 0; g <= 2; g++) {
                    for (int b = 0; b <= 1; b++) {
                        dp[i][g][b] = -1;
                    }
                }
            }

            dp[0][2][1] = power;

            for (int i = 0; i < n; i++) {
                for (int g = 0; g <= 2; g++) {
                    for (int b = 0; b <= 1; b++) {
                        long nowPower = dp[i][g][b];
                        if (nowPower == -1) continue;

                        long nextPower = nowPower;

                        for (int gi = 0; gi <= g; gi++) {
                            if (gi == 1) nextPower = nowPower * 2;
                            else if (gi == 2) nextPower = nowPower * 4;

                            for (int bi = 0; bi <= b; bi++) {
                                if (bi == 1) nextPower *= 3;

                                if (nextPower > arr[i]) {
                                    dp[i + 1][g - gi][b - bi] = Math.max(dp[i + 1][g - gi][b - bi], nextPower + arr[i] / 2);
                                }
                            }
                        }


                    }
                }
            }

            int maxReachableIndex = 0;

            for (int i = 1; i <= n; i++) {
                for (int g = 2; g >= 0; g--) {
                    for (int b = 1; b >= 0; b--) {
//                        System.out.print(dp[i][g][b] + " ");
                        if (dp[i][g][b] <= arr[i - 1]) continue;
                        maxReachableIndex = Math.max(maxReachableIndex, i);
                    }
                }
//                System.out.println();
            }

            System.out.println(maxReachableIndex);
        }
    }

    private static void howToUse(FastScanner fs, PriorityQueue<pii<Integer, Integer>> pq) {
        // fs.next();

        var min = min(List.of(11, 22, 33));
        System.out.println(min);

        var descList = Arrays.asList(33, 22, 11);
        sort(descList);
        System.out.println(descList);

        var ascList = Arrays.asList(11, 22, 33);
        rsort(ascList);
        System.out.println(ascList);
        System.out.println(max(ascList));

        System.out.println(lowerBound(descList, 15));

        pq.add(new pii<>(1, 2));
        pq.add(new pii<>(3, 4));

        System.out.println(pq.poll());

        System.out.println(gcd(2 * 2 * 3 * 5, 3 * 5));
        System.out.println(gcd(2 * 3 * 5 * 7 * 11L, 2 * 5 * 11L));

        System.out.println(abs(-11) + " " + abs(-1.123) + " " + abs(-12L));
    }


    /***********************************************************************/
    static <T extends Number> T gcd(T a, T b) {
        if (a instanceof Integer) {
            if (b.intValue() != 0) return (T) gcd(b, a.intValue() % b.intValue());
            else return a;
        } else if (a instanceof Long) {
            if (b.longValue() != 0) return (T) gcd(b, a.longValue() % b.longValue());
            else return a;
        } else {
            throw new IllegalArgumentException("[gcd] Unsupported data type. only support int, long.");
        }
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

    static <T extends Comparable<T>> int lowerBound(T[] a, T x) {
        int s = 0, e = a.length;
        while (s < e) {
            int mid = (s + e) / 2;
            if (a[mid].compareTo(x) >= 0) e = mid;
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

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
    }

    static class pii<F extends Comparable<F>, S extends Comparable<S>> implements Comparable<pii<F, S>> {
        final F fs;
        final S sc;

        public pii(F fs, S sc) {
            this.fs = fs;
            this.sc = sc;
        }

        static <F extends Comparable<F>, S extends Comparable<S>> pii of(F fs, S sc) {
            return new pii<>(fs, sc);
        }

        public int compareTo(pii<F, S> o) {
            return this.fs.compareTo(o.fs);
        }

        @Override
        public String toString() {
            return String.format("{fs: %s, sc: %s}\n", this.fs, this.sc);
        }
    }

    public static <T extends Number> T abs(T x) {
        if (x instanceof Integer) {
            return (T) (Object) Math.abs((Integer) x);
        }
        if (x instanceof Long) {
            return (T) (Object) Math.abs((Long) x);
        }
        if (x instanceof Double) {
            return (T) (Object) Math.abs((Double) x);
        }
        throw new IllegalArgumentException("[abs] Unsupported data type. only support int, long, double");
    }

    static <T extends Comparable<T>> T min(T a, T b) {
        if (a.compareTo(b) < 0) return a;
        else return b;
    }

    static <T extends Comparable<T>> T min(T[] a) {
        T min = a[0];
        for (int i = 1; i < a.length; i++) if (min.compareTo(a[i]) > 0) min = a[i];
        return min;
    }

    static <T extends Comparable<T>> T min(List<T> a) {
        T min = a.get(0);
        for (int i = 1; i < a.size(); i++) if (min.compareTo(a.get(i)) > 0) min = a.get(i);
        return min;
    }

    static <T extends Comparable<T>> T max(T a, T b) {
        if (a.compareTo(b) >= 0) return a;
        else return b;
    }

    static <T extends Comparable<T>> T max(T[] a) {
        T max = a[0];
        for (int i = 1; i < a.length; i++) if (max.compareTo(a[i]) < 0) max = a[i];
        return max;
    }

    static <T extends Comparable<T>> T max(List<T> a) {
        T max = a.get(0);
        for (int i = 1; i < a.size(); i++) if (max.compareTo(a.get(i)) < 0) max = a.get(i);
        return max;
    }
}