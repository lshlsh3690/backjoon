
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, Q, len;
	static int[][] arr;
	static int[] L;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		len = (int) Math.pow(2, N);
		arr = new int[len][len];
		L = new int[Q];
		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < len; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}

		for (int l : L) {
		    int s = 1 << l;

		    // 1) 부분 격자 시계 방향 회전
		    for (int sy = 0; sy < len; sy += s) {
		        for (int sx = 0; sx < len; sx += s) {
		            int[][] temp = new int[s][s];

		            // (y,x) <- (s-1-x, y)  (시계 90도)
		            for (int y = 0; y < s; y++) {
		                for (int x = 0; x < s; x++) {
		                    temp[y][x] = arr[sy + s - 1 - x][sx + y];
		                }
		            }

		            for (int y = 0; y < s; y++) {
		                for (int x = 0; x < s; x++) {
		                    arr[sy + y][sx + x] = temp[y][x];
		                }
		            }
		        }
		    }

		    // 2) 녹이기(동시 적용)
		    List<int[]> dec = new ArrayList<>();
		    for (int y = 0; y < len; y++) {
		        for (int x = 0; x < len; x++) {
		            if (arr[y][x] <= 0) continue;

		            int cnt = 0;
		            for (int k = 0; k < 4; k++) {
		                int ny = y + dy[k];
		                int nx = x + dx[k];
		                if (ny < 0 || ny >= len || nx < 0 || nx >= len) continue;
		                if (arr[ny][nx] > 0) cnt++;
		            }

		            if (cnt < 3) dec.add(new int[] { y, x });
		        }
		    }

		    for (int[] p : dec) arr[p[0]][p[1]]--;
		}

		int sum = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				sum += arr[i][j];
			}
		}
		System.out.println(sum);
		
		int ret = Integer.MIN_VALUE;
		Queue<int[]>q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[len][len];
		for(int i = 0;i<len;i++) {
			for(int j = 0;j<len;j++) {
				int cnt = 0;
				if(arr[i][j] > 0 && !visited[i][j]) {
					visited[i][j] =true;
					cnt++;
					q.add(new int[] {i,j});
					while(!q.isEmpty()) {
						int[] poll = q.poll();
						int y = poll[0];
						int x = poll[1];
						for(int k =  0;k<4;k++) {
							int ny =  y +dy[k];
							int nx =  x+dx[k];
							if(ny< 0 || ny >= len || nx < 0 || nx >= len || visited[ny][nx] || arr[ny][nx] <= 0)continue;
							visited[ny][nx] =true;
							q.add(new int[] {ny,nx});
							cnt++;
						}
					}
				}
				ret = Math.max(ret, cnt);
			}
			
		}
		System.out.println(ret);
	}
}
