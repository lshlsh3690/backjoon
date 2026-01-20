import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N,M, X,Y,K;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N  = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int [][]arr = new int[N][M];

        for(int i = 0;i<N;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0;j<M;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        

        String [] command = br.readLine().split(" ");
        Dice dice = new Dice();


        if (arr[Y][X] == 0) {
            arr[Y][X] = 0;
        }else{
            dice.down = arr[Y][X];    
        }

        for(int i = 0;i<command.length;i++){

            int n = Integer.parseInt(command[i]);

            if (n==1) {
                //동쪽, 오른쪽
                int ny = Y;
                int nx = X + 1;
                if (nx >= M) {
                    continue;
                }

                int temp = dice.right;

                dice.right = dice.up;
                dice.up = dice.left;
                dice.left = dice.down;
                dice.down = temp;

                if (arr[ny][nx] == 0) {
                    arr[ny][nx] = dice.down;
                }else{
                    dice.down = arr[ny][nx];
                    arr[ny][nx] = 0;
                }

                Y = ny;
                X = nx;

            }else if (n==2) {
                //서쪽, 왼쪽

                int ny = Y;
                int nx = X-1;
                if (nx < 0) {
                    continue;
                }

                int temp = dice.left;

                dice.left = dice.up;
                dice.up = dice.right;
                dice.right = dice.down;
                dice.down = temp;

                if (arr[ny][nx] == 0) {
                    arr[ny][nx] = dice.down;
                }else{
                    dice.down = arr[ny][nx];
                    arr[ny][nx] = 0;
                }

                Y = ny;
                X = nx;

            }else if(n==3){
                //북쪽, 위쪽
                int ny = Y-1;
                int nx = X;
                if (ny < 0) {
                    continue;
                }

                int temp =  dice.behind;


                dice.behind =  dice.up;
                dice.up = dice.front;
                dice.front = dice.down;
                dice.down = temp;

                if (arr[ny][nx] ==0) {
                    arr[ny][nx]  = dice.down;
                }else{
                    dice.down = arr[ny][nx];
                    arr[ny][nx] = 0;
                }

                Y = ny;
                X = nx;
            }else if (n==4) {
                //남쪽 , 아래
                int ny = Y+1;
                int nx = X;
                if (ny >= N) {
                    continue;
                }

                int temp = dice.front;

                dice.front = dice.up;
                dice.up = dice.behind;
                dice.behind = dice.down;
                dice.down = temp;

                if (arr[ny][nx] == 0) {
                    arr[ny][nx]  = dice.down;
                }else{
                    dice.down = arr[ny][nx];
                    arr[ny][nx ] = 0;
                }

                Y = ny;
                X = nx;
            }
            

            System.out.println(dice.up);

        }
    }

    
}

class Dice {
    int up;
    int front;
    int left;
    int right;
    int down;
    int behind;

    public Dice(){
        this.up = 0;
        this.front = 0;
        this.behind = 0;
        this.down = 0;
        this.right = 0;
        this.left = 0;
    }
}