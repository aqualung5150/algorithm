import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N, M, K;
    private static int[][] board;
    private static int[][] brokenCnt;

    public static void main(String[] args) throws IOException {

        init();
        bfs();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        brokenCnt = new int[N][M];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(brokenCnt[i], 11);
        }

        for (int i = 0; i < N; ++i) {
            String read = br.readLine();
            for (int j = 0; j < M; ++j) {
                board[i][j] = read.charAt(j) - '0';
            }
        }

        br.close();
    }

    private static void bfs() {

        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(0, 0, 1));
        brokenCnt[0][0] = 0;

        while (!q.isEmpty()) {
            Position here = q.poll();
            int x = here.x;
            int y = here.y;
            int curDist = here.dist;
            int broken = brokenCnt[y][x];

            if (x == M - 1 && y == N - 1) {
                System.out.print(curDist);
                return;
            }

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                    continue;
                }

                int nextBroken = broken;
                if(board[ny][nx] == 1) {
                    ++nextBroken;
                }

                if (nextBroken > K || nextBroken >= brokenCnt[ny][nx]) {
                    continue;
                }

                q.offer(new Position(nx, ny, curDist + 1));
                brokenCnt[ny][nx] = nextBroken;
            }
        }

        System.out.print("-1");
    }

    static class Position {
        public int x;
        public int y;
        public int dist;

        public Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}