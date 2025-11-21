import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] asc = new int[N];
        int[] desc = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            asc[i] = 1;
            desc[i] =1;
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    asc[i] = Math.max(asc[j] + 1, asc[i]);
                }
            }
        }

        for (int i = N-1; i >=0 ; i--) {
            for (int j = N-1; j > i; j--) {
                if (arr[j] < arr[i]) {
                    desc[i] = Math.max(desc[j] + 1, desc[i]);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            result = Math.max(result, asc[i] + desc[i] - 1);
        }
        System.out.println(result);
    }
}
