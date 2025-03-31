import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N, M, empty = 0, answer = 0;
    private static int[][] board;
    private static List<Pos> viruses = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, new Pos(0, 0));
        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    viruses.add(new Pos(j, i));
                } else if (board[i][j] == 0) {
                    ++empty;
                }
            }
        }

        br.close();
    }

    private static void dfs(int idx, Pos start) {

        if (idx == 3) {
            answer = Math.max(answer, getSafetyZone());            
            return;
        }

        int y = start.y;
        int x = start.x;
        while (y < N) {
            while (x < M) {
                if (board[y][x] == 0) {
                    board[y][x] = 1;
                    dfs(idx + 1, new Pos(x + 1, y));
                    board[y][x] = 0;
                }
                ++x;
            }

            if (x >= M) {
                ++y;
                x = 0;
            }
        }
    }

    private static int getSafetyZone() {

        int count = empty - 3;
        boolean[][] visited = new boolean[N][M];
        Queue<Pos> q = new ArrayDeque<>();
        for (Pos virus : viruses) {
            q.offer(virus);
            visited[virus.y][virus.x] = true;
        }

        while (!q.isEmpty()) {
            Pos here = q.poll();
            int x = here.x;
            int y = here.y;

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] != 0) {
                    continue;
                }

                q.offer(new Pos(nx, ny));
                visited[ny][nx] = true;
                --count;
            }
        }

        return count;
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