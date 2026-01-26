import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N, result;
    public static int[] dy = {0, 1, 0, -1};
    public static int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = Integer.MIN_VALUE;
        dfs(arr, 0);
        System.out.println(result);
    }

    public static void dfs(int[][] arr2, int depth) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    result = Math.max(result, arr2[i][j]);
                }
            }
            return;
        }
        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = arr2[i].clone();
        }
        boolean[][] added = new boolean[N][N];
        boolean moved = false;
        for (int i = 0; i < N; i++) {
            for (int j = N - 2; j >= 0; j--) {
                if (arr[i][j] == 0) {
                    continue;
                }
                int d = 1;
                int ny = Math.max(0, Math.min(N-1,i + (dy[0] * d)));
                int nx = Math.max(0, Math.min(N-1, j + (dx[0] * d)));
                while (arr[ny][nx] == 0) {
                    d++;
                    ny = i + (dy[0] * d);
                    nx = j + (dx[0] * d);
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        d--;
                        break;
                    }
                    if (arr[ny][nx] != 0 && (added[ny][nx] || arr[i][j] != arr[ny][nx] )) {
                        d--;
                        break;
                    }
                }
                 ny = Math.max(0, Math.min(N-1,i + (dy[0] * d)));
                 nx = Math.max(0, Math.min(N-1, j + (dx[0] * d)));
                 if (ny == i && nx == j) continue;

                if (arr[ny][nx] == 0) {
                    arr[ny][nx] = arr[i][j];
                    arr[i][j] = 0;
                    added[ny][nx] = false;
                    added[i][j] = false;
                    moved = true;
                } else if (arr[ny][nx] != 0 && !added[ny][nx] && arr[i][j] == arr[ny][nx]) {
                    arr[ny][nx] *= 2;
                    arr[i][j] = 0;
                    added[ny][nx] = true;
                    added[i][j] = false;
                    moved = true;
                } else {
//                    if (added[ny][nx] || arr[i][j] != arr[ny][nx]) {
//                        //그자리 인 경우
//                        continue;
//                    }
//                    //움직인 경우 못 움직인 경우 나눠야됌
//                    d--;
//                    ny = i + (dy[0] * d);
//                    nx = j + (dx[0] * d);
//                    arr[ny][nx] = arr[i][j];
//                    added[ny][nx] = added[i][j];
//                    arr[i][j] = 0;
//                    added[i][j] = false;
//                    moved = true;
                }
            }
        }
//        if (moved){
//            printArr(arr);
            dfs(arr, depth + 1);
//        }


        //아래로
        added = new boolean[N][N];
        moved = false;
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = arr2[i].clone();
        }
        for (int i = 0; i < N; i++) {
            for (int j = N - 2; j >= 0; j--) {
                if (arr[j][i] == 0) {
                    continue;
                }
                int d = 1;
                int ny = Math.max(0, Math.min(N-1, j + (dy[1] * d)));
                int nx = Math.max(0, Math.min(N-1, i + (dx[1] * d)));
                while (arr[ny][nx] == 0) {
                    d++;
                    ny = j + (dy[1] * d);
                    nx = i + (dx[1] * d);
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        d--;
                        break;
                    }
                    if (arr[ny][nx] != 0 && (added[ny][nx] || arr[j][i] != arr[ny][nx] )) {
                        d--;
                        break;
                    }
                }
                 ny = Math.max(0, Math.min(N-1, j + (dy[1] * d)));
                 nx = Math.max(0, Math.min(N-1, i + (dx[1] * d)));
                 if (ny == j && nx == i) continue;

                if (arr[ny][nx] == 0) {
                    arr[ny][nx] = arr[j][i];
                    arr[j][i] = 0;
                    added[ny][nx] = false;
                    added[j][i] = false;
                    moved = true;
                } else if (arr[ny][nx] != 0 && !added[ny][nx] && arr[j][i] == arr[ny][nx]) {
                    arr[ny][nx] *= 2;
                    arr[j][i] = 0;
                    added[ny][nx] = true;
                    added[j][i] = false;
                    moved = true;
                } else {
                    if (added[ny][nx] || arr[j][i] != arr[ny][nx]) {
                        //그자리 인 경우
                        continue;
                    }
//                    d--;
//                    ny = j + (dy[1] * d);
//                    nx = i + (dx[1] * d);
//                    arr[ny][nx] = arr[j][i];
//                    added[ny][nx] = added[j][i];
//                    arr[j][i] = 0;
//                    added[j][i] = false;
//                    moved = true;
                }
            }
        }
