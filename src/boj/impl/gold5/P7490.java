package boj.impl.gold5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class P7490 {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		int tt = scanner.nextInt();

		for (int tc = 0; tc < tt; tc++) {
			int n = scanner.nextInt();
			var q = new LinkedList<Value>();
			q.add(new Value(1, "1"));

			var ops = new ArrayList<>(List.of('+', '-', ' '));
			var exprs = new ArrayList<String>();

			while (!q.isEmpty()) {
				var here = q.poll();
				if (here.seq == n) {
					exprs.add(here.expr);
					continue;
				}

				int next = here.seq + 1;
				for (char op : ops) {
					if (op == '+') {
						q.add(new Value(next, here.expr + "+" + next));
					} else if (op == '-') {
						q.add(new Value(next, here.expr + "-" + next));
					} else {
						q.add(new Value(next, here.expr + " " + next));
					}
				}
			}

			exprs.sort(Comparator.naturalOrder());
			for (var expr : exprs) {
				if (eval(expr)) {
					System.out.println(expr);
				}
			}
			System.out.println();

		}
	}

	static boolean eval(String expr0) {
		// stack?
		// ㄴㄴ 이중 괄호 없어서 걍 이어붙이기만 먼저 다 하면 충분함
		var expr = expr0;
		expr = expr.replace(" ", "");
		int val = 0;

		var numbers = new ArrayList<Integer>();
		var ops = new ArrayList<Character>();

		for (int i = 0; i < expr.length(); i++) {
			char here = expr.charAt(i);

			if (here == '+' || here == '-') {
				ops.add(here);

				numbers.add(val);
				val = 0;
			} else {
				val *= 10;
				val += Integer.valueOf(""+here);

				if (i == expr.length() - 1) {
					numbers.add(val);
				}
			}
		}

//		System.out.println(expr+" "+numbers);
		int ret = 0;
		for(int i=0;i<ops.size();i++){
			char op = ops.get(i);
			if(op == '+'){
				int num1 = numbers.remove(0);
				int num2 = numbers.remove(0);
				numbers.add(0,num1+num2);

			}else if(op == '-'){
				int num1 = numbers.remove(0);
				int num2 = numbers.remove(0);
				numbers.add(0,num1-num2);
			}
		}

//		System.out.println(expr + " " + ret);
//		System.out.println(numbers.get(0));
		return numbers.get(0) == 0;
	}

	static class Value {

		int seq;
		String expr;

		public Value(int seq, String expr) {
			this.seq = seq;
			this.expr = expr;
		}

	}
}
