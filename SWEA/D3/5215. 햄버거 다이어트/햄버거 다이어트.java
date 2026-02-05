import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static int T, N, L, result;
	public static Burger[] arr;
	public static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			result = Integer.MIN_VALUE;
			arr = new Burger[N];
			visited = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int score = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				arr[i]= new Burger(score, cal);
			}
			
			Arrays.sort(arr, (o1,o2) -> {
				if (o1.score > o2.score) {
					return -1;
				}else if (o1.score < o2.score) {
					return 1;
				}else {
					if (o1.kcal < o2.kcal) {
						return -1;
					}else if (o2.kcal > o1.kcal) {
						return 1;
					}
				}
				
				return 1;
			});

			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (arr[i].kcal > L) {
					visited[i] = true;
					continue;
				}
				visited[i] = true;
				result = Math.max(result, arr[i].score);
				dfs(i, arr[i].score, arr[i].kcal);

				visited[i] = false;
			}

			System.out.println("#" + t + " " + result);
		}
	}

	public static void dfs(int idx, int score, int kcal) {
		if (kcal > L) {
			return;
		}
		
		result = Math.max(result, score);
		
		for (int i = idx+1; i < N; i++) {
			if (visited[i])
				continue;
			if (kcal + arr[i].kcal > L)
				continue;
			visited[i] = true;
			dfs(i, score + arr[i].score, kcal + arr[i].kcal);
			visited[i] = false;
		}

	}
}

class Burger{
	int score;
	int kcal;
	public Burger(int score, int cal) {
		this.score = score;
		this.kcal =cal;
	}
}