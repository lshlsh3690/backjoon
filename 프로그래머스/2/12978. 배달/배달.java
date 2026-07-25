import java.math.*;
import java.util.*;

class Solution {
    static int N,K, INF = 1000000000;
    static List<int[]>[]edges;
    static boolean[] visited;
    static int[] distance;
    static PriorityQueue<int[]>pq = new PriorityQueue<>((o1,o2)->{
        return o1[1] - o2[1];
    });
    public int solution(int N, int[][] road, int K) {
        N =N;
        K =K;
        
        
        edges = new ArrayList[N];
        visited = new boolean[N];
        distance = new int[N];
        for(int i = 0;i<N;i++){
            distance[i] = INF;
            edges[i]=new ArrayList<>();
        }
        
        
        for(int i = 0;i<road.length;i++){
            int start = road[i][0]-1;
            int end = road[i][1]-1;
            int cost = road[i][2];
            
            edges[start].add(new int[]{end,cost});
            edges[end].add(new int[]{start,cost});
        }
                
        pq.add(new int[]{0, 0});
        distance[0] = 0;
        while(!pq.isEmpty()){
            int [] poll = pq.poll();
            int start =  poll[0];
            int cost = poll[1];
            
            if(visited[start])continue;
            
            visited[start] = true;
            
            for(int i = 0;i<edges[start].size();i++){
                int[] get = edges[start].get(i);
                int next = get[0];
                int nextCost = get[1];
                
                if(distance[next] > cost + nextCost && cost + nextCost <= K){
                    distance[next] = cost + nextCost;
                    pq.add(new int[]{next, cost + nextCost});
                }
            }
        }
        
        //System.out.println(Arrays.toString(distance));
        int cnt = 0;
        for(int i = 0;i<N;i++){
            if(distance[i] <= K){
                cnt++;
            }
        }
        
        
        
        
        return cnt;
    }
}