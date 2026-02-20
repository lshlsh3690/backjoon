
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	static int T, N=100;
	static int [][] arr;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static boolean[][] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1;t<=10;t++) {
			T = Integer.parseInt(br.readLine());
			arr = new int[100][100];
			int cury = 0;
			int curx = 0;
			int desty = 0;
			int destx = 0;
			
			visited = new boolean[100][100];
			for(int i = 0;i<N;i++) {
				String[] sarr = br.readLine().split("");
				for(int j = 0;j<N;j++) {
					arr[i][j]= Integer.parseInt(sarr[j]);
					if(arr[i][j] == 2) {
						cury = i;
						curx = j;
					}else if (arr[i][j]==3 ) {
						desty = i;
						destx = j;
					}
				}
			}
			
			visited[cury][curx] = true;
			Queue<int[]>q = new ArrayDeque<int[]>();
			q.add(new int[] {cury, curx});
			while(!q.isEmpty()) {
				int[] poll = q.poll();
				int y = poll[0];
				int x = poll[1];
				for(int i = 0;i<4;i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if(out(ny, nx) || visited[ny][nx] || arr[ny][nx] ==1) continue;
					visited[ny][nx] = true;
					q.add(new int[] {ny,nx});
				}
			}
			
			if(visited[desty][destx]) {
				System.out.println("#"+t+" "+1);
			}else {
				System.out.println("#"+t+" "+0);
			}
			
		}
	}
	
	static boolean out(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N;
	}
}
