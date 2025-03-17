import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 1000000000;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int N;
    private static int[][] board;

    public static void main(String[] args) throws IOException {

        int T = 1;
        while (init()) {
            sb.append("Problem ")
                .append(T)
                .append(": ")
                .append(bfs())
                .append("\n");
            ++T;
        }

        System.out.print(sb);
        
        br.close();
    }

    private static boolean init() throws IOException {

        N = Integer.parseInt(br.readLine());

        if (N == 0) {
            return false;
        }

        board = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        return true;
    }

    private static int bfs() {

        int[][] dist = new int[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(0, 0, board[0][0]));
        dist[0][0] = board[0][0];

        while (!pq.isEmpty()) {
            Pos here = pq.poll();
            int x = here.x;
            int y = here.y;
            int curDist = here.dist;

            if (x == N - 1 && y == N - 1) {
                return curDist;
            }

            if (curDist > dist[y][x]) {
                continue;
            }

            for (int i = 0; i < 4; ++i) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                int nextDist = curDist + board[ny][nx];
                if (nextDist >= dist[ny][nx]) {
                    continue;
                }

                pq.offer(new Pos(nx, ny, nextDist));
                dist[ny][nx] = nextDist;
            }
        }

        return -1;
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