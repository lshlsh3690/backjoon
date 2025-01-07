import javax.swing.text.ViewFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] arr;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    static int[] top_dy = {-1, 0, 1, 0};
    static int[] top_dx = {0, 1, 0, -1};
    static int[] top_dy2 = {0, -1, 0, 1};
    static int[] top_dx2 = {1, 0, -1, 0};
    static int top_direction = 0;
    static int bottom_direction = 0;
    static int top_air_direction = 0;
    static int bottom_air_direction = 0;

    static int[] bottom_dy = {1, 0, -1, 0};
    static int[] bottom_dx = {0, 1, 0, -1};

    static int[] bottom_dy2 = {0, 1, 0, -1};
    static int[] bottom_dx2 = {1, 0, -1, 0};
    static boolean[][] visited;

    static Queue<int[]> top;
    static ArrayList<int[]> top2;
    static Queue<int[]> bottom;
    static ArrayList<int[]> bottom2;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        visited = new boolean[R][C];


        top = new LinkedList<>();
        top2 = new ArrayList<>();
        bottom = new LinkedList<>();
        bottom2 = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    if (top.isEmpty()) {
                        top.add(new int[]{i, j});
                        top2.add(new int[]{i, j});
                    } else {
                        bottom.add(new int[]{i, j});
                        bottom2.add(new int[]{i, j});
                    }
                }
            }
        }


        boj_17144_dfs(0);


        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) {
                    sum += arr[i][j];
                }
            }
        }

        System.out.println(sum);

    }

    private static void boj_17144_dfs(int depth) {
        if (depth == T) {
            return;
        }

        int tmp[][] = new int[R][C];

        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] >= 5) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];

            int sub = arr[y][x] / 5;
            int k_cnt = 0;

            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (ny < 0 || nx < 0 || ny >= R || nx >= C) {
                    continue;
                }
                if (arr[ny][nx] == -1) {
                    continue;
                }

                k_cnt++;// 확산된 방향 카운트
                tmp[ny][nx] += sub;
            }
            arr[y][x] -= sub * k_cnt;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] += tmp[i][j];
            }
        }

        int[] poll = top.peek();
        int y = poll[0];
        int x = poll[1];

        top_direction = 0;

        while (true) {
            int ny = y + top_dy[top_direction];
            int nx = x + top_dx[top_direction];
            if (ny < 0 || nx < 0 || ny > poll[0] || nx >= C) {
                top_direction++;
                ny = y + top_dy[top_direction];
                nx = x + top_dx[top_direction];
            }

            if (ny == poll[0] && nx == poll[1]) {
                break;
            }

            arr[y][x] = arr[ny][nx];

            y = ny;
            x = nx;
        }

        arr[poll[0]][poll[1]] = -1;

        arr[poll[0]][poll[1]+1] = 0;



        poll = bottom.peek();
        y = poll[0];
        x = poll[1];
        bottom_direction = 0;
        while (true) {
            int ny = y + bottom_dy[bottom_direction];
            int nx = x + bottom_dx[bottom_direction];
            if (ny < poll[0] || nx < 0 || ny >= R || nx >= C) {
                bottom_direction++;
                ny = y + bottom_dy[bottom_direction];
                nx = x + bottom_dx[bottom_direction];
            }

            if (ny == poll[0] && nx == poll[1]) {
                break;
            }

            arr[y][x] = arr[ny][nx];

            y = ny;
            x = nx;
        }

        arr[poll[0]][poll[1]] = -1;
        arr[poll[0]][poll[1]+1] = 0;



        boj_17144_dfs(depth + 1);
    }

    private static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
