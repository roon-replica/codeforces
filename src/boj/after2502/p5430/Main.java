package boj.after2502.p5430;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		// 걍 시뮬

		var scanner = new java.util.Scanner(System.in);
		var T = scanner.nextInt();

		for (int tt = 0; tt < T; tt++) {
			var p = scanner.next();
			var n = scanner.nextInt();

			var dq = new ArrayDeque<Integer>();
			var items = scanner.next();
			items = items.substring(1, items.length() - 1);

			if(items.length() > 0){
				Arrays.stream(items.split(","))
						.map(Integer::parseInt)
						.forEach(dq::add);
			}

//			for (int i = 0; i < n; i++) {
//				dq.add(scanner.nextInt());
//			}

			boolean headFront = true;
			String ans = null;

			for (char cmd : p.toCharArray()) {
				if (cmd == 'R') {
					headFront = !headFront;
				} else {
					if (dq.isEmpty()) {
						ans = "error";
						break;
					}

					if (headFront) {
						dq.pollFirst();
					} else {
						dq.pollLast();
					}
				}
			}

			if (ans == null) {
				if(!headFront){
					var list= new ArrayList<>(dq);
					Collections.reverse(list);
					ans = "["
							+ list.stream()
							.map(String::valueOf)
							.collect(Collectors.joining(","))
							+ "]";
				}else{
					ans = "["
							+ dq.stream()
							.map(String::valueOf)
							.collect(Collectors.joining(","))
							+ "]";
				}

			}

			System.out.println(ans);

		}
	}
}
