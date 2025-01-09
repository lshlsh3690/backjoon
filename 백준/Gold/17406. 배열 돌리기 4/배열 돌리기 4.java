import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, ret = Integer.MAX_VALUE;
    static int[][] arr;
    static int[][] commands;
    static boolean[] tf;

    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        commands = new int[K][3];
        tf = new boolean[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            commands[i] = new int[]{a, b, c};
        }

        for (int i = 0; i < K; i++) {
            if (!tf[i]) {
                tf[i] = true;
                boj17406_dfs(i, 1, arr);
                tf[i] = false;
            }
        }

        System.out.println(ret);


    }

    private static void boj17406_dfs(int index, int depth, int[][] tmp) {
        int[][] result = boj17405_rotate(commands[index], tmp);
        if (depth == K) {
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < M; j++) {
                    sum += result[i][j];
                }
                ret = Math.min(ret, sum);
            }
            return;
        }
        for (int i = 0; i < K; i++) {
            if (!tf[i]) {
                int[][] tmp2 = new int[N][M];
                for (int j = 0; j < N; j++) {
                    tmp2[j] = result[j].clone();
                }

                tf[i] = true;
                boj17406_dfs(i, depth + 1, tmp2);
                tf[i] = false;
            }
        }
    }

    private static int[][] boj17405_rotate(int[] commands, int[][] arr2) {
        int[][] rotatedArr = new int[N][M];  // 새 배열 생성
        for (int i = 0; i < N; i++) {
            rotatedArr[i] = arr2[i].clone();  // 깊은 복사
        }

        int leftTopY = commands[0] - commands[2] - 1;
        int leftTopX = commands[1] - commands[2] - 1;
        int rightBottomY = commands[0] + commands[2] - 1;
        int rightBottomX =commands[1] + commands[2] - 1;

        while (leftTopY < rightBottomY && leftTopX < rightBottomX) {
            int y = leftTopY;
            int x = leftTopX;
            boolean flag = false;
            int start = arr2[y][x];
            int direction = 0;

            while (!flag) {
                int ny = y + dy[direction];
                int nx = x + dx[direction];

                if (ny < leftTopY || ny > rightBottomY || nx < leftTopX || nx > rightBottomX) {
                    direction++;
                    ny = y + dy[direction];
                    nx = x + dx[direction];
                }


                if (ny == leftTopY && nx == leftTopX) {
                    flag = true;
                }

                rotatedArr[y][x] = rotatedArr[ny][nx];
                y = ny;
                x = nx;
            }

            if (flag) {
                rotatedArr[leftTopY][leftTopX + 1] = start;
            }


            leftTopY++;
            leftTopX++;
            rightBottomY--;
            rightBottomX--;
        }


        return rotatedArr;
    }
}
