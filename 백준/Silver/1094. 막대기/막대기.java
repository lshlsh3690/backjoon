import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int X, ret = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());

        boj1094_dfs(64,1);


        System.out.println(ret);
    }

    private static void boj1094_dfs(int len,int cnt) {
        if (X == len){
            ret = cnt;
            return;
        }
        if (len > X ){
            boj1094_dfs(len /2, cnt);
        }
        else if (len <= X){
            X -= len;
            boj1094_dfs(len/2, cnt+1);
        }
    }
}

/*

23

16 + 4 + 2 +1

64
32
16 16 16 16
16 8 8
16 4 4 8

24

16
8
*/

