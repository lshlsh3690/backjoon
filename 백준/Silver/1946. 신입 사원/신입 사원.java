import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static int T,N, ans;
    public static int[][]arr;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            solve();
        }
    }

    private static void solve() {
        Arrays.sort(arr, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        int min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            if(arr[i][1]<min){
                min = arr[i][1];
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static void init() throws IOException {
        ans=0;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

    }
}
