
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static String[] al= {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		int cnt = 0;
		for(int i = 0;i<s.length();i++) {
			if(s.charAt(i) == 'c') {
				if (i + 1 < s.length() && s.charAt(i+1) == '=') {
					cnt+=1;
					i++;
				}else if (i+ 1 < s.length() && s.charAt(i+1) == '-') {
					cnt+=1;
					i++;
				}else {
					cnt++;
				}
			}else if (s.charAt(i) == 'd') {
				if (i+2 <s.length() && s.charAt(i+1) == 'z' && s.charAt(i+2) == '=') {
					cnt+=1;
					i+=2;
				}else if( i + 1< s.length() && s.charAt(i+1) == '-') {
					cnt+=1;
					i++;
				}else {
					cnt++;
				}
			}else if (s.charAt(i)=='l' && i+1<s.length() && s.charAt(i+1) == 'j') {
				cnt+=1;
				i++;
			}else if (s.charAt(i)=='s' && i+1<s.length() && s.charAt(i+1) == '=') {
				cnt+=1;
				i++;
			}else if (s.charAt(i)=='z' && i+1<s.length() && s.charAt(i+1) == '=') {
				cnt+=1;
				i++;
			}else if (s.charAt(i)=='n' && i+1<s.length() && s.charAt(i+1) == 'j') {
				cnt+=1;
				i++;
			}else if (s.charAt(i)=='z' && i+1<s.length() && s.charAt(i+1) == '=') {
				cnt+=1;
				i++;
			}else {
				cnt++;
			}
				
		}
		System.out.println(cnt);
	}
}
