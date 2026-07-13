import java.lang.*;
import java.io.*;
import java.util.*;
import java.math.*;

class Solution {
    static int N, M;
    static boolean [][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    public int[] solution(int n, int m, int[][] arr) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        visited = new boolean[n][m];
        
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(arr[i][j] <= 0)continue;
                if(visited[i][j])continue;
                visited[i][j] = true;
                q.add(new int[]{i,j});
                numberOfArea++;
                int same = arr[i][j];
                int range=0;
                while(!q.isEmpty()){
                    int[] poll = q.poll();
                    int y = poll[0];
                    int x = poll[1];
                    // System.out.println("y="+y + "  : x= "+ x);
                    range++;
                    for(int k = 0;k<4;k++){
                        int ny = y + dy[k];
                        int nx = x + dx[k];
                        if(ny<0 || ny >= n || nx < 0|| nx>= m){
                            continue;
                        }
                        if(arr[ny][nx] != same || visited[ny][nx]) continue;
                        visited[ny][nx] = true;
                        q.add(new int[]{ny,nx});
                    }
                }
                
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea,range);
            }
        }
        
        
        

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}