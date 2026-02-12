import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int INF = 1000000000;
	static int N,M, startNum, destNum;
	static List<int[]>[]arr;
	static int[]distance;
	static boolean[]visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new ArrayList[N+1];
		distance = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1;i<N+1;i++) {
			arr[i]= new ArrayList<>();
			distance[i]=INF;
		}
		
		for(int i = 0;i<M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[start].add(new int[] {end, w});
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		startNum = Integer.parseInt(st.nextToken());
		destNum = Integer.parseInt(st.nextToken());
		
		distance[startNum] = 0;
		PriorityQueue<int[]>pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
		pq.add(new int[] {startNum,0});
		
		while(!pq.isEmpty()) {
			int[] poll = pq.poll();
			int idx = poll[0];
			int cost = poll[1];
			
			if (visited[idx]) {
				continue;
			}
			
			visited[idx]= true;
			
			for(int[] edge : arr[idx]) {
				int nextIdx = edge[0];
				int nextCost = edge[1];
				
				if (visited[nextIdx]) {
					continue;
				}
				
				if (distance[nextIdx] > cost + nextCost) {
					distance[nextIdx] = cost + nextCost;
					pq.add(new int[] {nextIdx, distance[nextIdx]});
				}
			}
		}
		
		
		System.out.println(distance[destNum]);
	}
}
