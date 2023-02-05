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

            // 𝑛 & (𝑛+1) & … & 𝑚=𝑥
            // minimal m
            // &...
            // x = aim
            // x bit 분해
            // 42 = 32 + 8 + 2
            // 16 = 16, 20 & 21 & 22 & 23 & 24
            // &라서 모든 수가 해당 bit 있어야 켜지고, 하나라도 없으면 꺼지고

            // n > x -> n의 일부를 없애야 함. 빠르게 정할 수 있을듯?
            // n == x -> m=n
            // n < x -> n에 일부를 추가해야 하는데 안 될 수도 있을듯. 이게 문제네
            // n=8, x= 16: ans=16
            // n=10, x= 16: ans=-1?
            // 불가능한 경우 많은듯?
            // 끄는건 쉬워도 켜는건 어려우니

            // n 분해
            // x 분해. target
            // min= n보다 크면서 x에 없는 비트는 꺼야 함. x에 있는 비트는 ..
            // ex. n=10, x=9 -> 1010, 1001 => impossible..
            // ex. n=10, x=7 -> 1010, 0111 => impossible..
            // 불가능: n에 없는 bit이 x에 있을 때

            // n > x
            //  10011010
            //  00010000
            // 1152921504606846976 이게 2^60인데. 큰 힌트인듯
            // 01000
            // 00000
            // 00111
            // 00010
            // 어떤 비트는 끄고 어떤 비트는 살려야
            // 00111, 00010의 경우는 불가능함
            // 01000을 반드시 거쳐야 해서

            // 00100 가능
            // 00101
            // 00110

            // 00010 가능
            // 01010
            // 01011

            // 00100 불가
            // 01110
            // 01111
            // 10000

            // 규칙 모르겠다...

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
