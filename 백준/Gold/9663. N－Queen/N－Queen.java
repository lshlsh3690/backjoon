import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int result = 0,N;
    private static int [][] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        dfs(0);

        System.out.println(result);
    }

    private static void dfs(int row){
        if (row == N) {
            result++;
            return;
        }
        int i = row;
        for (int j = 0; j < N; j++) {
            asdf : if (arr[i][j] == 0) {//퀸을 놓을 수 있는 자리
                for (int k = i - 1; k >= 0; k--) {
                    if (arr[k][j] == 1) {
                        break asdf;
                    }
                }

                //대각선 칠하기
                for (int n = 1; n < N; n++) {
                    int ny1 = i - n;
                    int nx1 = j - n;
                    int nx2 = j + n;
                    //3사분면
                    if (ny1 >= 0 && ny1 < N && nx1 >= 0 && nx1 < N && arr[ny1][nx1] == 1) {
//                        arr[ny2][nx1] = 1;
                        break asdf;
                    }
                    if (ny1 >= 0 && ny1 < N && nx2 >= 0 && nx2 < N && arr[ny1][nx2] == 1) {
//                        arr[ny2][nx2] = 1;
                        break asdf;
                    }
                }

                arr[i][j] = 1;
                dfs(i+1);
                arr[i][j] = 0;

            }
        }
    }
}
