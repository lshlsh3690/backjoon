import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int V, E, startNodeNum;
	public static int u, v, w;
	public static int[][] arr;
	public static int[] distance;
	public static boolean[] visited;

//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//
//		V = Integer.parseInt(st.nextToken());//노드의 개수
//		E = Integer.parseInt(st.nextToken());//간선의 개수
//		startNodeNum = Integer.parseInt(br.readLine());//시작 노드의 번호
//
//		arr = new int[V + 1][V + 1];
//		distance = new int[V + 1];
//		visited = new boolean[V + 1];
//
//		for (int i = 0; i < V + 1; i++) {
//			distance[i] = Integer.MAX_VALUE;
//			for (int j = 0; j < V + 1; j++) {
//				arr[i][j] = Integer.MAX_VALUE;
//			}
//		}
//
//		for (int i = 0; i < E; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			u = Integer.parseInt(st.nextToken());
//			v = Integer.parseInt(st.nextToken());
//			w = Integer.parseInt(st.nextToken());
//			arr[u][v] = w;
//		}
//
//		distance[startNodeNum] = 0;
//
//		for (int i = 1; i < V + 1; i++) {
//			int idx = -1;
//			int min = Integer.MAX_VALUE;
//
//			//최소값 인덱스와 인덱스의 값을 저장함
//			for (int j = 1; j < V + 1; j++) {
//				if (!visited[j] && arr[i][j] < min) {
//					min = arr[i][j];
//					idx = i;
//				}
//			}
//
//			//만약 최소값이 없다면 break;
//			if (idx == -1) {
//				break;
//			}
//
//			//아직 방문하지 않은 가장 작은 값이 있다면 visited로 막아서 다시 방문하지 못하게 함
//			visited[idx] = true;
//
//			for (int next = 1; next < V+1; next++) {
//				if (visited[next])continue; //다음 노드를 탐색하는데 이제 방문하지 않은 노드중에서 골라야되므로 continue
//				if (arr[idx][next] != Integer.MAX_VALUE) {//다음 노드의 간선이 없다면 Integer.MAX_VALUE이기 때문에 조건문 설정
//					
//					//distance는 최소거리를 저장하기 때문에 현재 노드값, 현재 거리 + 다음 간선의 가중치 중 더 작은 값을 저장 
//					distance[next] = Math.min(distance[next], distance[idx] + arr[idx][next]);
//				}
//			}
//
//		}
//
//		for(int i = 1; i< V+1;i++) {
//			System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i] + " ");
//		}
//	}
	
	
//"======================================================================"
//	우선순위 큐를 사용한 다익스트라
	
	//간선
	
    static List<int[]>[] graph;  // 인접 리스트

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		V = Integer.parseInt(st.nextToken());//노드의 개수
		E = Integer.parseInt(st.nextToken());//간선의 개수
		startNodeNum = Integer.parseInt(br.readLine());//시작 노드의 번호

		graph = new ArrayList[V+1];
		distance = new int[V + 1];
		visited = new boolean[V + 1];

		for (int i = 0; i < V + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
			graph[i]= new ArrayList<>(); 
		}
		
		for(int i = 0;i<E;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new int[] {v,w});
		}
		
		//자동으로 최단거리의 노드부터 나옴
		PriorityQueue<int[]> pQueue = new PriorityQueue<>((a,b)->{
			return a[1] - b[1];
		});
		
		
		distance[startNodeNum] = 0;
		pQueue.add(new int[] {startNodeNum, 0});
		
		while (!pQueue.isEmpty()) {
            int[] poll = pQueue.poll();

            int idx = poll[0]; // 현재 노드
            int d = poll[1];   // 현재 노드까지의 거리(큐에 담겨온 값)

            // 이미 최단거리 확정된 노드면 스킵
            if (visited[idx]) continue;

            // idx 노드 최단거리 확정
            visited[idx] = true;

            // idx에서 인접 노드 완화(relax)
            for (int[] edge : graph[idx]) {
                int next = edge[0]; // 다음 노드
                int cost = edge[1]; // idx -> next 간선 비용

                if (visited[next]) continue;

                // 새로 계산한 거리
                // (주의) distance[idx]가 MAX_VALUE면 오버플로우 날 수 있으니 d 사용(이미 poll된 값)
                int nd = d + cost;

                // 핵심: next의 최단거리 갱신
                if (nd < distance[next]) {
                    distance[next] = nd;
                    pQueue.add(new int[]{next, nd}); // 반드시 "새 거리(nd)"를 넣어야 함
                }
            }
        }
		 // 출력
        for (int i = 1; i < V+1; i++) {
            System.out.print((distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]) + " ");
        }
	}
}
