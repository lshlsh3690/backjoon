import java.util.*;
import java.io.*;

class Solution {
    static int N;
    public long[] solution(long[] numbers) {
        N = numbers.length;
        long[] answer = new long[N];
        
        for(int i = 0;i<N;i++){
            long num = numbers[i];
            
            if (num % 2 == 0) {
                // 짝수는 마지막 비트가 0이므로 1만 더하면 된다.
                answer[i] = num + 1;
            } else {
                long bit = 1;

                // 오른쪽부터 처음 발견되는 0비트의 자릿값을 찾는다.
                while ((num & bit) != 0) {
                    bit <<= 1;
                }

                answer[i] = num + (bit >> 1);
            }
        }
        
        return answer;
    }
}