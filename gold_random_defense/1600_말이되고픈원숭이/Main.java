import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 40000;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int K, W, H;
    private static boolean[][] board;
    private static int[][][] dist;

    public static void main(String[] args) throws IOException {

        init();
        bfs();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new boolean[H][W];
        for (int i = 0; i < H; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; ++j) {
                if (st.nextToken().equals("1")) {
                    board[i][j] = true;
                }
            }
        }

        dist = new int[K + 1][H][W];
        for (int i = 0; i <= K; ++i) {
            for (int j = 0; j < H; ++j) {
                Arrays.fill(dist[i][j], INF);
            }
        }
    }

    private static void bfs() {

        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0, 0, 0));
        dist[0][0][0] = 0;

        while (!q.isEmpty()) {
            Pos here = q.poll();
            int x = here.x;
            int y = here.y;
            int k = here.k;
            int curDist = dist[k][y][x];

            if (x == W - 1 && y == H - 1) {
                System.out.print(curDist);
                return;
            }

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!(nx < 0 || nx >= W || ny < 0 || ny >= H || board[ny][nx] || dist[k][ny][nx] != INF)) {
                    q.offer(new Pos(nx, ny, k));
                    dist[k][ny][nx] = curDist + 1;
                }

                nx = x + dx[i] * 2;
                ny = y + dy[i] * 2;

                int lnx = nx + dx[(i + 1) % 4];
                int lny = ny + dy[(i + 1) % 4];
                if (!(k >= K || lnx < 0 || lnx >= W || lny < 0 || lny >= H || board[lny][lnx] || dist[k + 1][lny][lnx] != INF)) {
                    q.offer(new Pos(lnx, lny, k + 1));
                    dist[k + 1][lny][lnx] = curDist + 1;
                }

                int rnx = nx + dx[(i + 3) % 4];
                int rny = ny + dy[(i + 3) % 4];
                if (!(k >= K || rnx < 0 || rnx >= W || rny < 0 || rny >= H || board[rny][rnx] || dist[k + 1][rny][rnx] != INF)) {
                    q.offer(new Pos(rnx, rny, k + 1));
                    dist[k + 1][rny][rnx] = curDist + 1;
                }
            }
        }

        System.out.print("-1");
    }

    static class Pos {
        public int x;
        public int y;
        public int k;

        public Pos(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}