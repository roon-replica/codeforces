package div2.c843;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            String s = fs.next();

            // Either 𝑎≤𝑏 and 𝑐≤𝑏, or 𝑏≤𝑎 and 𝑏≤𝑐
            // 2가지 경우에 대해 검사
            // a/c,b or b,a/c
            // 입력: a,b뿐!
            // 중간에 a가 있냐, b가 있냐 여부
            // a있다 -> b, a/c 가능? aa a aa
            // b있다 -> a/c ,b 가능 b bb aa

            int aIndex = s.length(), bIndex = s.length();

            for (int i = 1; i < s.length() - 1; i++) {
                char here = s.charAt(i);
                if (here == 'a') aIndex = Math.min(aIndex, i);
                if (here == 'b') bIndex = Math.min(bIndex, i);
            }

            if (aIndex != s.length()) { // b, a/c
                System.out.printf("%s %s %s\n", s.substring(0,aIndex), s.charAt(aIndex), s.substring(aIndex+1));
            } else if (bIndex != s.length()) { // a/c , b
                System.out.printf("%s %s %s\n", s.substring(0,bIndex), s.substring(bIndex,s.length()-1), s.charAt(s.length()-1));
            }else{ // impossible
                System.out.println(":(");
            }
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
