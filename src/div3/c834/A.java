package div3.c834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        String format = "Yes";

        for (int t = 0; t < T; t++) {
            String input = br.readLine();
            int startChar = input.charAt(0);
            int startIdx = -1;

            for (int i = 0; i < format.length(); i++) {
                if (startChar == format.charAt(i)) {
                    startIdx = i;
                    break;
                }
            }

            if (startIdx == -1) {
                System.out.println("NO");
                continue;
            }

            boolean possible = true;
            for (int i = 0, j = startIdx; i < input.length(); i++, j++, j %= 3) {
                if (input.charAt(i) != format.charAt(j)) {
                    possible = false;
                    break;
                }
            }

            System.out.println(possible ? "YES" : "NO");
        }
    }
}
