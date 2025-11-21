import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        int j = 0;
        int cnt = 0;
        while (true) {
            int sum = 0;
            for (int k = i; k < j; k++) {
                sum += arr[k];
            }

            if (sum == M) {
                cnt++;
            } else if (sum > M) {
                i++;
                continue;
            }
            if (j == N) {
                break;
            }
            j++;
        }
        System.out.println(cnt);
    }
}
