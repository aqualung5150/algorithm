import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 1000000000;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N;
    private static int[][] board;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {

        init();
        bfs();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            } 
        }
        dist = new int[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], INF);
        }
        br.close();
    }

    private static void bfs() {

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {

            Pos here = pq.poll();
            int x = here.x;
            int y = here.y;
            int curDist = here.dist;

            if (x == N - 1 && y == N - 1) {
                System.out.print(curDist);
                return;
            }

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                int nextDist = Math.max(Math.abs(board[y][x] - board[ny][nx]), curDist);
                if (nextDist < dist[ny][nx]) {
                    pq.offer(new Pos(nx, ny, nextDist));
                    dist[ny][nx] = nextDist;
                }
            }
        }
    }

    static class Pos implements Comparable<Pos> {

        public int x;
        public int y;
        public int dist;

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos p) {
            return this.dist - p.dist;
        }
    }
}