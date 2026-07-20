import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;

class Solution {
    static List<String>l1,l2;
    static int N1,N2;
    static Map<String,Integer>m1,m2;
    public int solution(String str1, String str2) {
        int answer = 0;
        
        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        
        m1 = new HashMap<>();
        m2 = new HashMap<>();
        
        N1 = str1.length();
        N2 = str2.length();
        for(int i = 0;i<N1-1;i++){
            char first = str1.charAt(i);
            char second = str1.charAt(i + 1);

            if (!isEnglishLetter(first) || !isEnglishLetter(second))
        continue;
            
             String s = String.valueOf(first) + String.valueOf(second);
            s =s.toLowerCase();
            if(m1.containsKey(s)){
                int cnt = m1.get(s);
                cnt++;
                m1.put(s,cnt);
            }else{
                m1.put(s,1);
            }
            
        }
        
        
        for(int i = 0;i<N2-1;i++){
            char first = str2.charAt(i);
            char second = str2.charAt(i + 1);

            if (!isEnglishLetter(first) || !isEnglishLetter(second)) continue;
            
            String s = String.valueOf(first) + String.valueOf(second);
            s =s.toLowerCase();
            if(m2.containsKey(s)){
                int cnt = m2.get(s);
                cnt++;
                m2.put(s,cnt);
            }else{
                m2.put(s,1);
            }
        }
        
        int gyo = 0;
        for(Map.Entry<String,Integer>entry : m1.entrySet()){
            String key = entry.getKey();
            
            int cnt1= entry.getValue();
            Integer cnt2 = m2.get(key);
            if(cnt2 == null)continue;
            
            
            gyo += Math.min(cnt1,cnt2);
        }
        
        System.out.println(gyo);
        int hap = 0;
        
        for(Map.Entry<String,Integer>entry : m1.entrySet()){
            String key = entry.getKey();
            
            int cnt1= entry.getValue();
            Integer cnt2 = m2.get(key);
            if(cnt2 == null){
                hap += cnt1;
                continue;
            }
            
            hap += Math.max(cnt1,cnt2);
        }
        
        
        for(Map.Entry<String,Integer>entry : m2.entrySet()){
            String key = entry.getKey();
            
            int cnt1= entry.getValue();
            Integer cnt2 = m1.get(key);
            if(cnt2 == null){
                hap += cnt1;
            }
        }
        
        System.out.println(hap);
        if(gyo == 0 && hap == 0){
            return 65536;
        }
        
        float ret = ((float)gyo/(float)hap) * 65536;
        
        return (int)ret;
    }
    
     private boolean isEnglishLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }
}