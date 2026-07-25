import java.io.*;
import java.util.*;
import java.math.*;

class Solution {
    static int N,gateCnt;
    static int retIdx = -1;
    static long ret= Long.MAX_VALUE;
    static List<int[]>[] edges;
    static long distance[];
    static class Node{
        boolean isGate = false;
        boolean isSummit = false;
    }
    static Node[] nodes;
    static PriorityQueue<int[]>pq = new PriorityQueue<>((o1,o2) -> {
        return o1[1] - o2[1];
    });
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;
        gateCnt = gates.length;
        edges = new ArrayList[N];
        nodes = new Node[N];
        for(int i = 0;i<N;i++){
            nodes[i] = new Node();
        }
        for(int i = 0;i<N;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i = 0;i<gates.length;i++){
            int idx = gates[i]- 1;
            nodes[idx].isGate = true;
            pq.add(new int[]{idx, 0});
        }
        
        for(int [] edge: paths){
            int start = edge[0] -1;
            int end = edge[1] -1;
            int cost = edge[2];
            edges[start].add(new int[]{end,cost});
            edges[end].add(new int[]{start, cost});
        }
        
        
        for(int i = 0;i<summits.length;i++){
            int idx = summits[i] - 1;
            nodes[idx].isSummit= true;
        }

        
        
        distance = new long[N];
        Arrays.fill(distance, Long.MAX_VALUE);
        
        while(!pq.isEmpty()){
            int[]poll = pq.poll();
            int start =  poll[0];
            int cost = poll[1];
            
            if (cost > distance[start]) continue;   // ← 이 줄 추가 (봉우리 체크보다 먼저)
            if(nodes[start].isSummit)continue;
            
            for(int i = 0;i<edges[start].size();i++){
                int[] get = edges[start].get(i);
                int next = get[0];
                int nextCost = get[1];
                
                if(nodes[next].isGate)continue;
                int max = Math.max(nextCost, cost);
                if(max < distance[next]){
                    distance[next]  = max;
                    pq.add(new int[]{next,max});
                }
                
                
            }
        }
        
        
        
        
        Arrays.sort(summits);
        long best = Long.MAX_VALUE;
        int bestSummit = -1;
        for (int s : summits) {
            long d = distance[s - 1];
            if (d < best) {      // 등호 없음 → 동률이면 먼저 나온 작은 번호 유지
                best = d;
                bestSummit = s;
            }
        }
        return new int[]{bestSummit, (int) best};
    }
}