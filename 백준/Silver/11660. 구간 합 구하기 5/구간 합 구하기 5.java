import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N,M;
	public static int[][]arr, sum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		sum = new int[N][N];
		
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0;j<N;j++) {
				arr[i][j]=  Integer.parseInt(st.nextToken());
			}
		}
		
		//누적합
		sum[0][0] = arr[0][0];
		for(int i = 1;i<N;i++) {
			sum[0][i] = sum[0][i-1] + arr[0][i];
		}
		
		for(int i = 1;i<N;i++) {
			sum[i][0] = sum[i-1][0] + arr[i][0];
		}
		
		for(int i = 1;i<N;i++) {
			for(int j = 1;j<N;j++) {
				sum[i][j] = sum[i-1][j]+ sum[i][j-1] - sum[i-1][j-1] + arr[i][j];  
			}
		}
		
		for(int i = 0;i<M;i++) {
			//여기는 일단 구간합 결과값 바로 출력하기
			st = new StringTokenizer(br.readLine(), " ");
			int y1 = Integer.parseInt(st.nextToken())-1;
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
			
			int result = sum[y2][x2];
			if (y1 > 0) {
				result -= sum[y1-1][x2];	
			}
			if (x1 > 0) {
				result -= sum[y2][x1-1];	
			}
			if (y1>0 && x1 > 0) {
				result += sum[y1-1][x1-1];
			}
			System.out.println(result);
			
		}
	}
}
