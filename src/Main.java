import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MAX = Integer.MAX_VALUE;
    static final long LMAX = Long.MAX_VALUE;

    public static void main(String[] args) {
        FastScanner fs = new FastScanner();
        Map<Integer, Integer> mp = new TreeMap<>();
        Set<Integer> set = new TreeSet<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();
        PriorityQueue<pii<Integer, Integer>> pq = new PriorityQueue<>();

        fs.next();

        var min = min(List.of(11, 22, 33));
        System.out.println(min);

        var descList = Arrays.asList(33, 22, 11);
        sort(descList);
        System.out.println(descList);

        var ascList = Arrays.asList(11, 22, 33);
        rsort(ascList);
        System.out.println(ascList);

        System.out.println(lowerBound(descList, 15));

        pq.add(new pii<>(1, 2));
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