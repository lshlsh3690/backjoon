import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, D, W, K, ret, cnt;
	static int[][]arr;
	static boolean[]visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[D][W];
			visited= new boolean[D];
			ret =Integer.MAX_VALUE;

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 0이면 A 1이면 B
			if (check())
				ret = Math.min(ret, 0);
			
			for(int i = 0;i<D;i++) {
				int [] temp = new int[W];
				visited[i] =  true;
				for(int j=0;j<W;j++) {
					temp[j] = arr[i][j];
					arr[i][j] = 1;
				}
				dfs(i,1);
				
				for(int j=0;j<W;j++) {
					 arr[i][j] = 0;
				}
				dfs(i,1);
				for(int j = 0;j<W;j++) {
					arr[i][j] = temp[j];
				}
				visited[i] = false;
			}
			

			System.out.println("#"+t+" "+ret);
		}
	}
	
	public static void dfs(int idx, int depth) {
		if(idx >= D || ret <= depth) {
			return;
		}
		if(check()) {
			ret = Math.min(ret, depth);			
			return;
		}
		
		for(int i = idx;i<D;i++) {
			if(visited[i]) continue;
			int []temp = new int[W];
			visited[i] =true;
			for(int j=0;j<W;j++) {
				temp[j] = arr[i][j];
				arr[i][j] = 1;
			}
			dfs(i, depth+1);
			
			for(int j=0;j<W;j++) {
				arr[i][j] = 0;
			}
			dfs(i, depth+1);
			for(int j = 0;j<W;j++) {
				arr[i][j] = temp[j];
			}
			visited[i] = false;
		}
	}

	public static boolean check() {
		boolean[] tf = new boolean[W];
		loop1:for (int i = 0; i < W; i++) {
			for (int j = 0; j <= D-K; j++) {
				boolean flag = true; 
				int temp = arr[j][i];
				for(int k = 0;k<K;k++) {
					if(arr[j+k][i] != temp) {
						flag = false;
					}
				}
				if(flag) {
					tf[i] = true;
					continue loop1;
				}
			}
		}
		
		for(int i = 0;i<W;i++) {
			if(!tf[i]) {
				return false;
			}
		}

		return true;
	}
}
