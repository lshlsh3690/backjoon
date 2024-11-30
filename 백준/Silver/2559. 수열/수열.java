import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;

    static int[] arr, sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        sum = new int[n + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            arr[i] = temp;
            if (i == 1){
                sum[1] = arr[1];
                continue;
            }
            sum[i] = sum[i - 1] + temp;
        }

        long result = Long.MIN_VALUE;

        for (int i = k; i <= n; i++) {
            int value = sum[i] - sum[i - k];
            result = Math.max(result, value);
        }

        System.out.println(result);

    }
}
