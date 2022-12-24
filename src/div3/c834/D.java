package div3.c834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class D {
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
            int n = fs.nextInt();
            int m = fs.nextInt();

            // try-1
            // 그냥 n*m%10이 답 아님?
            // 근데 불가능한 경우는 언제?
            // digit of max(n,m) == digit of n*m 일 때 안되는거라 n*m 출력하면 되나?
            // -> 문제 덜 이해함.

            // try-2
            // 일단 n*m 하고 n씩 줄여나가다가 되는지 안되는지 파악하면 안되려나?
            // 끝까지 안해봐도 될거 같은데
            // 증명해야 함
            // 근데 max(n*m) = 10^18이라 너무 큼. 브루트포스 절대 안됨.
            // 다른 관점이 필요하다.. 이 문제도 관점만 파악하면 바로 풀릴듯한데
            // 맨뒤에 0이 붙으려면 10=2*5 형태로 나눠떨어져야 하는데
            // n*m - n*k = n(m-k)이 2랑 5로 나눠떨어져야 함
            // n은 소인수분해 되고, (m-k)는 걍 자연수임. n에서 2,5 소인수 개수 세아리고, (m-k)에서 2,5개수 최대치로 뽑아내면 되겠다! 그럼 빠르게 될 듯
            // 결론은 min(n_2 + m_2, n_5+ m_5)의 max 값을 찾는건데
            // n은 결정됐고, m이 문젠데..
            // 일단 동률로 맞추고... 그다음에 하나씩 증가시켜가면 될듯?
            // TLE -> 소인수분해를 할게 아니라, 2와 5의 개수만 구해야겠다..!

//            var factorsN = factorize(n);
            int[] countTwosFives = countTwosFives(n);
            int twos = countTwosFives[0];
            int fives = countTwosFives[1];

            long usedM = 1L;
            long tempAnswer = (long) n;
            long maxMul = (long) n * m;

            if (twos > fives) {
                for (; fives < twos && usedM * 5 <= m; fives++, usedM *= 5) {
                    tempAnswer *= 5;
                }
            } else if (twos < fives) {
                for (; twos < fives && usedM * 2 <= m; twos++, usedM *= 2) {
                    tempAnswer *= 2;
                }
            }

            for (; usedM * 10 <= m; usedM *= 10) {
                tempAnswer *= 10;
            }

            long answer = tempAnswer * (m/usedM);

            System.out.println(tempAnswer == n ? maxMul : answer);
        }
    }

//    private static List<Integer> factorize(int n) {
//        List<Integer> ret = new ArrayList<>();
//        for (int f = 2; f <= n; f++) {
//            for (; n % f == 0; n /= f) {
//                ret.add(f);
//            }
//        }
//
//        return ret;
//    }

    private static int[] countTwosFives(int n){
        int[] ret = new int[]{0,0};
        for(;n %2 ==0; n/=2) ret[0]++;
        for(;n%5==0;n/=5) ret[1]++;
        return ret;
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

    static <T extends Comparable<T>> void sort(T[] a) {
        List<T> list = new ArrayList<>(Arrays.asList(a));
        Collections.sort(list);
        for (int i = 0; i < a.length; i++) a[i] = list.get(i);
    }

    static <T extends Comparable<T>> void sort(List<T> a) {
        Collections.sort(a);
    }

    static <T extends Comparable<T>> void rsort(T[] a) {
        List<T> list = new ArrayList<>(Arrays.asList(a));
        list.sort(Collections.reverseOrder());
        for (int i = 0; i < a.length; i++) a[i] = list.get(i);
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