import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    static int H,W, ret = Integer.MAX_VALUE;
    static char[][] map;
    static List<int[]>pos = new ArrayList<>();
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static boolean[][] visited;
    static PriorityQueue<int[]>pq = new PriorityQueue<>((o1,o2)->{
        if(o2[0] == o1[0]){
            return o2[1] - o1[1];
        }
        return o2[0]  - o1[0];
    });
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        
        visited = new boolean[H][W];
        for(int i = 0;i<H;i++){
            map[i] = br.readLine().toCharArray();
            for(int j = 0;j<W;j++){
                if(map[i][j] == '.'){
                    visited[i][j] = true;
                }else if(map[i][j] == '#'){
                    pos.add(new int[]{i,j});
                }
            }
        }

        for(int i = 0;i<pos.size();i++){
            int[] get = pos.get(i);
            int y = get[0];
            int x = get[1];
            int cnt = 0;
            int dir = -1;
            for(int k = 0;k<4;k++){
                int ny = y + dy[k];
                int nx = x + dx[k];
                if(out(ny,nx))continue;
                if(map[ny][nx] =='#'){
                    cnt++;
                    dir = k;
                }
            }
            if(cnt == 1){
                pq.add(new int[]{y,x, dir});
            }
        }

        int[] start = pq.poll();
        int y = start[0];
        int x = start[1];
        System.out.println((y+ 1) + " "+ (x+1));
        
        for(int i = 0;i<4;i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(out(ny,nx))continue;
            if(map[ny][nx] =='#'){
                if(i == 0){
                    System.out.println("^");
                }else if(i==1){
                    System.out.println(">");
                }else if(i==2){
                    System.out.println("v");
                }else if(i==3){
                    System.out.println("<");
                }
                break;
            }
        }

        Queue<int[]> q =new LinkedList<>();
        q.add(start);
        visited[y][x] = true;
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int[] poll = q.poll();
            y = poll[0];
            x = poll[1];
            int dir = poll[2];

            //LRA
            int nd = dir - 1;
            if(nd < 0) nd = 3;
            int ny = y + dy[nd];
            int nx = x + dx[nd];
            if(!out(ny,nx) && !visited[ny][nx]){
                sb.append("L");
                visited[ny][nx] = true;
                int ny2 = ny + dy[nd];
                int nx2 = nx + dx[nd];

                if(!out(ny2,nx2) && !visited[ny2][nx2]){
                    sb.append("A");
                    visited[ny][nx] = true;
                    visited[ny2][nx2] = true;
                    q.add(new int[]{ny2,nx2, nd});
                    continue;
                }
                
                
                q.add(new int[]{ny,nx,nd});
                continue;
            }

            //R
            nd = (dir + 1) % 4; 
            ny = y + dy[nd];
            nx = x + dx[nd];
            if(!out(ny,nx) && !visited[ny][nx]){
                sb.append("R");

                int ny2 = ny + dy[nd];
                int nx2 = nx + dx[nd];

                if(!out(ny2,nx2) && !visited[ny2][nx2]){
                    sb.append("A");
                    visited[ny][nx] = true;
                    visited[ny2][nx2] = true;
                    q.add(new int[]{ny2,nx2, nd});
                    continue;
                }

                visited[ny][nx] = true;
                q.add(new int[]{ny,nx,nd});
                continue;
            }

            //A
            ny = y + dy[dir];
            nx = x + dx[dir];
            
            
            if(!out(ny,nx) && !visited[ny][nx]){       
                int ny2 = ny + dy[dir];
                int nx2 = nx + dx[dir];
                sb.append("A");
                if(!out(ny2,nx2) && !visited[ny2][nx2]){
                    
                    visited[ny][nx] = true;
                    visited[ny2][nx2] = true;
                    q.add(new int[]{ny2,nx2, dir});
                    continue;
                }
                q.add(new int[]{ny,nx,dir});
                visited[ny][nx] = true;
                continue;
            }
        }
        
        System.out.println(sb.toString());
    }

    static boolean out(int y, int x){
        return y < 0 || y >= H || x < 0 || x>=W;
    }
}