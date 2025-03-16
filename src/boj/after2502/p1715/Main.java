package boj.after2502.p1715;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		var scanner = new Scanner(System.in);

		// 1 2 4
		// 1+2 + 1+2+4 = 1*2 + 2*2 + 4*1

		// 1 2 3 4
		// 1+2 + 1+2+3 + 1+2+3+4
		// 1+3 + 1+3+2 + 1+3+2+4
		// 1+4 + 1+4+3 + 1+4+3+2
		// 클수록 최대한 나중에 더하는게 낫네

		// sorted :{a b c d}
		// best = a+b + a+b+c + a+b+c+d
		// 		= 3*a + 3*b + 2*c +d

		// {a,b,c,d,e}
		// best = a+b + a+b+c + a+b+c+d + a+b+c+d+e
		//		= 4*a + 4*b + 3*c + 2*d + e

		// 개수가 같을때?
		// 1장?
		// 최소한? {2,3,4}, {5} 이렇게 있으면? : 1번?
		// 최소한? {3,6,9}, {4} 이렇게 있으면? : 2번?

		// {1~10}, {11~30}, {31~70} 이면? : 비교가 3번 필요한거 아님? => 최악의 경우도 포함하는 경우를 가정하는? => 그럼 생각해낸 공식이 맞을텐데?
		// 개수가 같은 경우는 (a+b-1)번 비교하는거 같은데?
		// {a,a,b,b,c}
		// best = (a+a-1) + (a+a +b) +(a+a+b+b) + (a+a+b+b+c)
		// 1 1 2 2 3
		// 1+1-1 + 2+2-1 +

		int N = scanner.nextInt();
		var pq = new PriorityQueue<Long>();

		for (int i = 0; i < N; i++) {
			var here = scanner.nextLong();
			pq.add(here);
		}

		long ans = 0;
		while (!pq.isEmpty()) {
			long a = pq.poll();
			if (pq.isEmpty()) {
				break;
			}

			long b = pq.poll();
			long sum = a + b;
			ans += sum;
			pq.add(sum);
		}

		System.out.println(ans);
	}
}
