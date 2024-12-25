import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String []arr;
    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        N = Integer.parseInt(s);
        int n = N % 10;
        int cnt=1;
        int sum = 0;
        int b=0;
        int temp=0;
        while(true){
            cnt++;
            sum =0;
            b=0;
            arr = String.valueOf(temp).split("");
            for(int i =0;i<arr.length;i++){
                sum += Integer.parseInt(arr[i]);
            }
            // System.out.println(sum);

            b = sum % 10;
            // System.out.println(n);
            // System.out.println(b);
            temp = n * 10 + b;
            // System.out.println(temp);
            if(N == temp){
                break;
            }
        }
        System.out.println(cnt);
    }
}