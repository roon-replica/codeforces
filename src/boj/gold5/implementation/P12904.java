package boj.gold5.implementation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class P12904 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);

		var s = scanner.next();
		var t = scanner.next();

		var q = new LinkedList<String>();
		q.add(t);

		var set = new HashSet<String>();

		boolean found = false;

		while (!q.isEmpty()) {
			var str = q.poll();

			if (str.equals(s)) {
				found = true;
				break;
			}

			int len = str.length();
			var lastChar = str.charAt(len - 1);
			var before = "";

			if (lastChar == 'A') {
				before = str.substring(0, len - 1);
			} else {
				for (int i = Math.max(0, len - 2); i >= 0; i--) {
					before += str.charAt(i);
				}
			}

			if (!set.contains(before) && !before.isEmpty()) {
				set.add(before);
				q.add(before);
			}

		}

		System.out.println(found ? 1 : 0);
	}
}
