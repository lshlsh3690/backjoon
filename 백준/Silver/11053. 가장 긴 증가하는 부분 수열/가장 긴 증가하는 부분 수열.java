import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] arr;
    public static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        arr = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
        }

        /*
7
10 20 60 60 60 30 40

7
10 20 10 10 10 30 40
         */

        

        for (int i = 1; i < N; i++) {
            int maxLen = Integer.MIN_VALUE;
            int maxIdx = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (maxLen < dp[j] && arr[j] < arr[i]) {
                    maxLen = dp[j];
                    maxIdx = j;
                }
            }

            if (maxIdx != -1 && arr[i] > arr[maxIdx]) {
                dp[i] += dp[maxIdx];
            }
        }


        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
