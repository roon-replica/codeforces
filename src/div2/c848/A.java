package div2.c848;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tt = scanner.nextInt();

        for (int tc = 0; tc < tt; tc++) {
            int n = scanner.nextInt();
            int[] a = new int[n];

            int sum = 0;
            boolean existDoubleMinus = false;
            boolean existAlternative = false;

            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

            for (int i = 0; i < n; i++) {
                sum += a[i];
                if (i != n - 1) {
                    if (a[i] == -1 && a[i + 1] == -1) existDoubleMinus = true;
                    else if (a[i] + a[i + 1] == 0) existAlternative = true;
                }
            }

            // 딱 1번
            // -1,-1

            if (existDoubleMinus) sum += 4;
            else {
                if (existAlternative == false) sum -= 4;
            }
            System.out.println(sum);
        }

    }
}
