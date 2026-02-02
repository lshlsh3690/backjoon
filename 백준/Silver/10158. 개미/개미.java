import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int W,H, X,Y;
	public static long T;
	public static int[] dy = {-1,-1,1,1};
	public static int[] dx = {1,-1,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		//0,0에 갈수 있어서 +1 로 할당
		
		
		st = new StringTokenizer(br.readLine(), " ");
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(br.readLine());
		
		
		//Y,X의 이동을 각각 따로 해야됌
		int heightTime = (int)(T % (H*2));
		int widthTime = (int)(T % (W*2));
		int y = H-Y;
		int x = X;
		int direction=0;
		int dy = -1;
		int dx = 1;
		
		for(int ht=0;ht<heightTime;ht++) {
			int ny = y + dy;
			if(ny >= H+1 || ny < 0) {
				dy *= -1;
				ht--;
				continue;
			}
			y = ny;
		}
		
		for(int wt=0;wt<widthTime;wt++) {
			int nx = x + dx;
			if(nx >= W+1 || nx < 0) {
				dx *= -1;
				wt--;
				continue;
			}
			x = nx;
		}
		
		
		System.out.println(x + " "+ (H-y));
		
		
	}
}
