import java.io.*;
import java.util.*;

public class Main {

    private static final int INF = 125 * 125 * 9 + 1;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int T = 1;
    private static int N;
    private static int[][] board;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {

            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            board = new int[N][N];
            dist = new int[N][N];
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; ++j) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            System.out.print("Problem " + T + ": ");
            bfs();
            ++T;
        }

        br.close();
    }

    private static void bfs() {

        PriorityQueue<Pos> pq = new PriorityQueue<>();

        pq.offer(new Pos(0, 0, board[0][0]));
        dist[0][0] = board[0][0];

        while (!pq.isEmpty()) {

            Pos here = pq.poll();
            int x = here.x;
            int y = here.y;
            int curDist = here.curDist;

            if (x == N - 1 && y == N - 1) {
                System.out.println(curDist);
                return;
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
    }

    public static class Pos implements Comparable<Pos> {
        public int x;
        public int y;
        public int curDist;

        public Pos(int x, int y, int curDist) {
            this.x = x;
            this.y = y;
            this.curDist = curDist;
        }

        @Override
        public int compareTo(Pos o) {
            return this.curDist - o.curDist;
        }
    }
}