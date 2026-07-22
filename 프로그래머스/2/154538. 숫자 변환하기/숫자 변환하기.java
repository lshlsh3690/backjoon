import java.util.*;

class Solution {
    static int X,Y,N;
    static int[] dp;
    public int solution(int x, int y, int n) {
        X = x;
        Y = y;
        N = n;
        dp = new int[Y+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        int answer = 0;
        
        answer = dp[Y];
        if(answer == Integer.MAX_VALUE){
            answer = -1;
        }
        
        Queue<Integer>q = new LinkedList<>();
        q.add(X);
        dp[X] = 0;
        
        while(!q.isEmpty()){
            int poll = q.poll();
            
            if(poll + N <= Y && dp[poll] + 1 < dp[poll + N]){
                dp[poll+N] = dp[poll] + 1;
                q.add(poll+N);
            }
            
            if(poll * 2 <= Y && dp[poll] + 1 < dp[poll * 2]){
                dp[poll * 2] = dp[poll] + 1;
                q.add(poll * 2);
            }
            
            if(poll * 3 <= Y && dp[poll] +1 < dp[poll*3]){
                dp[poll * 3] = dp[poll] + 1;
                q.add(poll * 3);
            }

        }
        
        answer = dp[Y];
        if(answer == Integer.MAX_VALUE){
            answer = -1;
        }
        
        return answer;
    }
}