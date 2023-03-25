package bolierplates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX = Integer.MAX_VALUE;
    static final long LMAX = Long.MAX_VALUE;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        Map<Integer, Integer> map = new TreeMap<>();
        Set<Integer> set = new TreeSet<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        PriorityQueue<pii<Integer, Integer>> pq = new PriorityQueue<>();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int n = fs.nextInt();
            int[] arr = fs.readArray(n);
        }

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
        List<Integer> list = new ArrayList<>();
        for (var i : a) list.add(i);
        Collections.sort(list, Collections.reverseOrder());
        for (int i = 0; i < a.length; i++) a[i] = list.get(i);
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

    static int lowerBound(long[] a, long x) {
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