package div2.c848;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tt = scanner.nextInt();

        for (int tc = 0; tc < tt; tc++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int d = scanner.nextInt();
            int[] p = new int[n];
            int[] a = new int[m];


            for (int i = 0; i < n; i++) {
                p[i] = scanner.nextInt();
            }

            for (int i = 0; i < m; i++) {
                a[i] = scanner.nextInt();
            }

            // 약간 당황스럽다. 어려워보여서
            // not good 조건 깨기 쉬워보임
            // 떨어진 거리
            // a[i] 위치 저장
            // calc dist
            // 최적이 답
은
            int[] pos = new int[n + 1];
            for (int i = 0; i < n; i++) {
                int here = p[i];
                pos[here] = i; // 0-indexing
            }

            int ans = n - 1;

            for (int i = 0; i < m - 1; i++) {
                int prev = a[i];
                int here = a[i + 1];

                int prevPosition = pos[prev];
                int herePosition = pos[here];

                if ((prevPosition >= herePosition) || (prevPosition + d < herePosition)) {
                    ans = 0;
                    break;
                } else {
                    int dist = herePosition - prevPosition;
                    int needToMove = prevPosition + d - herePosition + 1;

                    // 1. <-, ->
                    // 2. -> , <-
                    int leftMargin = prevPosition;
                    int rightMargin = n - 1 - herePosition;

                    if (leftMargin + rightMargin >= needToMove) { // 1. <-, ->
                        ans = Math.min(ans, Math.min(needToMove, dist));
                    } else { // 2. -> , <-
                        ans = Math.min(ans, dist);
                    }
                }
            }

            System.out.println(ans);
        }
    }
}
