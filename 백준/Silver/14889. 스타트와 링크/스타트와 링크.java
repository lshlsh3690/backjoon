import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, depth, ret = Integer.MAX_VALUE;

    static List<Integer> teamA;
    static List<Integer> teamB;
    static int[][] arr;
    static boolean[] tf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        depth = N / 2;
        tf = new boolean[N];
        teamA = new ArrayList<>();
        teamB = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        boj14889_dfs(0, 0);
        System.out.println(ret);
    }

    private static void boj14889_dfs(int idx, int cnt) {
        if (cnt == depth) {
            int sumA =0;
            int sumB=0;

            for (int i = 0; i < N; i++) {
                if (tf[i]) {
                    for (int j = i+1; j < N; j++) {
                        if (tf[j]) {
                            sumA += arr[i][j] + arr[j][i];
                        }
                    }

                } else if (!tf[i]) {
                    for (int j = i+1; j < N; j++) {
                        if (!tf[j]) {
                            sumB += arr[i][j] + arr[j][i];
                        }
                    }
                }
            }


            ret=Math.min(ret, Math.abs(sumA-sumB));


            teamA.clear();
            teamB.clear();
            return;
        }
        for (int i = idx; i < N; i++) {
            if (!tf[i]) {
                tf[i] = true;
                boj14889_dfs(i+1, cnt+1);
                tf[i] = false;
            }
        }
    }
}
