package div3.c834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class B2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            var inputs = br.readLine().split(" ");
            int m = Integer.parseInt(inputs[0]);
            int budget = Integer.parseInt(inputs[1]);

            var subArrayInputs = br.readLine().split(" ");
            var subArrays = Arrays.stream(subArrayInputs).map(Integer::parseInt).sorted().collect(Collectors.toList());
            int subArraySum = subArrays.stream().mapToInt(x -> x).sum();
            int maxNumber = subArrays.stream().max(Integer::compareTo).orElse(1);
            int permutationSum = maxNumber * (maxNumber + 1) / 2;

            budget -= (permutationSum - subArraySum);

            for (int num = maxNumber + 1; budget >= num; budget -= num, num++) {
            }

            System.out.println(budget == 0 ? "YES" : "NO");
        }
    }
}

