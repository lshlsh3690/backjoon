import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static int[][] arr;
	public static int[] dy = {-1,0,1,0};
	static int[] dx= {0,1,0,-1};
	public static int H,W, N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		
		arr = new int[H][W];
		
		int y = H-1;
		int x = 0;
		arr[y][x]= 1; 
		int direction = 0;
		for(int i =1;i<H*W;i++) {
			int ny = y + dy[direction];
			int nx = x + dx[direction];
			if(ny <0|| ny>=H || nx <0||nx>=W || arr[ny][nx]!= 0 ) {
				direction++;
				direction %= 4;
				ny = y + dy[direction];
				nx = x + dx[direction];
			}
			
			arr[ny][nx] = arr[y][x]+1;
			y = ny;
			x = nx;
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j]== N ) {
					System.out.println((j+1) + " " + (H-i));
					return;
				}
			}
		}
		
		System.out.println(0);
		
	}

}