//        if (moved){
//            printArr(arr);
            dfs(arr, depth + 1);
//        }


        //왼쪽
        added = new boolean[N][N];
        moved = false;
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = arr2[i].clone();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                int d = 1;
                int ny = Math.max(0, Math.min(N-1, i + (dy[2] * d)));
                int nx = Math.max(0, Math.min(N-1, j + (dx[2] * d)));

                while (arr[ny][nx] == 0) {
                    d++;
                    ny = i + (dy[2] * d);
                    nx = j + (dx[2] * d);
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        d--;
                        break;
                    }
                    if (arr[ny][nx] != 0 && (added[ny][nx] || arr[i][j] != arr[ny][nx] )) {
                        d--;
                        break;
                    }
                }
                 ny = Math.max(0, Math.min(N-1, i + (dy[2] * d)));
                 nx = Math.max(0, Math.min(N-1, j + (dx[2] * d)));
                 if (ny == i && nx == j) continue;

                if (arr[ny][nx] == 0) {
                    arr[ny][nx] = arr[i][j];
                    arr[i][j] = 0;
                    added[ny][nx] = false;
                    added[i][j] = false;
                    moved = true;
                } else if (arr[ny][nx] != 0 && !added[ny][nx] && arr[i][j] == arr[ny][nx]) {
                    arr[ny][nx] *= 2;
                    arr[i][j] = 0;
                    added[ny][nx] = true;
                    added[i][j] = false;
                    moved = true;
                } else {
//                    if (added[ny][nx] || arr[i][j] != arr[ny][nx]) {
//                        //그자리 인 경우
//                        continue;
//                    }
//                    d--;
//                    ny = i + (dy[2] * d);
//                    nx = j + (dx[2] * d);
//                    arr[ny][nx] = arr[i][j];
//                    arr[i][j] = 0;
//                    added[ny][nx] = added[i][j];
//                    added[i][j] = false;
//                    moved = true;
                }
            }
        }
//        if (moved) {
//        printArr(arr);
            dfs(arr, depth + 1);
//        }


        //위
        added = new boolean[N][N];
        moved = false;
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr[i] = arr2[i].clone();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (arr[j][i] == 0) {
                    continue;
                }
                int d = 1;
                int ny = Math.max(0, Math.min(N-1, j + (dy[3] * d)));
                int nx = Math.max(0, Math.min(N-1, i + (dx[3] * d)));
                while (arr[ny][nx] == 0) {
                    d++;
                    ny = j + (dy[3] * d);
                    nx = i + (dx[3] * d);
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        d--;
                        break;
                    }
                    if (arr[ny][nx] != 0 && (added[ny][nx] || arr[j][i] != arr[ny][nx] )) {
                        d--;
                        break;
                    }
                }
                 ny = Math.max(0, Math.min(N-1, j + (dy[3] * d)));
                 nx = Math.max(0, Math.min(N-1, i + (dx[3] * d)));
                if (ny == j && nx == i)continue;

                if (arr[ny][nx] == 0) {
                    arr[ny][nx] = arr[j][i];
                    arr[j][i] = 0;
                    added[ny][nx] = false;
                    added[j][i] = false;
                    moved = true;
                } else if (arr[ny][nx] != 0 && !added[ny][nx] && arr[j][i] == arr[ny][nx]) {
                    arr[ny][nx] *= 2;
                    arr[j][i] = 0;
                    added[ny][nx] = true;
                    added[j][i] = false;
                    moved = true;
                } else {
//                    if (added[ny][nx] || arr[i][j] != arr[ny][nx]) {
//                        //그자리 인 경우
//                        continue;
//                    }
//                    d--;
//                    ny = j + (dy[3] * d);
//                    nx = i + (dx[3] * d);
//                    arr[ny][nx] = arr[j][i];
//                    arr[j][i] = 0;
//                    added[ny][nx] = added[j][i];
//                    added[j][i] = false;
//                    moved = true;
                }
            }
        }
//        if (moved) {
//            printArr(arr);
            dfs(arr, depth + 1);
//        }

    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
