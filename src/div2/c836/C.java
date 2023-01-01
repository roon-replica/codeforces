package div2.c836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] inputs = br.readLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            int x = Integer.parseInt(inputs[1]);

            // try-1
                // multiple of i && use 2~n (first = x)
                // 1의 배수, 2의 배수, 3의 배수, 4의 배수, ..., n-1의 배수
                // if n > 2 && x = n-1 이면 절대 안 됨
                // else
                    // n == 2 -> {2 1}
                    // x != n-1
                        // n-1은 항상 마지막에 써줘야 함 : {1st 2nd 3rd ... n-1 1}
                        // n-2번째는.. n-2, 2*(n-2), 3*(n-2),... 등 a*(n-2) 가능
                        // ...
                        // k번째 -> a*k 가능.
                        // 최대한 자원을 효율적으로 쓰도록 그리디하게 채워넣으면 될거 같다
                            // n-1 th -> n-1
                            // n-2 th -> n-2의 배수 중에 가장 유리한 거? = 그냥 아무거나 채워넣을 수 있는걸로 넣으면 되려나..?

            // try-2 : 답 봄
            int[] answer = new int[n+1];
            answer[1] = x;
            answer[n] = 1;

            var factors = factor(n, x);
//            System.out.println(factors);

            boolean possible = true;

            for(int idx = 0, position = x;idx<factors.size();idx++, position += x){
                answer[position] = factors.get(idx);
            }

            for(int idx = 2; idx<=n-1;idx++){
                if(answer[idx]==0) answer[idx] = idx;
            }

            int[] cnt = new int[n+1];
            for(int i=1;i<=n;i++){
                cnt[answer[i]]++;
                if(cnt[answer[i]] >= 2) possible = false;
            }

            if(possible){
                for(int i=1;i<=n;i++){
                    System.out.print((answer[i]!=0 ? answer[i] : i) +" ");
                }
                System.out.println();
            }else{
                System.out.println(-1);
            }

        }
    }

    private static List<Integer> factor(int n,int x){
        List<Integer> factors = new ArrayList<>();
        n/=x;

        for(int i=2;i<=n;i++){
            for(;n%i ==0; n/=i){
                factors.add(i);
            }
        }

        List<Integer> ret = new ArrayList<>();
        ret.add(x);
        for(int f:factors){
            ret.add(ret.get(ret.size()-1) * f);
        }
        ret.remove(0);

        return ret;
    }
}
