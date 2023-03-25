package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
    public static void main(String[] args) {
        // 조합 어케 짜더라
        // nCk

        List<Integer> source = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        pick(source, 3, 0);
    }

    private static List<Integer> picked = new ArrayList<>();

    private static void pick(List<Integer> source, int count, int nowIdx) {
        if (picked.size() == count) {
            System.out.println(picked);
            return;
        }

        for (int idx = nowIdx; idx < source.size(); idx++) {
            picked.add(source.get(idx));
            pick(source, count, idx + 1);
            picked.remove(picked.size() - 1);
        }
    }
}
