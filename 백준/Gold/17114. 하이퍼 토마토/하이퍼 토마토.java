import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int ret, zeroCnt;
    static int M, N, O, P, Q, R, S, T, U, V, W;
    static int[][][][][][][][][][][] arr;
//    static boolean[][][][][][][][][][][]visited;

    static int[][] dm = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    static int[][] dn = {
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    static int[][]d5 = {
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    static int[][] dp = {
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0}
    };
    static int[][] dq = {
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0}
    };
    static int[][] dr = {
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0}
    };
    static int[][] ds = {
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0}
    };
    static int[][] dt = {
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0}
    };
    static int[][] du = {
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0}
    };
    static int[][] dv = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0}
    };
    static int[][] dw = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1}
    };

    public static void main(String[] args) throws Exception {
        //큰 11차원 창고를 가지고 있다. 창고는 m × n × o × p × q × r × s × t × u × v × w 의 격자 모양이고, 각 칸에 토마토를 하나씩 보관할 수 있다.
        //정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //첫 줄에는 문제의 설명에서 창고의 크기를 나타내는 자연수 m, n, o, p, q, r, s, t, u, v, w가 주어진다.
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        O = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());


        //m,n 바꿔서 생성 n이 세로 m이 가로
        arr = new int[M][N][O][P][Q][R][S][T][U][V][W];//?
//        visited = new boolean[M][N][O][P][Q][R][S][T][U][V][W];

        ret = Integer.MAX_VALUE;
        /*
        6 4 1 1 1 1 1 1 1 1 1
        0 0 0 0 0 0 //m이 6이므로
        0 0 0 0 0 0 //n이 4세로
        0 0 0 0 0 0
        0 0 0 0 0 1
        */

        //뒤에서부터 for문 시작해야댐..
        Queue<int[]> queue = new ArrayDeque<>();
        zeroCnt=0;//익지 않은 토마토 개수
        for (int w=0;w<W;w++){
            for(int v=0;v<V;v++){
                for (int u=0;u<U;u++){
                    for (int t=0;t<T;t++){
                        for (int s = 0; s < S; s++) {
                            for (int r = 0; r < R; r++) {
                                for (int q = 0; q < Q; q++) {
                                    for (int p = 0; p < P; p++) {
                                        for (int o = 0; o < O; o++) {
                                            for (int n = 0; n < N; n++) {
                                                st = new StringTokenizer(br.readLine(), " ");
                                                for (int m = 0; m < M; m++) {
                                                    //정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
                                                    arr[m][n][o][p][q][r][s][t][u][v][w] = Integer.parseInt(st.nextToken());
                                                    if (arr[m][n][o][p][q][r][s][t][u][v][w] == 1) {
                                                        queue.add(new int[]{m, n, o, p, q, r, s, t, u, v, w});
//                                                        visited[m][n][o][p][q][r][s][t][u][v][w] = true;
                                                    } else if (arr[m][n][o][p][q][r][s][t][u][v][w] == 0) {
                                                        zeroCnt++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        dfs(queue, 0);

//        for (int w=0;w<W;w++){
//            for(int v=0;v<V;v++){
//                for (int u=0;u<U;u++){
//                    for (int t=0;t<T;t++){
//                        for (int s = 0; s < S; s++) {
//                            for (int r = 0; r < R; r++) {
//                                for (int q = 0; q < Q; q++) {
//                                    for (int p = 0; p < P; p++) {
//                                        for (int o = 0; o < O; o++) {
//                                            for (int n = 0; n < N; n++) {
//                                                for (int m = 0; m < M; m++) {
//                                                    //visited false고 arr==1이라면 방문되지 않은 토마토임
//                                                    if (!visited[m][n][o][p][q][r][s][t][u][v][w]  && arr[m][n][o][p][q][r][s][t][u][v][w] == 0) {
//                                                        ret = -1;
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (zeroCnt != 0){
            ret = -1;
        }
        System.out.println(ret);
    }

    static void dfs(Queue<int[]>queue, int depth) {

        while (true) {
            Queue<int[]> nextQ = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                //m, n, o, p, q, r, s, t, u, v, w
                int pm = poll[0];
                int pn = poll[1];
                int po = poll[2];
                int pp = poll[3];
                int pq = poll[4];
                int pr = poll[5];
                int ps = poll[6];
                int pt = poll[7];
                int pu = poll[8];
                int pv = poll[9];
                int pw = poll[10];
                for (int k = 0; k < 2; k++) {
                    for (int i = 0; i < 11; i++) {
                        int nm = pm + dm[k][i];
                        int nn = pn + dn[k][i];
                        int no = po + d5[k][i];//do는 예약어 때문에 못힘
                        int np = pp + dp[k][i];
                        int nq = pq + dq[k][i];
                        int nr = pr + dr[k][i];
                        int ns = ps + ds[k][i];
                        int nt = pt + dt[k][i];
                        int nu = pu + du[k][i];
                        int nv = pv + dv[k][i];
                        int nw = pw + dw[k][i];
                        if (out(nm, nn, no, np, nq, nr, ns, nt, nu, nv, nw)) {
                            continue;
                        }
//                        if (visited[nm][nn][no][np][nq][nr][ns][nt][nu][nv][nw]) {
//                            continue;
//                        }

                        if (arr[nm][nn][no][np][nq][nr][ns][nt][nu][nv][nw] == -1) {
                            continue;//벽이면 막힘
                        }

                        if (arr[nm][nn][no][np][nq][nr][ns][nt][nu][nv][nw] == 1) {
                            continue;//이미 익은 토마토여도 막힘
                        }

//                        visited[nm][nn][no][np][nq][nr][ns][nt][nu][nv][nw] = true;
                        arr[nm][nn][no][np][nq][nr][ns][nt][nu][nv][nw] = 1;
                        nextQ.add(new int[]{nm, nn, no, np, nq, nr, ns, nt, nu, nv, nw});
                        zeroCnt--;
                    }
                }
            }

            if (!nextQ.isEmpty()) {
                depth++;
                queue = nextQ;
            } else {
                if(ret > depth){
                    ret = depth;
                }
                break;
            }
        }
    }


    static boolean out(int m, int n, int o, int p, int q, int r, int s, int t, int u, int v, int w){
        return
                m < 0 || m >= M
                || n < 0 || n >= N
                || o < 0 || o >= O
                || p < 0 || p >= P
                || q < 0 || q >= Q
                || r < 0 || r >= R
                || s < 0 || s >= S
                || t < 0 || t >= T
                || u < 0 || u >= U
                || v < 0 || v >= V
                || w < 0 || w >= W;
    }
}
