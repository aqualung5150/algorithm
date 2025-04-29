import java.util.*;
import java.io.*;

public class Main {
    
    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int INF = 1000000000;

    private static int N, sizeOfShark = 2, fed = 0, answer = 0;
    private static Pos cur;
    private static int[][] board;

    public static void main(String[] args) throws IOException {

        init();

        while (bfs()) {
            if (fed == sizeOfShark) {
                fed = 0;
                ++sizeOfShark;
            }
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                int n = Integer.parseInt(st.nextToken());

                if (n == 9) {
                    cur = new Pos(j, i);
                } else {
                    board[i][j] = n;
                }
            }
        }

        br.close();
    }

    private static boolean bfs() {

        int dist[][] = new int[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], -1);
        }

        Queue<Pos> q = new ArrayDeque<>();
        q.offer(cur);
        dist[cur.y][cur.x] = 0;

        Pos target = new Pos(N, N);
        int targetDist = INF;
        while (!q.isEmpty()) {
            Pos here = q.poll();
            int x = here.x;
            int y = here.y;
            int curDist = dist[y][x];

            if (curDist > targetDist) {
                break;
            }

            if (board[y][x] != 0 && board[y][x] < sizeOfShark && curDist <= targetDist) {
                if (y < target.y || (y == target.y && x < target.x)) {
                    targetDist = curDist;
                    target = new Pos(x, y);
                }
            }

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || dist[ny][nx] != -1 || board[ny][nx] > sizeOfShark) {
                    continue;
                }

                q.offer(new Pos(nx, ny));
                dist[ny][nx] = curDist + 1;
            }
        }

        if (targetDist == INF)
            return false;
        else {
            ++fed;
            answer += targetDist;
            cur = target;
            board[target.y][target.x] = 0;
            return true;
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