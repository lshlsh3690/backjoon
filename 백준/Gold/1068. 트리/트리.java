import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static int n,removeNum,cnt=0;
    public static int [] arr;
    public static boolean[]visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int root = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] == -1) root = i;
        }
        removeNum = Integer.parseInt(br.readLine());
        delete(removeNum);
        visited=new boolean[n];
        countLeaf(root);
        System.out.println(cnt);
    }

    public static void delete(int removeNum) {
        arr[removeNum] = -2;
        for (int i = 0; i < n; i++) {
            if (arr[i] == removeNum){
                delete(i);
            }
        }
    }

    public static void countLeaf(int s) {
        boolean isLeaf = true;
        visited[s] = true;
        if(arr[s] != -2) {
            for(int i = 0; i < n; i++) {
                if(arr[i] == s && visited[i] == false) {
                    countLeaf(i);
                    isLeaf = false;
                }
            }
            if(isLeaf) cnt++;
        }
    }

}
