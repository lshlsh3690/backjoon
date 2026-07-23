import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ret = 0;
    static int[][] arr, goals;
    static boolean[][] visited;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st= new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        goals = new int[M][2];
        
        for(int i = 0;i<N;i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j =0 ;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0;i<M;i++){
            st = new StringTokenizer(br.readLine(), " ");
            goals[i][0] = Integer.parseInt(st.nextToken())-1;
            goals[i][1] = Integer.parseInt(st.nextToken())-1;
        }

        visited = new boolean[N][N];
        int cury = goals[0][0];
        int curx = goals[0][1];
        visited[cury][curx] = true;
        go(cury, curx, 1);

        System.out.println(ret);
    }

    static void go(int cury, int curx, int goalIdx){
        // System.out.println(cury +" "+curx + " " + ret);
        // for(int i = 0;i<N;i++){
        //     System.out.println(Arrays.toString(visited[i]));
        // }
        // System.out.println();
        if (goalIdx == M) {
            ret++;
            return;
        }
        for(int k = 0;k<4;k++){
            int ny = cury+dy[k];
            int nx = curx+dx[k];

            if(out(ny,nx) || visited[ny][nx] || arr[ny][nx] == 1){
                continue;
            }

            visited[ny][nx] = true;
            if(ny == goals[goalIdx][0] && nx == goals[goalIdx][1]){
                go(ny,nx, goalIdx+1);
            }else{
                go(ny,nx, goalIdx);
            }
            visited[ny][nx] = false;
        }
    }

    static boolean out(int y, int x){
        return y< 0 || y>=N || x< 0|| x>=N;
    }
}