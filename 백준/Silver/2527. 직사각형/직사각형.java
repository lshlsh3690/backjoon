import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            //겹치지 않는 경우
            if (p1 < x2 || q1 < y2 || p2 < x1 || q2 < y1) {
                System.out.println("d");
                continue;
            }

            //점 4개
            if ((p1 == x2 && q1 == y2)){
                System.out.println("c");
                continue;
            }
            if (y1 == q2 && p1  == x2){
                System.out.println("c");
                continue;
            }
            if (y1 == q2 && x1 == p2){
                System.out.println("c");
                continue;
            }
            if (p1 == y2 && x1 == p2) {
                System.out.println("c");
                continue;
            }

            //면4개
            if (q1 == y2 && (p2 > x1 || x2 < p1)) {
                //윗선 겹침
                System.out.println("b");
                continue;
            }

            if (p1 == x2 && (y2 < q1 || q2 < y1)) {
                System.out.println("b");
                continue;
            }

            if (y1 == q2 && (p2 > x1 || x2 < p1)){
                System.out.println("b");
                continue;
            }

            if (x1 == p2 && (y2 < q1 || q2 < y1)){
                System.out.println("b");
                continue;
            }

            System.out.println("a");

        }
    }
}
