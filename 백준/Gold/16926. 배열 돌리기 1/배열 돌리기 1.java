import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	public static int N,M,R;
	public static int arr[][];
	public static int []dy= {0,1,0,-1};
	public static int []dx= {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for	(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j =0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i = 0;i<R;i++) {
			turn();
//			printArr();
//			System.out.println();
		}
		
		printArr();
		
	}
	
	public static void turn() {
		boolean [][]visited = new boolean[N][M];
		for(int k = 0;k<N/2;k++)
		{	
			int temp = arr[k][k];
			int direction = 0;
			int y = k;
			int x = k;
			if(visited[k][k]) {
				break;
			}
			
			for(int i = 0;true;i++) {
				int ny = y + dy[direction];
				int nx = x + dx[direction];
				
				if(ny < k || ny >= N - k || nx < k || nx >= M- k) {
					direction++;
					direction %= 4;
					continue;
				}
				
				if(visited[ny][nx]) {
					break;
				}
				
				arr[y][x] = arr[ny][nx];

				visited[y][x] = true;
				y = ny;
				x = nx;
			}
			arr[k+1][k] = temp;
		}

	}

	public static void printArr() {
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
