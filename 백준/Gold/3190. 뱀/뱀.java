import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[][] board;
    public static ArrayList<command> commands;
    public static ArrayList<snake>snakes;
    public static int directionIdx=0;
    public static String[] directions = {"RIGHT", "DOWN", "LEFT","UP"};
    public static boolean isFinished;
    public static class command{
        int x;
        String c;

        public command(int x, String c) {
            this.x = x;
            this.c = c;
        }
    }
    public static class snake{
        int y;
        int x;
        public snake(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        int idx=0;
        for (int i = 1;; i++) {
            proceed();
            if (isFinished) {
                //게임오버
                System.out.println(i);
                return;
            }
            if (idx < commands.size()) {
                if (commands.get(idx).x == i) {
                    String command = commands.get(idx).c;
                    if (command.equals("L")) {
                        directionIdx--;
                        if (directionIdx < 0) {
                            directionIdx = 3;
                        }
                    } else if (command.equals("D")) {
                        directionIdx++;
                        if (directionIdx > 3) {
                            directionIdx = 0;
                        }
                    }
                    idx++;
                }
            }
        }
    }

    private static void proceed() {
        int y = snakes.get(0).y;
        int x = snakes.get(0).x;
        int lastIdx = snakes.size()-1;
        int tailY = snakes.get(lastIdx).y;
        int tailX = snakes.get(lastIdx).x;
        if (directions[directionIdx].equals("RIGHT")) {
            //오른쪽
            if (x + 1 >= N || board[y][x+1] == 2) {
                isFinished = true;
                return;
            }
            snakes.add(0,new snake(y, x+1));
            if (board[y][x + 1] == 1) {
                board[y][x +1] = 2;
                return;
            }
            board[y][x+1] = 2;
            board[tailY][tailX] = 0;
            snakes.remove(snakes.size()-1);
        } else if (directions[directionIdx].equals("LEFT")) {
            if (x - 1 < 0 || board[y][x-1] == 2) {
                isFinished = true;
                return;
            }
            snakes.add(0,new snake(y,x-1));
            if (board[y][x - 1] == 1) {
                board[y][x -1] = 2;
                return;
            }
            board[y][x -1] = 2;
            board[tailY][tailX] = 0;
            snakes.remove(snakes.size()-1);
        } else if (directions[directionIdx].equals("UP")) {
            if (y - 1 < 0 || board[y -1][x] == 2) {
                isFinished = true;
                return;
            }
            snakes.add(0, new snake(y-1,x));
            if (board[y - 1][x] == 1) {
                board[y -1][x] = 2;
                return;
            }
            board[y -1][x] = 2;
            board[tailY][tailX] = 0;
            snakes.remove(snakes.size()-1);
        } else if (directions[directionIdx].equals("DOWN")) {
            if (y + 1 >= N || board[y +1][x] == 2) {
                isFinished = true;
                return;
            }
            snakes.add(0, new snake(y+1,x));
            if (board[y + 1][x] == 1) {
                board[y+1][x] = 2;
                return;
            }
            board[y+1][x] = 2;
            board[tailY][tailX] = 0;
            snakes.remove(snakes.size()-1);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        int appleCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < appleCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            board[y-1][x-1] = 1;
        }
        board[0][0] = 2;
        int commandCnt = Integer.parseInt(br.readLine());
        commands = new ArrayList<>(commandCnt);
        snakes = new ArrayList<>();
        snakes.add(new snake(0,0));
        for (int i = 0; i < commandCnt; i++) {
            String s = br.readLine();
            int x = Integer.parseInt(s.split(" ")[0]);
            String c = s.split(" ")[1];
            commands.add(new command(x,c));
        }
    }
}
