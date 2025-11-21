import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, P;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        String[] s = br.readLine().split("");

        st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        ACGT[] arr = new ACGT[N];
        int cnt = 0;
        if (s[0].equals("A")) {
            arr[0] = new ACGT(1, 0, 0, 0);
        } else if (s[0].equals("C")) {
            arr[0] = new ACGT(0, 1, 0, 0);
        } else if (s[0].equals("G")) {
            arr[0] = new ACGT(0, 0, 1, 0);
        }else{
            arr[0] = new ACGT(0, 0, 0, 1);
        }


        for (int i = 1; i < s.length; i++) {
            if (s[i].equals("A")) {
                arr[i] = new ACGT(arr[i-1].aCnt+1, arr[i-1].cCnt, arr[i-1].gCnt, arr[i-1].tCnt);
            } else if (s[i].equals("C")) {
                arr[i] = new ACGT(arr[i-1].aCnt, arr[i-1].cCnt+1, arr[i-1].gCnt, arr[i-1].tCnt);
            } else if (s[i].equals("G")) {
                arr[i] = new ACGT(arr[i-1].aCnt, arr[i-1].cCnt, arr[i-1].gCnt+1, arr[i-1].tCnt);
            }else{
                arr[i] = new ACGT(arr[i-1].aCnt, arr[i-1].cCnt, arr[i-1].gCnt, arr[i-1].tCnt+1);
            }
        }

        if (P <= N) {
            int aCnt = arr[P-1].aCnt;
            int cCnt = arr[P-1].cCnt;
            int gCnt = arr[P-1].gCnt;
            int tCnt = arr[P-1].tCnt;

            if (aCnt >= A && cCnt >= C && gCnt >= G && tCnt >= T) {
                cnt++;
            }
        }

        for (int i = P; i < N; i++) {
            int aCnt = arr[i].aCnt - arr[i - P].aCnt;
            int cCnt = arr[i].cCnt - arr[i - P].cCnt;
            int gCnt = arr[i].gCnt - arr[i - P].gCnt;
            int tCnt = arr[i].tCnt - arr[i - P].tCnt;

            if (aCnt >= A && cCnt >= C && gCnt >= G && tCnt >= T) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}

class ACGT{
    int aCnt;
    int cCnt;
    int gCnt;
    int tCnt;

    public ACGT(int aCnt, int cCnt, int gCnt, int tCnt) {
        this.aCnt = aCnt;
        this.cCnt = cCnt;
        this.gCnt = gCnt;
        this.tCnt = tCnt;
    }
}