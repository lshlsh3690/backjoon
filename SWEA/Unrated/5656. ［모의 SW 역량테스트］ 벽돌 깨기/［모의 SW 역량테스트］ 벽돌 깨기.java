import java.io.*;
import java.util.*;

public class Solution {
	
	static int T, N, W, H;
	static int ans ;
	static int[][] arr;
	static int[][] bombedArr;
	static int[][] gravityArr;
	static boolean[][] visited; 
	
	static ArrayDeque<int[]> que = new ArrayDeque<>();
	static int[] selected;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	 public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 T = Integer.parseInt(br.readLine());
		 
		 for (int tc = 1 ; tc <= T ; tc ++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 W = Integer.parseInt(st.nextToken());
			 H = Integer.parseInt(st.nextToken());
			 ans = Integer.MAX_VALUE;
			 arr = new int[H][W];
			 bombedArr = new int[H][W];
			 gravityArr = new int[H][W];
			 visited = new boolean[H][W];
			 selected = new int[N];
			 que.clear();
			 
			 for (int i = 0 ; i < H ; i ++) {
				 st = new StringTokenizer(br.readLine());
				 for (int j = 0 ; j < W ; j ++) {
					 arr[i][j] = Integer.parseInt(st.nextToken());
				 }
			 }

			 selectBombColOrder(0); // 폭탄을 떨어트릴 열의 순서를 중복 순열로 구한다 O(W^N)
			 
//			 initBombedArr();
//			 
//			 bomb(2);
//			 initGravityArr();
//			 gravity();
//			 
//			 bomb(2);
//			 initGravityArr();
//			 gravity();
//			 
//			 bomb(6);
//			 initGravityArr();
//			 gravity();
//			 
//			 System.out.println("============================");
//			 for (int[] a : bombedArr) {
//				 System.out.println(Arrays.toString(a));
//			 }

			 System.out.println("#" + tc + " " + ans);
		 }
	}
	 
	 static void selectBombColOrder(int idx) {
		 if(idx == N) {
			 // 폭탄 터트릴 arr 배열 복사, visited 배열 초기화 
			 initBombedArr();
			 initVisited();
			 
			 for(int col : selected) {
				 bomb(col);
				 initGravityArr();
				 gravity();
			 }
			 
			 ans = Math.min(ans, cntBlock());
			 
			 
			 return;
		 }
		 
		 for (int i = 0 ; i < W ; i ++) {
			 selected[idx] = i;
			 selectBombColOrder(idx + 1);
		 }
	 }
	 
	 static void bomb(int col) {
		 // 해당 열에서 가장 처음 만나는 0이 아닌 원소를 que에 넣는다.
		 // que가 빌 떄 까지 que에 있는 값을 터트리고, 연쇄 작용될 곳을 que에 넣는다.
		 
		 for (int i = 0 ; i < H ; i ++) {
			 if(bombedArr[i][col] != 0) {
				 que.add(new int[] {i, col, bombedArr[i][col]});
				 bombedArr[i][col] = 0;
				 break;
			 }
		 }
		 
		 while(!que.isEmpty()) {
			 int[] cur = que.poll();
			 int x = cur[0];
			 int y = cur[1];
			 int dist = cur[2];
			 
			 
			 for (int i = 0 ; i < 4 ; i ++) {
				 for (int d = 0 ; d < dist ; d ++) {
					 int nx = x + dx[i] * d;
					 int ny = y + dy[i] * d;
					 
					 if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
					 if(bombedArr[nx][ny] != 0) {
						 que.add(new int[] {nx, ny, bombedArr[nx][ny]});
						 bombedArr[nx][ny] = 0;
					 }
				 }
			 }
			 
		 }
	 }
	 
	 static void gravity() {
		 for (int j = 0 ; j < W ; j ++) {
			 int col = H - 1;
			 for (int i = H - 1 ; i >= 0 ; i --) {
				 if(bombedArr[i][j] != 0) {
					 gravityArr[col --][j] = bombedArr[i][j];
				 }
			 }
		 }
		 
		 for (int i = 0 ; i < H ; i ++) {
			 for (int j = 0 ; j < W ; j ++) {
				 bombedArr[i][j] = gravityArr[i][j];
			 }
		 }
	 }
	 
	 static int cntBlock() {
		 int cnt = 0;
		 
		 for (int i = 0 ; i < H ; i ++) {
			 for (int j = 0 ; j < W ; j ++) {
				 if (bombedArr[i][j] != 0) ++ cnt;
			 }
		 }
		 
		 return cnt;
	 }
	 
	 static void initBombedArr() {
		 for (int i = 0 ; i < H ; i ++) {
			 for (int j = 0 ; j < W ; j ++) {
				 bombedArr[i][j] = arr[i][j];
			 }
		 }
	 }
	 
	 
	 static void initGravityArr() {
		 for (int i = 0 ; i < H ; i ++) {
			 for (int j = 0 ; j < W ; j ++) {
				 gravityArr[i][j] = 0 ;
			 }
		 }
	 }

	 
	 static void initVisited() {
		 for (int i = 0 ; i < H ; i ++) {
			 for (int j = 0 ; j < W ; j ++) {
				 visited[i][j] = false;
			 }
		 }
	 }
}
