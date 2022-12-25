import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SimpleMain {
    public static void main(String[] args) {
        Main.FastScanner fs = new Main.FastScanner();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int n = fs.nextInt();
            int[] arr = fs.readArray(n);
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
