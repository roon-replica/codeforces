package div2.c836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            String s = br.readLine();
            for(int i=0; i< s.length(); i++){
                System.out.print(s.charAt(i));
            }

            for(int i=s.length()-1; i >=0; i--){
                System.out.print(s.charAt(i));
            }
            System.out.println();
        }
    }
}
