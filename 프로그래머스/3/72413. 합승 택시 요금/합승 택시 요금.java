import java.util.*;
import java.math.*;

class Solution {
    static int N,S, INF=1000000000;
    static resultDijk[]ret;
    static List<int[]>[] edges;
    static PriorityQueue<int[]>pq = new PriorityQueue<int[]>((o1,o2)->{
       return o1[1] - o2[1]; 
    });
    
    static class resultDijk{
        int[]distance;
        int[]paths;
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        S = s;
        
        ret = new resultDijk[3];//0 A, 1 B
        ret[0] = new resultDijk();
        ret[1] = new resultDijk();
        ret[2] = new resultDijk();
        
        edges = new ArrayList[N];
        for(int i = 0;i<N;i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int i = 0;i<fares.length;i++){
            int start = fares[i][0] - 1;
            int end = fares[i][1] - 1;
            int cost = fares[i][2];
            edges[start].add(new int[]{end, cost});
            edges[end].add(new int[]{start,cost});
        }
        
        resultDijk resultA = dijkstra(a-1);
        ret[0] = resultA;
        resultDijk resultB = dijkstra(b-1);
        ret[1] = resultB;
        resultDijk resultC = dijkstra(s-1);
        ret[2] = resultC;
        
        System.out.println(Arrays.toString(ret[0].distance));
        // System.out.println(Arrays.toString(ret[0].paths));
        System.out.println(Arrays.toString(ret[1].distance));
        // System.out.println(Arrays.toString(ret[1].paths));
        System.out.println(Arrays.toString(ret[2].distance));
        // System.out.println(Arrays.toString(ret[2].paths));
        
        int[] distA = ret[0].distance;
        int[] distB = ret[1].distance;
        int[] distS = ret[2].distance;

        int answer = INF;
        for (int k = 0; k < N; k++) {
            // k가 어느 한쪽에서도 도달 불가면 스킵 (INF끼리 더하면 오버플로/왜곡)
            if (distS[k] == INF || distA[k] == INF || distB[k] == INF) continue;
            int cost = distS[k] + distA[k] + distB[k];
            answer = Math.min(answer, cost);
        }
        return answer;
    }
    
    static resultDijk dijkstra(int  start){
        int [] d = new int[N];
        int [] prev = new int[N];
        Arrays.fill(d, INF);
        
        pq.clear();
        
        pq.add(new int[]{start,0});
        d[start] = 0;
        prev[start] = -1;
        
        while(!pq.isEmpty()){
            int[] poll = pq.poll();
            int s = poll[0];
            int cost = poll[1];
            
            if(d[s] < cost){
                continue;
            }
            
            for(int i = 0;i<edges[s].size();i++){
                int[] get = edges[s].get(i);
                int next =get[0];
                int nextCost = get[1];
                
                if(d[next] > cost + nextCost){
                    d[next] = cost + nextCost;
                    pq.add(new int[]{next, cost + nextCost});
                    prev[next] = s;
                }
            }
        }
        
        resultDijk result = new resultDijk();
        result.paths = prev;
        result.distance = d;
        
        
        return result;
    }
}