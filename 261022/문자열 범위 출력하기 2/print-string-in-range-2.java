import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) throws Exception{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        if(s.length()<n){
            sb = new StringBuilder(s);
            System.out.println(sb.reverse());
        }else{
            for(int i = s.length()-1; i >= s.length()-n; i--){
                sb.append(s.charAt(i));
            }

            System.out.println(sb.toString());
        }
    }
}