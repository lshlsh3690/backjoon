import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static int N,M,K;
    public static int[] dyRight = {0, 1 , 0, -1};
    public static int[] dxRight = {1, 0, -1, 0};
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken())+1;
        N = Integer.parseInt(st.nextToken())+1;
        int arr[][] = new int[N][M];

        K = Integer.parseInt(br.readLine());
        List<int[]>pos = new ArrayList<>();
        for(int i =0;i<K;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int direction = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if (direction == 1) {
                pos.add(new int[]{0, distance});
                arr[0][distance] = 1;
            }else if (direction == 2) {
                pos.add(new int[]{N-1, distance});
                arr[N-1][distance] = 1;
            }else if (direction == 3) {
                pos.add(new int[]{distance,0});
                arr[distance][0]=1;
            }else if (direction == 4) {
                pos.add(new int[]{distance, M-1});
                arr[distance][M-1] = 1;
            }

        }

        st = new StringTokenizer(br.readLine(), " ");
        int direction = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());

        int y = 0;
        int x = 0;
        if (direction == 1) {
            arr[0][distance] = 2;  
            y = 0;
            x = distance;
        }else if(direction == 2){
            arr[N-1][distance] = 2;
            y = N-1;
            x = distance;
        }else if (direction == 3){
            arr[distance][0] = 2;
            y = distance;
            x = 0;
        }else if(direction == 4) {
            arr[distance][M-1] = 2;
            y = distance;
            x = M-1;
        }

        int sum = 0;
        for (int[] p : pos){
            int targetY = p[0];
            int targetX = p[1];

        
            int rightTurn = 0;
            int cy = y;
            int cx = x;

            int d;
            if (direction == 1) d = 0;      // 북 -> 오른쪽
            else if (direction == 4) d = 1; // 동 -> 아래
            else if (direction == 2) d = 2; // 남 -> 왼쪽
            else d = 3;   
            while (true) {
                if (cy == y && cx == x) {
                    rightTurn = 0;
                }
                if (cy == targetY && cx == targetX) {
                    break;
                }
                int ny = cy + dyRight[d];
                int nx = cx + dxRight[d];
                if (ny<0 || ny>= N || nx < 0 || nx >= M || ny != 0 && ny != N-1 && nx != 0 && nx != M-1) {
                    d++;
                    d %= 4;
                    continue;
                }
                cy = ny;
                cx = nx; 
                rightTurn++;
            }
            int leftTurn = (2*(N-1)) + (2*(M-1)) - rightTurn; 
            sum += Math.min(leftTurn, rightTurn);
        }
        System.out.println(sum);
    }
}
