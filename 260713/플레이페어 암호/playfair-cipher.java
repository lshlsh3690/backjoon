
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main {
    static boolean[] alphabet = new boolean[26];
    static String al = "ABCDEFGHIJKLMNOPQRSTUVEXYZ";
    static char[][] arr = new char[5][5];
    static String s, k;
    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        k = br.readLine();

        alphabet[9] = true;
        loop1: for (int i = 0; i < k.length(); i++) {
            char c = k.charAt(i);
            if (alphabet[c - 'A']) {
                continue;
            }
            for (int a = 0; a < 5; a++) {
                for (int b = 0; b < 5; b++) {
                    if (arr[a][b] == 0) {
                        arr[a][b] = c;
                        alphabet[c - 'A'] = true;
                        continue loop1;
                    }
                }
            }
        }

        loop2: for (int i = 0; i < 26; i++) {
            if (alphabet[i]) {
                continue;
            }
            for (int a = 0; a < 5; a++) {
                for (int b = 0; b < 5; b++) {
                    if (arr[a][b] == 0) {
                        arr[a][b] = (char) ('A' + i);
                        alphabet[i] = true;
                        continue loop2;
                    }
                }
            }
        }

//        for(int i = 0;i<5;i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }

        Queue<char[]> q = new ArrayDeque<>();
        char last = '0';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (last == '0') {
                last = c;
            } else {
                if (last == c && c != 'X') {
                    q.add(new char[] { last, 'X' });
                } else if (last == c && c == 'X') {
                    q.add(new char[] { last, 'Q' });
                } else if (last != c) {
                    q.add(new char[] { last, c });
                    last = '0';
                }
            }
        }
        if (last != '0') {
            q.add(new char[] { last, 'X' });
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char[] poll = q.poll();

            int aY = -1;
            int aX = -1;
            int bY = -1;
            int bX = -1;

            loop4: for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (arr[i][j] == poll[0]) {
                        aY = i;
                        aX = j;
                        break loop4;
                    }
                }
            }

            loop5: for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (arr[i][j] == poll[1]) {
                        bY = i;
                        bX = j;
                        break loop5;
                    }
                }
            }

            if (aY == bY) {
                sb.append(arr[aY][(aX + 1) % 5]);
                sb.append(arr[bY][(bX + 1) % 5]);
            } else if (aX == bX) {
                sb.append(arr[(aY + 1) % 5][aX]);
                sb.append(arr[(bY + 1) % 5][bX]);
            } else {
                sb.append(arr[aY][bX]);
                sb.append(arr[bY][aX]);
            }
        }

        System.out.println(sb.toString());
    }

    static boolean out(int y, int x) {
        return y < 0 || y >= 5 || x < 0 || x >= 5;
    }
}
