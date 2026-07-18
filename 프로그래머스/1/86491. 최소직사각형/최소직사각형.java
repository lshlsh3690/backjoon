import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Solution {
    static int N;
    static int garo=Integer.MIN_VALUE, sero= Integer.MIN_VALUE;
    public int solution(int[][] sizes) {
        int answer = 0;
        
        N = sizes.length;
        
        
        
        for(int i = 0;i<N;i++){
            if(sizes[i][1] > sizes[i][0] ){
                int temp= sizes[i][1];
                sizes[i][1] = sizes[i][0];
                sizes[i][0] = temp;
            }
        }
        
        for(int i = 0;i<N;i++){
            garo = Math.max(garo, sizes[i][0]);
            sero = Math.max(sero, sizes[i][1]);
        }
        
        answer = garo * sero;
        
        
        System.out.println(garo+" "+sero);
        
        return answer;
    }
}