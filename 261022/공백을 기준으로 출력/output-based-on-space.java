import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String[] sarr = br.readLine().split(" ");
        for(String s : sarr){
            sb.append(s);
        }
        sarr = br.readLine().split(" ");
        for(String s : sarr){
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}