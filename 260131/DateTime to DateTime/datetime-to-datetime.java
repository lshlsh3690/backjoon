import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        // Please write your code here.

        if(A < 11 || (A <= 11 && B < 11) || (A <= 11 && B <= 11 && C < 11)){
            System.out.println(-1);
            return;
        }

        int sum = 0;
        int day = 11;
        int hour = 11;
        int minute = 11;
        while(day < A || hour < B || minute < C){
            minute++;
            sum++;
            if(minute == 60){
                hour++;
                minute = 0;
            }

            if(hour == 24){
                day++;
                hour= 0;
            }
        }

        System.out.println(sum);
    }
}