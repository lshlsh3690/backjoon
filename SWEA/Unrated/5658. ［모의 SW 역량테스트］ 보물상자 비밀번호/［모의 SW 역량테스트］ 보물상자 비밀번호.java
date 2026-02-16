import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int T,N,K,ret;
	static char[][]arr;
	static List<Integer>list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
//		1B3
//		B3B
//		81F
//		75E
		for(int t=1;t<=T;t++) {
			ret = 0;
			list = new ArrayList<Integer>();
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr =new char[4][N/4];
			String s = br.readLine();
			
			
			for(int i  = 0;i<s.length();i++) {
				arr[i/(N/4)][i% (N/4)] = s.charAt(i); 
			}
			
			for(int i = 0;i<=N;i++) {
				//계산부터
				calc();
				turn();
			}
			calc();
			Collections.sort(list, Collections.reverseOrder());
			
//			System.out.println(list);
			
			sb.append("#" + t+" "+list.get(K-1)+"\n");
		}
		System.out.println(sb);
	}
	
	static void turn() {
		char temp = arr[3][N/4-1];
		for(int i = 3;i>=0;i--) {
			for(int j = N/4-1;j>0;j--) {
				if(j == N/4-1 && i != 3) {
					arr[i+1][0] = arr[i][j];
				}
				arr[i][j] = arr[i][j-1];
			}
		}
		arr[0][0] = temp;
	}
	
	static void calc() {
		for(int i = 0;i<4;i++) {
			int temp = 0;
			int mul = 0;
			for(int j = N/4-1;j>=0;j--) {
				int num;
				if(arr[i][j] == 'A' || arr[i][j] == 'B' || arr[i][j] =='C' || arr[i][j] == 'D' || arr[i][j] == 'E' || arr[i][j] == 'F') {
					num = (int)(arr[i][j] - 'A' + 10);
				}else {
					num = (int)(arr[i][j] - '0');
				}
				
				temp += num * Math.pow(16, mul);
				mul++;
			}
			ret = Math.max(ret, mul);
			if(!list.contains(temp)) {				
				list.add(temp);
			}
		}
	}
}
