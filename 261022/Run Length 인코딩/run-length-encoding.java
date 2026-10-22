import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        char lastC = s.charAt(0);
        sb.append(lastC);
        int cnt = 1;
        for(int i = 1;i<s.length();i++){
            char c = s.charAt(i);
            if(lastC == c){
                cnt++;
            }else{
                sb.append(cnt);
                cnt = 1;
                sb.append(c);
            }
            lastC = c;
        }

        sb.append(cnt);
       
        System.out.println(sb.length());
        System.out.println(sb.toString());
    }
}