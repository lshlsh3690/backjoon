import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        dp = new int[K + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = 1;

        //동전의 가치 오름차순으로 정렬
        Arrays.sort(arr);

        /*
1
1

2
1 + 1
2

3
1 + 1 + 1
2 + 1

4
1 + 1 + 1+ 1
2 + 1 + 1
2 + 2

5
1 + 1 + 1+ 1+ 1
2 + 1+ 1+
2 + 2+1
5
         */

        for (int j = 0; j < N; j++) {
            for (int i = 1; i <= K; i++) {
                int coin = arr[j];
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        System.out.println(dp[K]);
    }
}
