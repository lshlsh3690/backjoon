import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int T, N, X, M;
	public static int[] arr;
	public static int[][] commands;
	public static boolean isStopped = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			isStopped = false;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());// 우리의 개수
			X = Integer.parseInt(st.nextToken());// 햄스터의 최대 마리수
			M = Integer.parseInt(st.nextToken());// 명령어 개수

			arr = new int[N];
			
			commands = new int[M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				// i,r,s
				commands[i][0] = Integer.parseInt(st.nextToken()) - 1;
				commands[i][1] = Integer.parseInt(st.nextToken()) - 1;
				commands[i][2] = Integer.parseInt(st.nextToken());
			}

			
			loop1: for (int i = N - 1; i >= 0; i--) {
				for (int j = X; j >= 0; j--) {
					arr[i] = j;
					dfs(N - i);
					if (isStopped) break loop1;
					arr[i] = 0;
				}
			}

			if (!isStopped) {
				System.out.println("#" + t + " " + -1);
			} else {
				boolean[] asdf=new boolean[N];
				for(int i =0;i<M;i++) {
					int l = commands[i][0];
					int r = commands[i][1];
					int s = commands[i][2];
					for (int k = l; k <= r; k++) {
						asdf[k]= true; 
					}
				}
				for(int i = 0;i<N;i++) {
					if (!asdf[i]) {
						arr[i]=X; 
					}	
				}
				
				
				StringBuilder sb = new StringBuilder();
				sb.append("#" + t + " ");
				for (int i = 0; i < N; i++) {
					sb.append(arr[i] + " ");
				}
				System.out.println(sb);
			}
		}

	}

	public static void dfs(int depth) {
		boolean flag= true;
		loop2: for (int i = 0; i < M; i++) {
			int l = commands[i][0];
			int r = commands[i][1];
			int s = commands[i][2];
			int tSum = 0;
			for (int k = l; k <= r; k++) {
				tSum += arr[k];
			}
			if(tSum != s) {
				flag = false;
				break loop2;
			}
		}
		if (flag) {
			isStopped=true;
			return;
		}

		for (int i = N - (depth + 1); i >= 0; i--) {
			for (int j = X; j >= 0; j--) {
				arr[i] = j;
				dfs(N - i);
				if (isStopped) {
					return;
				}
				arr[i] = 0;
			}
		}
	}
}
