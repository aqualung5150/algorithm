import java.util.*;
import java.io.*;

public class Main {
    private static final int dx[] = {1, -1, 0, 0};
    private static final int dy[] = {0, 0, 1, -1};

    private static int N;
    private static char[][] boardRGB;
    private static char[][] boardRB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String read = br.readLine();
        StringTokenizer st = new StringTokenizer(read);
        N = Integer.parseInt(st.nextToken());

        boardRGB = new char[N][N];
        boardRB = new char[N][N];
        for (int i = 0; i < N; ++i) {
            read = br.readLine();
            for (int j = 0; j < N; ++j) {
                char c = read.charAt(j);

                boardRGB[i][j] = c;
                boardRB[i][j] = c;

                if (c == 'G')
                    boardRB[i][j] = 'R';
            }
        }

        System.out.print(getAnswer(boardRGB) + " " + getAnswer(boardRB));
    }

    static int getAnswer(char[][] board) {
        boolean[][] visited = new boolean[N][N];

        int count = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (visited[i][j])
                    continue;
                
                bfs(board, visited, j, i);
                count++;
            }
        }

        return count;
    }

    static void bfs(char[][] board, boolean[][] visited, int sx, int sy) {
        char target = board[sy][sx];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(sx, sy));
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            Point here = q.poll();
            int x = here.x;
            int y = here.y;

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] != target)
                    continue;

                q.offer(new Point(nx, ny));
                visited[ny][nx] = true;
            }
        }
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}