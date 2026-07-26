import java.util.*;
import java.math.*;

class Solution {
    static int N;
    static int[][] dp;
    public int solution(int[][] triangle) {
        int answer = 0;
        N = triangle.length;
        int max = Integer.MIN_VALUE;
        for(int i = 0;i<N;i++){
            max= Math.max(max, triangle[i].length);
        }
        dp = new int[N][max];
        for(int j = 0;j<max;j++){
            dp[N-1][j] = triangle[N-1][j];
        }
        for(int i = N-1;i>=1;i--){
            for(int j = 1;j<triangle[i].length;j++){
                // System.out.print(triangle[i][j] + " ");
                int left = dp[i][j-1];
                int right=dp[i][j];
                
                // System.out.println(left+" "+right);
                dp[i-1][j-1] = Math.max(left,right) + triangle[i-1][j-1];
            }
        }
        // System.out.println();
        // for(int i = 0;i<N;i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        return dp[0][0];
    }
}