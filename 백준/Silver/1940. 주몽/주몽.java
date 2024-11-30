import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cnt=0;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            recursive(i, i + 1);
        }

        System.out.println(cnt);
    }

    public static void recursive(int i, int k) {
        if (i >= N || k >= N) {
            return;
        }

        if (arr[i] + arr[k] == M) {
            cnt++;
        }

        recursive(i, k+1);

    }
}
