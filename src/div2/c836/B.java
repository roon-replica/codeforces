package div2.c836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // n: odd
        // a,a,a,a,... a / b : a(n-1) + b
        // b(n-1) = a(n-1)
        // b=a
        // all a's

        // n: even
        // a,a,... a,a / b,c  : a*(n-2) +b +c
        // (n-1)(b+c) = a(n-2)
        // b+c = n-2, a = n-1 (n>=3), b,c는 a와 bit 안겹치게...

        // n=2
        // n(b^c) = b+c
        // b,c = 1,3 => ok

        // 와 n :even일 때...
        // 1,3,(2,2,...,2)
        // n(1^3) = 1+3+2(n-2)
        // 2n = 2n

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            var list = new ArrayList<String>();

            if (n % 2 == 1) {
                for (int i = 0; i < n; i++) {
                    list.add("1");
                }
            } else {
                for (int i = 0; i < n - 2; i++) {
                    list.add(String.valueOf(2));
                }
                list.add("1");
                list.add("3");
            }

            var ans = String.join(" ", list);
            System.out.println(ans);
        }
    }
}
