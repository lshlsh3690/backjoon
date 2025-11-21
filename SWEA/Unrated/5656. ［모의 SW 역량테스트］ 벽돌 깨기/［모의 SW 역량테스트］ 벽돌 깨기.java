import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private static int N, H, W, T, result;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            int[][] arr = new int[H][W];
            result = Integer.MAX_VALUE;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int idx = 0; idx < W; idx++) {
                dfs(idx, 0, arr);
            }

            System.out.println("#" +t+ " " +result);
        }
    }

    private static void dfs(int idx, int depth, int[][] arr) {
        if (depth == N) {
            int sum = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (arr[i][j] > 0) {
                        sum++;
                    }
                }
            }
            result = Math.min(sum, result);

            return;
        }
        int[][] clone = new int[H][W];
        for (int i = 0; i < H; i++) {
            clone[i] = arr[i].clone();
        }

        int height = 0;
        while (clone[height][idx] == 0) {
            height++;
            if (height == H-1) {
                break;
            }
        }

        if (clone[height][idx] == 1) {
            clone[height][idx] = 0;
        }else{
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{height, idx});
            while (!q.isEmpty()) {
                int[] pos = q.poll();
                int y = pos[0];
                int x = pos[1];
                int distance = clone[y][x];
                clone[y][x] = 0;
                for (int d = 1; d<distance;d++){
                    for (int k = 0; k < 4; k++) {
                        int ny = y + (dy[k]*d);
                        int nx = x + (dx[k]*d);
                        if (ny < 0 || ny >= H || nx < 0 || nx >= W || clone[ny][nx] == 0) {
                            continue;
                        }

                        if (clone[ny][nx] == 1) {
                            clone[ny][nx] = 0;
                            continue;
                        }
                        if (clone[ny][nx] != 1) {
                            q.add(new int[]{ny, nx});
                        }
                    }
                }
            }
            for (int w = 0; w < W; w++) {
                for (int h = H-1; h > 0; h--) {
                    if (clone[h][w] == 0) {
                        int tmpH = h - 1;
                        while (clone[tmpH][w] == 0 && tmpH>0) {
                            tmpH--;
                        }
                        clone[h][w] = clone[tmpH][w];
                        clone[tmpH][w] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < W; i++) {
            dfs(i, depth + 1, clone);
        }

    }
}
