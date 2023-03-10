package div2.c843;

import div2.c836.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class C {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            long n = fs.nextLong(), x = fs.nextLong();

            // ๐ & (๐+1) & โฆ & ๐=๐ฅ
            // minimal m
            // &...
            // x = aim
            // x bit ๋ถํด
            // 42 = 32 + 8 + 2
            // 16 = 16, 20 & 21 & 22 & 23 & 24
            // &๋ผ์ ๋ชจ๋  ์๊ฐ ํด๋น bit ์์ด์ผ ์ผ์ง๊ณ , ํ๋๋ผ๋ ์์ผ๋ฉด ๊บผ์ง๊ณ 

            // n > x -> n์ ์ผ๋ถ๋ฅผ ์์ ์ผ ํจ. ๋น ๋ฅด๊ฒ ์ ํ  ์ ์์๋ฏ?
            // n == x -> m=n
            // n < x -> n์ ์ผ๋ถ๋ฅผ ์ถ๊ฐํด์ผ ํ๋๋ฐ ์ ๋  ์๋ ์์๋ฏ. ์ด๊ฒ ๋ฌธ์ ๋ค
            // n=8, x= 16: ans=16
            // n=10, x= 16: ans=-1?
            // ๋ถ๊ฐ๋ฅํ ๊ฒฝ์ฐ ๋ง์๋ฏ?
            // ๋๋๊ฑด ์ฌ์๋ ์ผ๋๊ฑด ์ด๋ ค์ฐ๋

            // n ๋ถํด
            // x ๋ถํด. target
            // min= n๋ณด๋ค ํฌ๋ฉด์ x์ ์๋ ๋นํธ๋ ๊บผ์ผ ํจ. x์ ์๋ ๋นํธ๋ ..
            // ex. n=10, x=9 -> 1010, 1001 => impossible..
            // ex. n=10, x=7 -> 1010, 0111 => impossible..
            // ๋ถ๊ฐ๋ฅ: n์ ์๋ bit์ด x์ ์์ ๋

            // n > x
            //  10011010
            //  00010000
            // 1152921504606846976 ์ด๊ฒ 2^60์ธ๋ฐ. ํฐ ํํธ์ธ๋ฏ
            // 01000
            // 00000
            // 00111
            // 00010
            // ์ด๋ค ๋นํธ๋ ๋๊ณ  ์ด๋ค ๋นํธ๋ ์ด๋ ค์ผ
            // 00111, 00010์ ๊ฒฝ์ฐ๋ ๋ถ๊ฐ๋ฅํจ
            // 01000์ ๋ฐ๋์ ๊ฑฐ์ณ์ผ ํด์

            // 00100 ๊ฐ๋ฅ
            // 00101
            // 00110

            // 00010 ๊ฐ๋ฅ
            // 01010
            // 01011

            // 00100 ๋ถ๊ฐ
            // 01110
            // 01111
            // 10000

            // ๊ท์น ๋ชจ๋ฅด๊ฒ ๋ค...

            if(n==x){ //n==m
                System.out.println(n);
                continue;
            }

            List<Integer> nBits = getBits(n);
            List<Integer> xBits = getBits(x);
            boolean possible = true;
            for(int bit : nBits){
                if(xBits.contains(bit)){
                    possible = false;
                    break;
                }
            }

            if(!possible){
                System.out.println(-1);
                continue;
            }

            if(n > x){



            }




        }
    }

    private static List<Integer> getBits(long num){
        List<Integer> bits = new ArrayList<>();
        for (int b = 0; b < 60; b++) {
            long bit = 1 << b;
            boolean isOn = (num & bit) != 0;
            if (isOn) bits.add(b);
        }

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
