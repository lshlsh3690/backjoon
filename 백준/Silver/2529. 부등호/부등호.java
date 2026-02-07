import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int [] nums = {0,1,2,3,4,5,6,7,8,9};
    static boolean [] visited;
    static char [] signs;
    static int K;
    static String max_ret = "0";
    static String min_ret = "9876543210";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        signs = new char[K];
        String [] s = br.readLine().split(" ");
        for (int i = 0; i < K; i++) {
            signs[i] = s[i].charAt(0);
        }

        visited = new boolean[10];

        for (int i = 0; i < 10; i++) {
            visited[i] = true;
            boj2529_dfs( 0,nums[i]+"");
            visited[i] = false;
        }

        System.out.println(max_ret);
        System.out.println(min_ret);
    }

    private static void boj2529_dfs(int idx, String s) {
        if(idx >= K){
            if (Long.parseLong(max_ret) >= Long.parseLong(s)){
                max_ret = max_ret;
            }else if (Long.parseLong(max_ret) < Long.parseLong(s)){
                max_ret = s;
            }

            if (Long.parseLong(min_ret) <= Long.parseLong(s)){
                min_ret = min_ret;
            }else{
                min_ret = s;
            }

            return;
        }
        for (int i =0;i<10;i++){
            if (visited[i])
                continue;
            if (signs[idx] == '<'){
                if(nums[i] <= s.charAt(s.length()-1)-'0')
                    continue;
                visited[i] = true;
                boj2529_dfs(idx+1, s+i);
                visited[i] = false;
            } else if (signs[idx] == '>'){
                if (nums[i] >= s.charAt(s.length()-1)-'0')
                    continue;
                visited[i] = true;
                boj2529_dfs(idx+1, s+i);
                visited[i] = false;
            }
        }
    }
}
