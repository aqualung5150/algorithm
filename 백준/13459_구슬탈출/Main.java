import java.util.*;
import java.io.*;

public class Main {

    private static final int dx[] = {1, -1, 0, 0};
    private static final int dy[] = {0, 0, 1, -1};
    
    private static int N, M;
    private static int[][] board;
    private static Point R;
    private static Point B;
    private static Point O;
    private static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {

        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M][N][M];
        for (int i = 0; i < N; ++i) {
            String read = br.readLine();
            for (int j = 0; j < M; ++j) {
                char c = read.charAt(j);
                if (c == '#') {
                    board[i][j] = 1;
                }
                if (c == 'O') {
                    board[i][j] = 2;
                    O = new Point(j, i);
                }
                if (c == 'R') {
                    R = new Point(j, i);
                }
                if (c == 'B') {
                    B = new Point(j, i);
                }
            }
        }

        visited[R.y][R.x][B.y][B.x] = true;

        //dfs
        if (dfs(0, R, B)) {
            System.out.print(1);
            return;
        }
        System.out.print(0);
    }

    private static boolean dfs(int idx, Point red, Point blue) {

        if (idx == 10) {
            return false;
        }

        for (int d = 0; d < 4; ++d) {
            Point nextRed = updatePos(red, d);
            Point nextBlue = updatePos(blue, d);

            if (nextBlue.equals(O)) {
                continue;
            }

            if (nextRed.equals(O)) {
                return true;
            }

            if (nextRed.equals(nextBlue)) {
                collision(nextRed, nextBlue, red, blue, d);
            }

            if (visited[nextRed.y][nextRed.x][nextBlue.y][nextBlue.x]) {
                continue;
            }

            visited[nextRed.y][nextRed.x][nextBlue.y][nextBlue.x] = true;
            if (dfs(idx + 1, nextRed, nextBlue)) {
                return true;
            }
           visited[nextRed.y][nextRed.x][nextBlue.y][nextBlue.x] = false;
        }

        return false;
    }

    // 2(dest) 또는 1(벽) 와 만난다면 리턴
    private static Point updatePos(Point p, int direction) {
        Point np = new Point(p.x, p.y);

        while (true) {
            int nx = np.x + dx[direction];
            int ny = np.y + dy[direction];

            if (board[ny][nx] == 1) {
                break;
            }

            np.x = nx;
            np.y = ny;

            if (np.equals(O)) {
                break;
            }
        }

        return np;
    }

    private static void collision(Point nextRed, Point nextBlue, Point prevRed, Point prevBlue, int direction) {
        if (direction == 0) {
            if (prevRed.x < prevBlue.x) {
                nextRed.x--;
            } else {
                nextBlue.x--;
            }
        }
        if (direction == 1) {
            if (prevRed.x < prevBlue.x) {
                nextBlue.x++;
            } else {
                nextRed.x++;
            }
        }
        if (direction == 2) {
            if (prevRed.y < prevBlue.y) {
                nextRed.y--;
            } else {
                nextBlue.y--;
            }
        }
        if (direction == 3) {
            if (prevRed.y < prevBlue.y) {
                nextBlue.y++;
            } else {
                nextRed.y++;
            }
        }
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point p = (Point) o;
                return (p.x == x && p.y == y);
            }
            return false;
        }
    }
}