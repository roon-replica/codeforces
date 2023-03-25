package div2.c848;

import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tt = scanner.nextInt();

        for (int tc = 0; tc < tt; tc++) {
            n = scanner.nextInt();
            k = scanner.nextInt();
            a = scanner.next();
            b = scanner.next();
            picked = new ArrayList<>();
            answer = 0;

            // 뭐지? a의 알파벳 중에 고르고(10Ck) 하고 시뮬(n)하면 될듯?

            Set<Character> alphabetSet = new HashSet<>();
            for (int i = 0; i < n; i++) alphabetSet.add(a.charAt(i));
            var alphabets = new ArrayList<Character>(alphabetSet);

            k = Math.min(k, alphabets.size());
            pick(alphabets, 0);

            System.out.println(answer);

            /* <리뷰>
                예시 입출력보니까 문제 이해 측면에서 놓친부분 추가로 이해됨. 예시 적극 활용하기.. 문제 이해 검증으로라도

                와.. 간단한 문제였네..?
                첫인상은 까다롭다 생각해서 좌절감 들었었는데
                중요한 관점은 set에 뭐 넣을지 결정하는 거였음
                그 이후는 매우 단순했다

                확실히 주석으로 안풀고 종이에 푸니까 더 고차원적이고 구체적인 생각을 하게 된다
            */
        }
    }

    private static List<Character> picked;
    private static String a, b;
    private static int n, k;
    private static long answer;

    private static void pick(List<Character> source, int nowIdx) {
        if (picked.size() == k) {
            // System.out.println(picked);
            answer = Math.max(answer, calc());
            return;
        }

        for (int idx = nowIdx; idx < source.size(); idx++) {
            picked.add(source.get(idx));
            pick(source, idx + 1);
            picked.remove(picked.size() - 1);
        }
    }

    private static long calc() {
        char[] aCopy = new char[n];
        for (int i = 0; i < n; i++) aCopy[i] = a.charAt(i);

        for (int i = 0; i < n; i++) {
            if (picked.contains(aCopy[i])) aCopy[i] = b.charAt(i);
        }

        long sum = 0;
        int consecutivesCount = 0;

        for (int i = 0; i < n; i++) {
            if (aCopy[i] == b.charAt(i)) {
                consecutivesCount++;
            } else {
                sum += 1L * consecutivesCount * (consecutivesCount + 1) / 2;
                consecutivesCount = 0;
            }
        }

        sum += 1L * consecutivesCount * (consecutivesCount + 1) / 2;

        return sum;

    }
}
