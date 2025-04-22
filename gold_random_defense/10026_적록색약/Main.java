import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static int N, answer1 = 0, answer2 = 0;
    private static char[][] board;
    private static boolean[][] visited1, visited2;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (!visited1[i][j]) {
                    bfs1(j, i);
                    ++answer1;
                }

                if (!visited2[i][j]) {
                    bfs2(j, i);
                    ++answer2;
                }
            }            
        }

        System.out.print(answer1 + " " + answer2);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][];
        for (int i = 0; i < N; ++i) {
            board[i] = br.readLine().toCharArray();
        }

        visited1 = new boolean[N][N];
        visited2 = new boolean[N][N];

        br.close();
    }

    private static void bfs1(int sx, int sy) {

        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(sx, sy));
        visited1[sy][sx] = true;

        while (!q.isEmpty()) {
            Pos here = q.poll();
            int x = here.x;
            int y = here.y;
            char cur = board[y][x];

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited1[ny][nx] || board[ny][nx] != cur) {
                    continue;
                }

                q.offer(new Pos(nx, ny));
                visited1[ny][nx] = true;
            }
        }
    }

    private static void bfs2(int sx, int sy) {

        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(sx, sy));
        visited2[sy][sx] = true;

        while (!q.isEmpty()) {
            Pos here = q.poll();
            int x = here.x;
            int y = here.y;
            char cur = board[y][x];

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited2[ny][nx]) {
                    continue;
                }

                char next = board[ny][nx];
                if (next != cur) {
                    if (!('R' + 'G' == next + cur)) {
                        continue;
                    }
                }

                q.offer(new Pos(nx, ny));
                visited2[ny][nx] = true;
            }
        }
    }

    static class Pos {
        public int x;
        public int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}