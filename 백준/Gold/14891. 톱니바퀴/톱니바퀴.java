import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] wheels;
    public static int[] dyRight = {0, 1, 0, -1};
    public static int[] dxRight = {1, 0, -1, 0};
    public static int[] dyLeft = {1, 0, -1, 0};
    public static int[] dxLeft = {0, 1, 0, -1};
    public static int K;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        wheels = new int[3][12];

        for (int i = 0; i < 4; i++) {
            String[] st = br.readLine().split("");
            wheels[0][(i * 3) + 1] = Integer.parseInt(st[0]);
            wheels[0][(i * 3) + 2] = Integer.parseInt(st[1]);
            wheels[1][(i * 3) + 2] = Integer.parseInt(st[2]);
            wheels[2][(i * 3) + 2] = Integer.parseInt(st[3]);
            wheels[2][(i * 3) + 1] = Integer.parseInt(st[4]);
            wheels[2][(i * 3) + 0] = Integer.parseInt(st[5]);
            wheels[1][(i * 3) + 0] = Integer.parseInt(st[6]);
            wheels[0][(i * 3) + 0] = Integer.parseInt(st[7]);
        }

        K = Integer.parseInt(br.readLine());

//        System.out.println();
//        printArr();
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int wheelNum = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            visited = new boolean[4];
            visited[wheelNum] = true;
            turn(wheelNum, direction);
        }


        int sum = 0;
        if (wheels[0][1] == 1) {
            sum++;
        }
        if (wheels[0][4] == 1) {
            sum += 2;
        }
        if (wheels[0][7] == 1) {
            sum += 4;
        }
        if (wheels[0][10] == 1) {
            sum += 8;
        }

        System.out.println(sum);
    }

    public static void turn(int wheelNum, int direction) {
        int temp = wheels[0][3 * wheelNum];

        int y = 0;
        int x = 3 * wheelNum;
        int k = 0;
        boolean turnLeft = wheelNum != 0 && wheels[1][3 * wheelNum] != wheels[1][(3 * wheelNum) - 1] && !visited[wheelNum-1];
        boolean turnRight = wheelNum != 3 && wheels[1][3 * wheelNum + 2] != wheels[1][3 * (wheelNum+1)] && !visited[wheelNum+1];
        for (int i = 0; i < 8; i++) {
            if (direction == 1) {
                //오른쪽
                int ny = y + dyLeft[k];
                int nx = x + dxLeft[k];
                if (ny < 0 || ny >= 3 || nx < 3 * wheelNum || nx >= 3 * (wheelNum+1)) {
                    k++;
                    i--;
                    continue;
//                    ny = y + dyLeft[k];
//                    nx = x + dxLeft[k];
                }
                wheels[y][x] = wheels[ny][nx];
                y = ny;
                x = nx;

            } else if (direction == -1) {
                //왼쪽
                int ny = y + dyRight[k];
                int nx = x + dxRight[k];
                if (ny < 0 || ny >= 3 || nx < 3 * wheelNum || nx >= 3 * (wheelNum + 1)) {
                    k++;
                    i--;
                    continue;
//                    ny = y + dyRight[k];
//                    nx = x + dxRight[k];
                }
                wheels[y][x] = wheels[ny][nx];
                y = ny;
                x = nx;
            }
        }
        if (direction == 1) {
            wheels[0][3 * wheelNum + 1] = temp;
        } else if (direction == -1) {
            wheels[1][3 * wheelNum] = temp;
        }

//        System.out.println(wheelNum + " : " + direction);
//        printArr();

        if (turnLeft) {
            visited[wheelNum-1] = true;
            turn(wheelNum - 1, direction * -1);
        }

        if (turnRight) {
            visited[wheelNum+1] = true;
            turn(wheelNum + 1, direction * -1);
        }
    }

    public static void printArr() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(wheels[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
