import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br  =new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String word = br.readLine();
		
		int cnt=0;
		for(int i = 0;i<s.length();i++) {
			int idx=0;
			if (s.charAt(i) == word.charAt(idx)) {
				boolean flag = true;
				for(;idx<word.length();idx++) {
					if (i + idx >= s.length() || s.charAt(i+idx) != word.charAt(idx)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					cnt++;
					i += word.length()-1;
				}
			}
			
		}
		
		System.out.println(cnt);
	}
}
