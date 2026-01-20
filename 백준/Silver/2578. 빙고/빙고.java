import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int [][]arr;
    public static int bingoCnt;
    public static void main(String[] args) throws Exception{

        arr = new int[5][5];
        int[] commands = new int[25];
        bingoCnt = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                commands[(i * 5) + j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < 25; k++) {
            int command = commands[k];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (command == arr[i][j]) {
                        arr[i][j] = 0;//빙고부른 수는 0으로 초기화
                        checkBingo(i,j);
                        if (bingoCnt >= 3) {
                            System.out.println(k+1);
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void checkBingo(int y, int x) {
        //가로 체크
        boolean bingo = true;
        for (int i = 0; i < 5; i++) {
            if (arr[y][i] != 0) {
                bingo = false;
                break;
            }
        }

        if (bingo ) {
            bingoCnt++;
        }

        bingo = true;
        //세로 체크
        for (int i = 0; i < 5; i++) {
            if (arr[i][x] != 0) {
                bingo = false;
                break;
            }
        }

        if (bingo ) {
            bingoCnt++;
        }


        //좌상우하 체크
        if (y == x){
            bingo = true;
            for (int i = 0; i < 5; i++) {
                if (arr[i][i] != 0) {
                    bingo = false;
                    break;
                }
            }

            if (bingo) {
                bingoCnt++;
            }
        }


        if ((y == 4 && x == 0) || (y == 3 && x == 1) || (y == 2 && x == 2) || (y == 1 && x == 3) || (y == 0 && x == 4)) {
            bingo = true;
            for (int i = 0; i < 5; i++) {
                if (arr[4 - i][i] != 0) {
                    bingo = false;
                    break;
                }
            }

            if (bingo) {
                bingoCnt++;
            }
        }
    }
}
