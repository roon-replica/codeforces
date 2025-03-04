package boj.after2502.p17298;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

	// 시간초과 어케 해결할지 모르곘어서 답 찾아봄
	// 초점을 바꾸는 생각의 전환이 필요한? => beforeIdx가 주인공이자 기준으로 생각하면 풀리는듯
	// 출력도 단순 sout으로 하면 시간초과나는 타이트한 문제..

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		var N = scanner.nextInt();
		var arr = new int[N];
		var stack = new Stack<Integer>();
		int[] ans = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = scanner.nextInt();
			ans[i] = -1;
		}

		stack.add(0);

		for (int i = 1; i < N; i++) {
			var here = arr[i];
			while (!stack.isEmpty()) {
				int beforeIdx = stack.peek();
				if (beforeIdx > i || arr[beforeIdx] >= here) {
					break;
				}

				ans[beforeIdx] = here;
				stack.pop();
			}
			stack.add(i);
		}

		var answer=  Arrays.stream(ans)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(" "));

		System.out.println(answer);

	}
}
