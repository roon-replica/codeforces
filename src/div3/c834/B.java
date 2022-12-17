package div3.c834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // range is small so just implement

        for (int tc = 0; tc < T; tc++) {
            var inputs = br.readLine().split(" ");
            int m = Integer.parseInt(inputs[0]);
            int budget = Integer.parseInt(inputs[1]);

            var subArrayInputs = br.readLine().split(" ");
            var subArrays = Arrays.stream(subArrayInputs).map(Integer::parseInt).sorted().collect(Collectors.toList());

            int subArraySum = 0;
            int maxNumber = 0;
            for (int num : subArrays) {
                subArraySum += num;
                maxNumber = Math.max(maxNumber, num);
            }

            int requiredInRangeSum = maxNumber * (maxNumber + 1) / 2 - subArraySum;

            int outRangeSum = 0;
            boolean possible = false;
            for (int num = maxNumber; num <= 100; num++) {
                outRangeSum = num * (num + 1) / 2 - (requiredInRangeSum + subArraySum);

                if (requiredInRangeSum + outRangeSum == budget) {
                    possible = true;
                    break;
                }
            }

            System.out.println(((requiredInRangeSum + outRangeSum) != 0) && possible ? "YES" : "NO");
        }
    }
}
