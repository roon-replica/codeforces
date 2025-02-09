package boj.olympia.mid1.p21758;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 서로 다른 두 곳
		// 또, 다른 한 장소를 골라서 벌통
		// 벌통이 있는 장소에서도 채집
		// 벌이 시작한 장소에서는 어떤 벌도 꿀을 딸 수 없다

		// 벌1: 왼쪽 젤 끝, 벌2: 중간 어딘가 -> 오른쪽 젤끝 : {sum - 벌1 시작(=왼젤끝) - 벌2 시작} + {sum - sum(벌2 시작:x)} // ok
		// 오른쪽 젤 끝 -> 왼쪽 젤 끝 : {sum[끝-1] - 벌2 시작} + {sum[x-1]} // ok
		// 왼, 오 끝 -> 중간 어딘가 x : {sum[x] - sum[1]} + {sum[n-1] - sum[x-1]} // ok

		// precalc sum
		// iteraate x
		// ans = max(ans, left2right, right2left, left&right2mid)

		var scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		long[] arr = new long[N];
		long[] sum = new long[N + 1];

		for (int i = 0; i < N; i++) {
			arr[i] = scanner.nextInt();
			sum[i + 1] = sum[i] + arr[i];
		}

		long ans = 0L;

		for (int x = 1; x < N; x++) {
			long l2r = (sum[N] - arr[0] - arr[x]) + (sum[N] - sum[x + 1]);
			ans = Math.max(ans, l2r);
		}
		
		for (int x = 0; x < N-1; x++) {
			long r2l = (sum[N - 1] - arr[x]) + sum[x];
			ans = Math.max(ans, r2l);
		}

		for (int x = 1; x < N; x++) {
			long lr2m = (sum[x] - sum[1]) + (sum[N - 1] - sum[x - 1]);
			ans = Math.max(ans, lr2m);
		}

		System.out.println(ans);


	}

}
