
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 이동 방향의 경우 1번부터 8번까지 → ↗ ↑ ↖ ← ↙ ↓ ↘으로

    static int N, M, D, P;
    static int[][] arr;
    static boolean[][] arr2;
    static Queue<int[]> q = new LinkedList<>();

    static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };

    static int[] dy2 = { -1, -1, 1, 1 };
    static int[] dx2 = { -1, 1, 1, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        arr2 = new boolean[N][N];

        // 초기 특수 영양제는 n×n 격자의 좌하단의 4개의 칸에 주어집니다.
        arr2[N - 1][0] = true;
        arr2[N - 1][1] = true;
        arr2[N - 2][0] = true;
        arr2[N - 2][1] = true;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int z = 0; z < M; z++) {
            st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken()) - 1;
            P = Integer.parseInt(st.nextToken());

            // 옮기기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr2[i][j]) {
                        arr2[i][j] = false;
                        int ny = i + ((dy[D] * P) % N);
                        int nx = j + ((dx[D] * P) % N);
                        if (ny < 0) {
                            ny = N + ny;
                        }
                        if (ny >= N) {
                            ny %= N;
                        }
                        if (nx < 0) {
                            nx = N + nx;
                        }
                        if (nx >= N) {
                            nx %= N;
                        }
                        q.add(new int[] { ny, nx });
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] poll = q.poll();

                int y = poll[0];
                int x = poll[1];

                arr2[y][x] = true;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr2[i][j]) {
                        arr[i][j] += 1;
                    }
                }
            }
            // 대각선 1이상인 개수 만큼 증가 시키기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr2[i][j]) {
                        int cnt = 0;
                        for (int k = 0; k < 4; k++) {
                            int ny = i + dy2[k];
                            int nx = j + dx2[k];
                            if (out(ny, nx) || arr[ny][nx] < 1) {
                                continue;
                            }
                            cnt++;
                        }
                        q.add(new int[] { i, j, cnt });
                    }
                }
            }

            while (!q.isEmpty()) {
                int[] poll = q.poll();
                int y = poll[0];
                int x = poll[1];
                int cnt = poll[2];
                arr[y][x] += cnt;
            }

            // 특수 영양제를 맞은 땅을 제외하고 높이가 2이상인 리브로수를 높이 2만큼 잘라내고 해당 땅 위에 특수 영양제를 올려줍니다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr2[i][j] || arr[i][j] < 2) {
                        continue;
                    }
                    q.add(new int[] { i, j });
                    arr[i][j] -= 2;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr2[i][j] = false;
                }
            }

            while (!q.isEmpty()) {
                int[] poll = q.poll();
                arr2[poll[0]][poll[1]] = true;
            }

        }

        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += arr[i][j];
            }
        }

        System.out.println(sum);
    }

    static boolean out(int y, int x) {
        return y < 0 || y >= N || x < 0 || x >= N;
    }
}
