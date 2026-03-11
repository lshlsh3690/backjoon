import java.util.*;

class Solution {
    static int N, M, INF=1000000000;
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        int [][] dp = new int [N][M];
        for(int i = 0;i<N;i++){
            for(int j = 0;j<M;j++){
                dp[i][j] = INF;
            }
        }
        Queue<int[]>q = new LinkedList<>();
        q.add(new int[]{0,0});
        dp[0][0] = 1;
        while(!q.isEmpty()){
            int[] poll = q.poll();
            int y = poll[0];
            int x = poll[1];
            for(int k = 0;k<4;k++){
                int ny = y + dy[k];
                int nx = x + dx[k];
                if(out(ny,nx) || maps[ny][nx] == 0 || dp[ny][nx] != INF){
                    continue;
                }
                dp[ny][nx] = dp[y][x]+1;
                q.add(new int[]{ny,nx});
            }
        }
        
        int answer = dp[N-1][M-1] == INF ? -1 : dp[N-1][M-1];
        
        return answer;
    }
    
    static boolean out(int y, int x){
        return y < 0 || y >= N || x < 0 || x>= M;
    }
}