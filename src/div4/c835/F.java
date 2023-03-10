package div4.c835;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class F {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int n = fs.nextInt();
            long minimumCoin = fs.nextLong();
            int days = fs.nextInt();

            int[] a = fs.readArray(n);

            //try -1
            // 최소의 자원을 투입해서 원하는 값을 얻어야 하는
            // binary search?
            // bin searching k, and simulate each cases
            // O: log * 시뮬
            // 시뮬 어케 하나..
            // 다다익선 그리디?
            // 쿨타임 값 유지를 어케..
            // 아하! 시뮬이 아니라
            // a[i] * (time / cool_time)의 sum으로 계산하면 되겠네

            // bin search 구현 불안..
            // 헷갈린다 구현..

            // 아 왜 틀리지... 이런 식은 맞을텐데.......
            // 모르겠다...

//            rsort(a);
//
//            final int MAX = 200_000+1;
//
//            int maxCooltime = -1;
//            for (int mink = 0, maxk = MAX; mink <= maxk; ) {
//                int mid = (mink + maxk) / 2;
//
//                long sum = sum(a, days, mid);
//
//                if (sum >= minimumCoin) {
//                    mink = mid + 1;
//                    maxCooltime = Math.max(maxCooltime, mid);
//                } else {
//                    maxk = mid - 1;
//                }
//            }
//
//            if (maxCooltime == -1) System.out.println("Impossible");
//            else if (maxCooltime == MAX) System.out.println("Infinity");
//            else System.out.println(maxCooltime);


            // try -2
            // binary search는 맞을거고
            // impossible -> k=0이어도 안될때
            // infinity -> a[i] 중에 목표값 이상이 있을 때
            // 관점...
            // 어케 고를지
            // 다다익선
            // 정렬
            // a: n개
            // n > k -> 큰 a들만 고를 수 있음
            // n =< k -> k-n 일은 허비하게 됨
            // 이렇게 시뮬하면 맞지 않나?
            //mink=0, maxk = days?

            rsort(a);
            long[] psum = psum(a);
            long answer = -1;

            for(int mink=0, maxk = days; mink <= maxk; ){
                int midk = (mink + maxk) / 2;

                if(sum(a,psum,days,midk) >= minimumCoin){
                    answer = Math.max(answer, midk);
                    mink = midk+1;
                }else{
                    maxk = midk-1;
                }
            }

            if (answer == -1) {
                System.out.println("Impossible");
            }else if(answer == days){
                System.out.println("Infinity");
            }else{
                System.out.println(answer);
            }

        }
    }

    private static long[] psum(int[] a){
        long[] ret = new long[a.length + 1];
        for(int i=0; i<a.length;i++){
            ret[i+1] = ret[i] + a[i];
        }

        return ret;
    }

    private static long sum(int[] a, long[] psum, int days, int cooltime) {
        long sum = 0;
        int n = a.length;

        if(n > cooltime){
            // cooltime: 5, n:10, days:5 -> a[0], a[1], ... ,a[4]
            // cooltime: 5, n: 10, days: 10 -> (a[0], a[1], a[2], a[3], a[4], a[5]), a[0], a[1], ...
            // cooltime: 5, n: 10, days: 20 -> (a[0], ... ,a[5]), (a[0], ... ,a[5]), ...
            // 묶음 단위 : cooltime +1개
            // 반복: days / (cooltime+1)
            // 나머지: days % (cooltime+1)

            int repeat = days / (cooltime+1);
            int remain = days % (cooltime+1);
            sum = (psum[cooltime+1] * repeat) + psum[remain];
        }else{
            // cooltime: 5, days: 10, n:2 -> (a[0], a[1], -, -, -, -), (a[0], a[1], -, -)
            // cooltime: 10, days:5, n:2 -> (a[0], a[1], -, -, -,...,-)
            int repeat = days / (cooltime+1);
            int remain = Math.min(days % (cooltime+1), n);

            sum = (psum[n] * repeat) + psum[remain];
        }

        return sum;
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
