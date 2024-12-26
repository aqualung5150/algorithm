import java.util.*;
import java.io.*;

public class Main {

    private static final int dx[] = {1, 0, -1, 0};
    private static final int dy[] = {0, 1, 0, -1};

    private static int N, M;
    private static char[][] board;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        init();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (visited[i][j]) {
                    continue;
                }

                if (bfs(j, i)) {
                    System.out.print("Yes");
                    return;
                }
            }
        }

        System.out.print("No");
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][];
        visited = new boolean[N][M];

        for (int i = 0; i < N; ++i) {
            String read = br.readLine();
            board[i] = read.toCharArray();
        }

        br.close();
    }

    private static boolean bfs(int sx, int sy) {

        char target = board[sy][sx];

        Queue<Dot> q = new LinkedList<>();
        q.offer(new Dot(sx, sy, -1));
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            Dot here = q.poll();

            for (int i = 0; i < 4; ++i) {

                if (here.blockDir == i) {
                    continue;
                }

                int nx = here.x + dx[i];
                int ny = here.y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] != target) {
                    continue;
                }

                if (visited[ny][nx]) {
                    return true;
                }

                visited[ny][nx] = true;
                q.offer(new Dot(nx, ny, (i + 2) % 4));
            }
        }

        return false;
    }

    static class Dot {

        int x;
        int y;
        int blockDir;

        public Dot(int x, int y, int blockDir) {
            this.x = x;
            this.y = y;
            this.blockDir = blockDir;
        }
    }
}