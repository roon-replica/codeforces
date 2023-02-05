package div2.c843;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C_retry {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            long n = fs.nextLong(), x = fs.nextLong();

            // 1010, 1000
            // 1010, 101010
            // n에 없는 더 큰 자리수의 비트가 x에 있다 -> 불가능
            // 1010,0101
            // n에 없는 비트가 x에 있다 -> 불가능
            // n이 x 비트들 다 포함해야
            // 10, 100
            // 10100, 10000
            // 어떤 비트를 끄려면 그 비트보다 한 자리수 큰 비트까지 가면 됨
            // 즉, msb~b+2까지 그대로, b+1 켜기, b~lsb까지 0
            // 0110000, 0
            // 101010, 001010
            // 101011~1000000
            // 꺼야할 비트
            // 꺼야할 비트들 끄기. 되는지 시뮬
            // max, ispossible

            // 틀림..
            // 11,10
            // 111,110

            var nBits = getBits(n);
            var xBits = getBits(x);

            if (!nBits.containsAll(xBits)) { // 답 있긴하냐
                System.out.println(-1);
                continue;
            }

            // 꺼
            // b+1 키고 b~lsb까지 0
            // 끈 목록
            // 끈 목록에 x bit 포함되면 불가
            // 끝까지 되면 가능
            // 01111, 0
            // 10000
            // 끌 목록 : x에 없는 n bit

            Set<Integer> needToOffSet = new HashSet<>();
            for (int nb : nBits) {
                if (!xBits.contains(nb)) needToOffSet.add(nb);
            }

            Set<Integer> offSet = new HashSet<>();
            long answer = 0L;

            for (int bit : needToOffSet) {
                long add = 1L << (bit + 1);

                answer = Math.max(answer, add);

                List<Integer> off = new ArrayList<>();
                for (int b = 0; b <= bit; b++) off.add(b);

                offSet.addAll(off);
            }


            for (int off : offSet) {
                if (xBits.contains(off)) answer = -1;
            }
//            System.out.println(1L<<answer);
//            System.out.println(needToOffSet);
            answer |= x;
            if(answer < n) answer = -1;


            System.out.println(answer);
//            var ansBits = getBits(answer);
//            System.out.println("ansBits: " + ansBits);

        }
    }

    private static List<Integer> getBits(long num) {
        List<Integer> bits = new ArrayList<>();
        for (int b = 0; b < 60; b++) {
            long bit = 1L << b;
            boolean isOn = (num & bit) != 0;
            if (isOn) bits.add(b);
        }

        bits.sort(Comparator.naturalOrder());
        return bits;
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
