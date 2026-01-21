import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int [][]arr= new int[100][100];
        int N = Integer.parseInt(br.readLine());

        int [][]commands = new int[N][2];
        for (int i = 0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            commands[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        for(int z = 0;z<N;z++){
            int left = commands[z][0];
            int bottom = commands[z][1];


            int top = 100 - bottom- 10;
            for(int i = top;i<top+10 && i<100;i++){
                for(int j = left;j<left+10 && j<100;j++){
                    arr[i][j] = 1;
                }
            }
        }

        int cnt = 0;
        for (int i = 0;i<100;i++){
            for(int j = 0;j<100;j++){
                if(arr[i][j] == 1){
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
