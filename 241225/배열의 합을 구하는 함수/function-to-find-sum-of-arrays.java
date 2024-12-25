import java.lang.*;
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][]arr;
    public static void main(String[] args) throws Exception {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N+1][N+1];

        for (int i =0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int sum = 0;
            for(int j =0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum += arr[i][j];
            }
            arr[i][N] = sum;
        }

        for(int i = 0;i<N;i++){
            int sum =0;
            for(int j =0;j<N;j++){
                sum += arr[j][i];
            }
            arr[N][i] = sum;
        }

       
        int a=0;
        int b=0;
        for(int i =0;i<N;i++){
            a += arr[i][N];
            b += arr[N][i];
        }
        arr[N][N] = a;

         for(int i =0;i<=N;i++){
            for(int j =0;j<=N;j++){
                System.out.printf(arr[i][j]+" ");
            }
            System.out.println();
        }

    }
}