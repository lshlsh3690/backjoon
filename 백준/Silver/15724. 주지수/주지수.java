import java.util.*;
import java.lang.*;

public class Main {
    private static int N, M,K;
    private static int [][]arr,sum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N][M];
        sum = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        K = sc.nextInt();
        List<int[]> l = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            l.add(new int[]{x1, y1, x2, y2});
        }

        sum[0][0] = arr[0][0];

        for (int i = 1; i < M; i++) {
            sum[0][i] = arr[0][i] + sum[0][i - 1];
        }

        for (int i = 1; i < N; i++) {
            sum[i][0] = arr[i][0] + sum[i - 1][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + arr[i][j];
            }
        }

        for (int[] e : l) {
            int y1 = e[0]-1;
            int x1 = e[1]-1;
            int y2 = e[2]-1;
            int x2 = e[3]-1;

            int result = sum[y2][x2];

            if (y1 == 0 && x1 == 0) {
            }else if (y1 != 0 && x1 == 0){
                result -= sum[y1-1][x2];
            } else if (y1 == 0 && x1 != 0) {
                result -= sum[y2][x1-1];
            } else if (x1 != 0 && y1 != 0) {
                result -= sum[y1-1][x2];
                result -= sum[y2][x1 - 1];
                result += sum[y1 - 1][x1 - 1];
            }

            System.out.println(result);
        }
    }
}
