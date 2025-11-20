import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringBuilder sb = new StringBuilder(br.readLine());
            int cnt = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '1') {
                    int len = 0;
                    while (i + len < sb.length() && sb.charAt(i + len) == '1') {
                        len++;
                    }

                    for (int j = i;j <sb.length() ; j++) {
                        if (sb.charAt(j) == '1') {
                            sb.setCharAt(j, '0');
                        }else{
                            sb.setCharAt(j, '1');
                        }
                    }
                    cnt++;
                    i += len-1;
                }
            }

            System.out.println("#"+ t+" " +cnt);
        }
    }
}

/*
1010011
0101100
0010011
0001100
0000011
0000000
 */