import java.util.*;
import java.io.*;

public class Main {

    private static final int dx[] = {1, 0, -1, 0};
    private static final int dy[] = {0, 1, 0, -1};

    private static int R, C;
    private static char[][] board;
    private static Pos start;
    private static List<Coord> waters = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();

        int answer = bfs();
        if (answer == -1) {
            System.out.print("KAKTUS");
        } else {
            System.out.print(answer);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; ++i) {
            String read = br.readLine();
            for (int j = 0; j < C; ++j) {
                board[i][j] = read.charAt(j);
                if (board[i][j] == 'S') {
                    start = new Pos(j, i, 0);
                    board[i][j] = '.';
                } else if (board[i][j] == '*') {
                    waters.add(new Coord(j, i));
                }
            }
        }

        br.close();
    }

    private static int bfs() {

        boolean[][] visited = new boolean[R][C];
        Queue<Pos> q = new LinkedList<>();
        q.offer(start);
        visited[start.y][start.x] = true;

        int depth = 0;
        while (!q.isEmpty()) {
            Pos here = q.poll();
            int x = here.x;
            int y = here.y;
            int curDist = here.dist;

            if (curDist > depth) {
                water();
                ++depth;
            }

            if (board[y][x] == '*') {
                continue;
            }

            for (int i = 0; i < 4; ++i) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || board[ny][nx] == '*' || board[ny][nx] == 'X') {
                    continue;
                }

                if (board[ny][nx] == 'D') {
                    return curDist + 1;
                }

                q.offer(new Pos(nx, ny, curDist + 1));
                visited[ny][nx] = true;
            }
        }

        return -1;
    }

    private static void water() {

        List<Coord> newWaters = new ArrayList<>();

        for (Coord w : waters) {
            int x = w.x;
            int y = w.y;

            for (int d = 0; d < 4; ++d) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= C || ny < 0 || ny >= R || board[ny][nx] != '.') {
                    continue;
                }

                board[ny][nx] = '*';
                newWaters.add(new Coord(nx, ny));
            }
        }

        waters = newWaters;
    }

    static class Coord {
        public int x;
        public int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Pos {
        public int x;
        public int y;
        public int dist;

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}