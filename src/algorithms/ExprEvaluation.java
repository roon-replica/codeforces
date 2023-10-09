package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class ExprEvaluation {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		var expr = scanner.next();

		System.out.println(infix2Postfix(expr));
		;
		// evalNoParenthesis(expr);
	}


	private static void evalWithParenthesis(String expr) {

	}

	private static String infix2Postfix(String expr) {
		/* 알고리즘 (associativity는 고려하지 않음. 즉, +,-,*,/, (, )만 고려)
		1. 스캔
		  1-1. 숫자일때 -> postfix 수식에 넣기
		  1-2. 연산자일 때
		    a) 현재 연산자가 stack 최상단의 연산자보다 우선순위가 높다, empty, ( 인 경우 -> push
		    b) 현재 연산자가 더 우선순위가 낮거나 같다 -> 그런 연산자 모두 pop하고 나서 현재 연산자 push
		  1-3. 여는 괄호일 때 -> push
		  1-4. 닫는 괄호일 때 -> (가 나올때까지 pop. 괄호들은 버림
		2. pop all

		ex. 123+(5-3)*4
		*/

		var opStack = new Stack<String>();
		var operators = new HashSet<>(Set.of('+', '-', '*', '/'));
		var priorityMap = new HashMap<String, Integer>();
		priorityMap.put("+", 1);
		priorityMap.put("-", 1);
		priorityMap.put("*", 2);
		priorityMap.put("/", 2);

		StringBuilder postfix = new StringBuilder();
		String value = "";

		for (int i = 0; i < expr.length(); i++) {
			var ch = expr.charAt(i);

			if (Character.isDigit(ch)) {
				value += ch;
				if (i == expr.length() - 1) {
					postfix.append(" "+value + " ");
				}
			} else {
				if (!value.equals("")) {
					postfix.append(value + " ");
					value = "";
				}

				if (operators.contains(ch)) {
					while (!(opStack.isEmpty() || opStack.peek().equals("(") || priorityMap.get("" + ch) > priorityMap.get(opStack.peek()))) {
						postfix.append(opStack.pop());
					}

					opStack.push("" + ch);
				} else if (ch == '(') {
					opStack.push("" + ch);
				} else if (ch == ')') {
					while (!opStack.isEmpty()) {
						var popped = opStack.pop();
						if (popped.equals("(")) {
							break;
						}
						postfix.append(popped + " ");
					}
				}
			}
		}

		while (!opStack.isEmpty()) {
			postfix.append(opStack.pop()).append(" ");
		}

		return postfix.toString();
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
