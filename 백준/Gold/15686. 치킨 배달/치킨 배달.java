import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N,M, falseCnt=0,distance=Integer.MAX_VALUE, chickenCnt=0, ans = Integer.MAX_VALUE;
    public static int[][]arr;
    public static boolean[] tf;
    public static ArrayList<Point>chickens = new ArrayList<>();
    public static ArrayList<Point>person = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        init();
        tf = new boolean[chickens.size()];
        slove();

        System.out.println(ans);
    }

    private static void slove() {
        backtracking(0,0);
    }

    private static void backtracking(int start, int cnt) {
//        if(falseCnt<(chickenCnt - M)) {
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    if (arr[i][j] == 2) {
//                        arr[i][j] = 0;
//                        falseCnt++;
//                        backtracking();
//                        arr[i][j] = 2;
//                        falseCnt--;
//                    }
//                }
//            }
//        }

        if(cnt == M){
            int res=0;
            for(int i=0;i<person.size();i++){
                int temp = Integer.MAX_VALUE;
                for(int j=0;j<chickens.size();j++) {
                    if (tf[j]) {
                        int distance2 = Math.abs(person.get(i).x - chickens.get(j).x) + Math.abs(person.get(i).y - chickens.get(j).y);

                        temp = Math.min(distance2, temp);
                    }
                }
                res += temp;
            }
            ans = Math.min(ans, res);
            return;
        }

        for(int i=start;i<chickens.size();i++){
            tf[i] = true;
            backtracking(i+1,cnt+1);
            tf[i] = false;
        }

//        else{
//            int sum = 0;
//            for(int i=0;i<N;i++){
//                for (int j = 0; j < N; j++) {
//                    int min = Integer.MAX_VALUE;
//                    if (arr[i][j] == 1) {
//                        for(int k=0;k<N;k++){
//                            for(int m=0;m<N;m++){
//                                if(arr[k][m] == 2){
//                                    min = Math.min(min, Math.abs(k-i)+Math.abs(m-j));
//                                }
//                            }
//                        }
//                        sum += min;
//                    }
//                }
//            }
//            distance = Math.min(distance, sum);
//        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr= new int[N][N];
        for(int i = 0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    Point chicken = new Point(i,j);
                    chickens.add(chicken);
                    chickenCnt++;
                } else if (arr[i][j] == 1) {
                    Point person2 = new Point(i,j);
                    person.add(person2);
                }
            }
        }
    }
}

class Point {
    int y;
    int x;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
