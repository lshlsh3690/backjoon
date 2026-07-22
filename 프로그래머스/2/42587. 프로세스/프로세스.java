import java.util.*;

class Solution {
    static int N;
    //idx, prio
    static PriorityQueue<int[]>pq = new PriorityQueue<>((o1,o2) -> {
        return o2[1] - o1[1];
    });
    public int solution(int[] priorities, int location) {
        N = priorities.length;
        
        int answer = 0;
        
        System.out.println(Arrays.toString(priorities));
        
        for(int i = 0;i<N;i++){
            pq.add(new int[]{i,priorities[i]});
        }
        
        int cnt=1;
        for(int i = 0;;i++){
            i%=N;
            if(priorities[i] == -1)continue;
            if(priorities[i] >= pq.peek()[1]){
                pq.poll();
                priorities[i] = -1;
                if(i == location){
                    return cnt;
                }
                cnt++;
            }
        }
        
        
        
        
    }
}