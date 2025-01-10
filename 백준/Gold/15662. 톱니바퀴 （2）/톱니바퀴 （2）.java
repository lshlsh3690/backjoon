import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, K;
    static int[][] arr;

    static int[][] wheels;

    static int[] init_dy = {0, 0, 1, 1, 0, 0, -1, -1, 0};
    static int[] init_dx = {1, 1, 0, 0, -1, -1, 0, 0, 1};

    static int[] turnRightY = {1, 1, 0, 0, -1, -1, 0, 0};
    static int[] turnRightX = {0, 0, 1, 1, 0, 0, -1, -1};


    static int [] turnLeftY = {0, 0, 1, 1, 0, 0, -1, -1};
    static int [] turnLeftX = {1, 1, 0, 0, -1, -1, 0, 0};

    static int[][] commadns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        arr = new int[T][8];
        wheels = new int[3][T * 3];

        for (int i = 0; i < T; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }


        K = Integer.parseInt(br.readLine());
        commadns = new int[K][2];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            commadns[i][0] = Integer.parseInt(st.nextToken());
            commadns[i][1] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < T; i++) {
            int direction = 0;
            int y = 0;
            int x = i * 3;
            for (int j = 0; j < 8; j++) {

                int ny = y + init_dy[direction];
                int nx = x + init_dx[direction];

                wheels[ny][nx] = arr[i][j];
                direction++;
                y = ny;
                x = nx;
            }
        }
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < T * 3; j++) {
//                System.out.print(wheels[i][j]);
//                if (j % 3 == 2) {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
//
//        System.out.println();

        //첫 번째 정수는 회전시킨 톱니바퀴의 번호, 두 번째 정수는 방향이다. 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.
        for (int i = 0; i < K; i++) {
            boolean[] visited = new boolean[T];
            boj_15662_turn(commadns[i][0]-1, commadns[i][1], visited);
        }

//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < T * 3; j++) {
//                System.out.print(wheels[i][j]);
//                if (j % 3 == 2) {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }


        int cnt = 0;
            for (int j = 0; j < T * 3; j++) {
                if (j % 3 == 1) {
                    if (wheels[0][j] == 1) {
                        cnt++;
                    }

                }
            }

        System.out.println(cnt);

    }

    private static void boj_15662_turn(int wheelsNum, int rotDir, boolean[] visited) {
        int left = wheelsNum * 3;
        int right = left + 2;
        boolean turnLeft = false;
        boolean turnRight = false;

        visited[wheelsNum] = true;

        if (left - 1 >= 0 && wheels[1][left - 1] != wheels[1][left]) {
            turnLeft = true;
        }
        if (right + 1 < T * 3 && wheels[1][right + 1] != wheels[1][right]) {
            turnRight = true;
        }

        int y = 0;
        int x = left;
        int tmp = wheels[y][x];
        for (int i = 0; i < 8; i++) {

            if (rotDir == 1) {

                int ny = y + turnRightY[i];
                int nx = x + turnRightX[i];

                wheels[y][x] = wheels[ny][nx];


                y = ny;
                x = nx;
            }else{
                int ny = y + turnLeftY[i];
                int nx = x + turnLeftX[i];

                wheels[y][x] = wheels[ny][nx];


                y = ny;
                x = nx;
            }


        }


        if (rotDir == 1) {
            wheels[0][left + 1] = tmp;
        } else {
            wheels[1][left] = tmp;
        }

//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < T * 3; j++) {
//                System.out.print(wheels[i][j]);
//                if (j % 3 == 2) {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
//        System.out.println();

        if (turnLeft && !visited[wheelsNum - 1]) {
            boj_15662_turn(wheelsNum - 1, -rotDir, visited);
        }

        if (turnRight && !visited[wheelsNum + 1]) {
            boj_15662_turn(wheelsNum + 1, -rotDir, visited);
        }
    }
}
