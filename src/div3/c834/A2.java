package div3.c834;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String fullStr = "";
        for (int i = 0; i < 18; i++) {
            fullStr += "Yes";
        }

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String input = br.readLine();
            System.out.println(fullStr.contains(input) ? "YES" : "NO");

        }
    }
}
