import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr, oper;
    static long min=Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        oper = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
        }


        boj14888_dfs(1, arr[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void boj14888_dfs(int idx, int calcResult) {
        if (idx == N) {
            max = Math.max(max, calcResult);
            min = Math.min(min, calcResult);
//            System.out.println();
            return;
        }

        int right = arr[idx];
//        System.out.println(right+ " " + calcResult);
        for (int i = 0; i < 4; i++) {
            if (oper[i] > 0) {
                oper[i] -= 1;
                if (i == 0) {
                    boj14888_dfs(idx+1, calcResult+right);
                } else if (i == 1) {
                    boj14888_dfs(idx + 1, calcResult - right);
                } else if (i == 2) {
                    boj14888_dfs(idx+1, calcResult*right);
                }else if (i == 3 && right != 0){
                    boj14888_dfs(idx+1, calcResult/right);
                }
                oper[i] += 1;
            }
        }


    }
}
