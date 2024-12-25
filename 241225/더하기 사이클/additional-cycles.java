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
        String [] arr=s.split("");
        int n = N % 10;
        int cnt=0;

        if(N == 0){
            System.out.println(1);
            return;
        }
        int sum =0;
        for(int i = 0;i<arr.length;i++){
            sum +=  Integer.parseInt(arr[i]);
        }
        int b= sum%10;
        int next = n * 10 + b;
        n= next%10;
        cnt++;
        while(true){
            sum=0;
            arr=String.valueOf(next).split("");
            for(int i = 0;i<arr.length;i++){
                sum +=  Integer.parseInt(arr[i]);
            }

            b = sum % 10;
            next = n * 10 + b;
            n = next%10;
            cnt++;
            if(N == next){
                break;
            }
        }
        System.out.println(cnt);
    }
}