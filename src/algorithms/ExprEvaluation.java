package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExprEvaluation {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		var expr = scanner.next();

		evalNoParenthesis(expr);
	}

	private static void evalNoParenthesis(String expr) {
		// +, -, *, / 포함 가능하고 괄호는 없는 수식

		List<Character> operators = new ArrayList<>();
		var numbers = new ArrayList<Integer>();
		int val = 0;
		int len = expr.length();

		for (int i = 0; i < len; i++) {
			var here = expr.charAt(i);

			if (here == '+' || here == '-' || here == '*' || here == '/') {
				operators.add(here);
				numbers.add(val);
				val = 0;
			} else {
				val *= 10;
				val += Integer.parseInt("" + here);
				if (i == expr.length() - 1) { // 조잡1
					numbers.add(val);
				}
			}
		}

		// *, /
		int idx1 = 0; // 조잡2
		for (char op : operators) {
			if (op == '*') {
				int num1 = numbers.remove(idx1);
				int num2 = numbers.remove(idx1);
				numbers.add(idx1, num1 * num2); // overflow 주의
			} else if (op == '/') {
				int num1 = numbers.remove(idx1);
				int num2 = numbers.remove(idx1);
				numbers.add(idx1, num1 / num2); // overflow 주의
			} else {
				idx1++;
			}
		}

		operators = operators.stream().filter(op -> op == '+' || op == '-').toList();

		int idx2 = 0; // 조잡3
		for (char op : operators) {
			if (op == '+') {
				int num1 = numbers.remove(idx2);
				int num2 = numbers.remove(idx2);
				numbers.add(idx2, num1 + num2); // overflow 주의
			} else if (op == '-') {
				int num1 = numbers.remove(idx2);
				int num2 = numbers.remove(idx2);
				numbers.add(idx2, num1 - num2); // overflow 주의
			} else {
				idx2++;
			}
		}

		System.out.println(numbers.get(0));

	}

}
