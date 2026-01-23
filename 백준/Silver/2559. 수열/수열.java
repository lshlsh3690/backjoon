import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.lang.*;

public class Main {
    private static int N, K, result=Integer.MIN_VALUE;
    private static int[] arr,sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        sum = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }



        int tmp = 0;
        for (int i = 0; i < K; i++) {
            tmp += arr[i];
        }
        sum[K-1] = tmp;

        result = Math.max(result, tmp);
        for (int i = K; i < N; i++) {
            sum[i] =sum[i - 1] - arr[i - K] + arr[i];
            result = Math.max(result, sum[i]);
        }

        System.out.println(result);
    }
}
