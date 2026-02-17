import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr, move;
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1,};
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static boolean[][] wasCloud;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        move = new int[M][2];//d, s

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            move[i][0] = Integer.parseInt(st.nextToken()) - 1;
            move[i][1] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> clouds = new ArrayDeque<>();
        Queue<int[]> movedClouds = new ArrayDeque<>();
        // (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다.
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});
        for (int m = 0; m < M; m++) {

            //모든 구름이 di 방향으로 si칸 이동한다.
            int d = move[m][0];
            int s = move[m][1];

            movedClouds.clear();
            wasCloud = new boolean[N][N];

            while (!clouds.isEmpty()) {
                int[] c = clouds.poll();
                int y = c[0];
                int x = c[1];
                for (int k = 0; k < s; k++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    y = ny;
                    x = nx;
                    if (y == N) {
                        y = 0;
                    } else if (y == -1) {
                        y = N - 1;
                    }

                    if (x == N) {
                        x = 0;
                    } else if (x == -1) {
                        x = N - 1;
                    }
                }

                movedClouds.add(new int[]{y, x});
                wasCloud[y][x] = true;
            }

            //2.각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
            for (int[] c : movedClouds) {
                arr[c[0]][c[1]]++;
            }

            //3.구름이 모두 사라진다.
            clouds.clear();

            //2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다.
            //물복사버그 마법을 사용하면,
            //대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다
            for (int[] c : movedClouds) {
                int cnt = 0;
                int y = c[0];
                int x = c[1];
                for (int k = 1; k < 8; k += 2) {
                    int ny = y + dy[k];
                    int nx = x + dx[k];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                    if (arr[ny][nx] > 0) {
                        cnt++;
                    }
                }
                arr[y][x] += cnt;
            }

            //바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
            // 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] < 2) continue;
                   if(wasCloud[i][j]) continue;
                    arr[i][j] -= 2;
                    clouds.add(new int[]{i, j});
                }
            }
        }
        //M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println(sum);
    }
}
