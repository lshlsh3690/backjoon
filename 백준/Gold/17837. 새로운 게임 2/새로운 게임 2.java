import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Horse {
        int y;
        int x;
        int horseNum;
        int d;

        public Horse(int y, int x, int horseNum, int d) {
            this.y = y;
            this.x = x;
            this.horseNum = horseNum;
            this.d = d;
        }
    }

    static class Cell {
        int y, x, color;
        //        0은 흰색, 1은 빨간색, 2는 파란색이다.
        List<Horse> horseList;

        public Cell(int y, int x, int color) {
            this.y = y;
            this.x = x;
            this.color = color;
            horseList = new ArrayList<>();
        }
    }

    static int N, K;
    static Cell[][] board;
    static Map<Integer, Horse> horseMap;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new Cell[N][N];
        horseMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int color = Integer.parseInt(st.nextToken());
                board[i][j] = new Cell(i, j, color);
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            Horse h = new Horse(y, x, i, d);
            board[y][x].horseList.add(h);
            horseMap.put(i, h);
        }

        int turn = 1;
        w:
        while (true) {
            if (turn > 1000) {
                break;
            }
            for (int horseNum = 0; horseNum < K; horseNum++) {
                Horse horse = horseMap.get(horseNum);
                int y = horse.y;
                int x = horse.x;
                int ny = y + dy[horse.d];
                int nx = x + dx[horse.d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                    //체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
                    if (horse.d == 0) {
                        horse.d = 1;
                    } else if (horse.d == 1) {
                        horse.d = 0;
                    } else if (horse.d == 2) {
                        horse.d = 3;
                    } else if (horse.d == 3) {
                        horse.d = 2;
                    }
                    ny = y + dy[horse.d];
                    nx = x + dx[horse.d];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue; 
                    if (board[ny][nx].color == 2) {
                        continue;
                    } else if (board[ny][nx].color == 0) {
                        List<Horse> curList = board[y][x].horseList;
                        int idx = -1;
                        for (int i = 0; i < curList.size(); i++) {
                            if (curList.get(i).horseNum == horseNum) {
                                idx = i;
                                break;
                            }
                        }
                        List<Horse> moving = new ArrayList<>();
                        for (int i = idx; i < curList.size(); i++) {
                            moving.add(curList.get(i));
                        }
                        for (int i = curList.size() - 1; i >= idx; i--) {
                            curList.remove(i);
                        }
                        board[ny][nx].horseList.addAll(moving);
//                        board[ny][nx].horseList.addAll(board[y][x].horseList);
//                        board[y][x].horseList.clear();
                        for (Horse h : moving) {
                            h.y = ny;
                            h.x = nx;
                        }
                    } else if (board[ny][nx].color == 1) {
//                        board[y][x].horseList.sort(Collections.reverseOrder());
                        List<Horse> curList = board[y][x].horseList;
                        int idx = -1;
                        for (int i = 0; i < curList.size(); i++) {
                            if (curList.get(i).horseNum == horseNum) {
                                idx = i;
                                break;
                            }
                        }
                        List<Horse> moving = new ArrayList<>();
                        for (int i = idx; i < curList.size(); i++) {
                            moving.add(curList.get(i));
                        }
                        for (int i = curList.size() - 1; i >= idx; i--) {
                            curList.remove(i);
                        }
                        moving = reverseSort(moving);
                        board[ny][nx].horseList.addAll(moving);
//                        board[ny][nx].horseList.addAll(board[y][x].horseList);
//                        board[y][x].horseList.clear();
                        for (Horse h : moving) {
                            h.y = ny;
                            h.x = nx;
                        }
                    }
                    if (board[ny][nx].horseList.size() >= 4) break w;
                    continue;
                }

                //0은 흰색, 1은 빨간색, 2는 파란색이다.
                //흰색인 경우에는 그 칸으로 이동한다. 이동하려는 칸에 말이 이미 있는 경우에는 가장 위에 A번 말을 올려놓는다.
                if (board[ny][nx].color == 0) {
                    List<Horse> curList = board[y][x].horseList;
                    int idx = -1;
                    for (int i = 0; i < curList.size(); i++) {
                        if (curList.get(i).horseNum == horseNum) {
                            idx = i;
                            break;
                        }
                    }
                    List<Horse> moving = new ArrayList<>();
                    for (int i = idx; i < curList.size(); i++) {
                        moving.add(curList.get(i));
                    }
                    for (int i = curList.size() - 1; i >= idx; i--) {
                        curList.remove(i);
                    }
                    board[ny][nx].horseList.addAll(moving);
//                        board[ny][nx].horseList.addAll(board[y][x].horseList);
//                        board[y][x].horseList.clear();
                    for (Horse h : moving) {
                        h.y = ny;
                        h.x = nx;
                    }
                } else if (board[ny][nx].color == 1) {
//                        board[y][x].horseList.sort(Collections.reverseOrder());
                    List<Horse> curList = board[y][x].horseList;
                    int idx = -1;
                    for (int i = 0; i < curList.size(); i++) {
                        if (curList.get(i).horseNum == horseNum) {
                            idx = i;
                            break;
                        }
                    }
                    List<Horse> moving = new ArrayList<>();
                    for (int i = idx; i < curList.size(); i++) {
                        moving.add(curList.get(i));
                    }
                    for (int i = curList.size() - 1; i >= idx; i--) {
                        curList.remove(i);
                    }
                    moving = reverseSort(moving);
                    board[ny][nx].horseList.addAll(moving);
//                        board[ny][nx].horseList.addAll(board[y][x].horseList);
//                        board[y][x].horseList.clear();
                    for (Horse h : moving) {
                        h.y = ny;
                        h.x = nx;
                    }
                } else if (board[ny][nx].color == 2) {
                    //파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다.
                    //방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
                    if (horse.d == 0) {
                        horse.d = 1;
                    } else if (horse.d == 1) {
                        horse.d = 0;
                    } else if (horse.d == 2) {
                        horse.d = 3;
                    } else if (horse.d == 3) {
                        horse.d = 2;
                    }
                    int d = horse.d;
                    ny = y + dy[d];
                    nx = x + dx[d];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
                    if (board[ny][nx].color == 2) {
                        continue;
                    } else if (board[ny][nx].color == 0) {
                        List<Horse> curList = board[y][x].horseList;
                        int idx = -1;
                        for (int i = 0; i < curList.size(); i++) {
                            if (curList.get(i).horseNum == horseNum) {
                                idx = i;
                                break;
                            }
                        }
                        List<Horse> moving = new ArrayList<>();
                        for (int i = idx; i < curList.size(); i++) {
                            moving.add(curList.get(i));
                        }
                        for (int i = curList.size() - 1; i >= idx; i--) {
                            curList.remove(i);
                        }
                        board[ny][nx].horseList.addAll(moving);
//                        board[ny][nx].horseList.addAll(board[y][x].horseList);
//                        board[y][x].horseList.clear();
                        for (Horse h : moving) {
                            h.y = ny;
                            h.x = nx;
                        }
                    } else if (board[ny][nx].color == 1) {
//                        board[y][x].horseList.sort(Collections.reverseOrder());
                        List<Horse> curList = board[y][x].horseList;
                        int idx = -1;
                        for (int i = 0; i < curList.size(); i++) {
                            if (curList.get(i).horseNum == horseNum) {
                                idx = i;
                                break;
                            }
                        }
                        List<Horse> moving = new ArrayList<>();
                        for (int i = idx; i < curList.size(); i++) {
                            moving.add(curList.get(i));
                        }
                        for (int i = curList.size() - 1; i >= idx; i--) {
                            curList.remove(i);
                        }
                        moving = reverseSort(moving);
                        board[ny][nx].horseList.addAll(moving);
//                        board[ny][nx].horseList.addAll(board[y][x].horseList);
//                        board[y][x].horseList.clear();
                        for (Horse h : moving) {
                            h.y = ny;
                            h.x = nx;
                        }
                    }
                }



                if (board[ny][nx].horseList.size() >= 4) {
                    break w;
                }
            }
            turn++;
        }

        System.out.println(turn > 1000 ? "-1" : turn);
    }

    static List<Horse> reverseSort(List<Horse>list) {
        List<Horse> temp = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            temp.add(list.get(i));
        }
        return temp;
    }
}
