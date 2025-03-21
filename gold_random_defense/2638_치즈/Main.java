import java.util.*;
import java.io.*;

public class Main {

    private static final int dx[] = {1, 0, -1, 0};
    private static final int dy[] = {0, 1, 0, -1};

    private static int N, M;
    private static boolean[][] board;
    private static boolean[][] air;
    private static List<Pos> cheeses = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;
        while (!cheeses.isEmpty()) {
            bfs();
            removeCheese();
            ++answer;
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new boolean[N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                String read = st.nextToken();
                if (read.equals("1")) {
                    board[i][j] = true;
                    cheeses.add(new Pos(j, i));
                }
            }
        }

        br.close();
    }

    private static void bfs() {

        air = new boolean[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Pos here = q.poll();
            int x = here.x;
            int y = here.y;

            air[y][x] = true;

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx]) {
                    continue;
                }

                q.offer(new Pos(nx, ny));
                visited[ny][nx] = true;
            }
        }
    }

    private static void removeCheese() {

        List<Pos> newCheeses = new ArrayList<>();

        for (Pos cheese : cheeses) {

            int x = cheese.x;
            int y = cheese.y;

            int count = 0;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (air[ny][nx]) {
                    ++count;
                }
            }

            if (count < 2) {
                newCheeses.add(cheese);
            } else {
                board[y][x] = false;
            }
        }

        cheeses = newCheeses;
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