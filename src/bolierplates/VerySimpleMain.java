package bolierplates;

import java.util.Scanner;

public class VerySimpleMain {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        int tt = scanner.nextInt();

        for (int tc = 0; tc < tt; tc++) {
            int n = scanner.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }

        }
    }
}
