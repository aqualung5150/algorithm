import java.util.*;
import java.io.*;

class Main {

    private static int N;
    private static int M;
    private static int[][] board;
    private static List<Glacier> glaciers = new ArrayList<>();
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; ++i) {
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int j = 0; j < M; ++j) {
                int amount = Integer.parseInt(st.nextToken());
                if (amount != 0)
                    glaciers.add(new Glacier(j, i, amount));
                board[i][j] = amount;
                
            }
        }

        int ans = 0;
        while (true) {
            melt();
            ++ans;

            int numberOfGlacier = getNumberOfGlacier();

            if (numberOfGlacier > 1) {
                System.out.print(ans);
                break;
            }

            if (numberOfGlacier == 0) {
                System.out.print(0);
                break;
            }
        }
    }

    private static void melt() {

        int[] meltedAmount = new int[glaciers.size()];

        for (int i = 0; i < glaciers.size(); ++i) {
            Glacier glacier = glaciers.get(i);

            if (glacier.amount <= 0)
                continue;

            meltedAmount[i] = getMeltedAmount(glacier.x, glacier.y);
        }

        for (int i = 0; i < glaciers.size(); ++i) {
            Glacier glacier = glaciers.get(i);
            glacier.amount -= meltedAmount[i];
            
            board[glacier.y][glacier.x] -= meltedAmount[i];
            board[glacier.y][glacier.x] = Math.max(board[glacier.y][glacier.x], 0);
        }
    }

    private static int getMeltedAmount(int x, int y) {
        
        int result = 0;

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] != 0)
                continue;

            ++result;
        }

        return result;
    }

    private static int getNumberOfGlacier() {

        int numberOfGlacier = 0;
        boolean[][] visited = new boolean[N][M];

        for (Glacier g : glaciers) {
            if (board[g.y][g.x] == 0 || visited[g.y][g.x])
                continue;
            
            ++numberOfGlacier;
            if (numberOfGlacier > 1)
                return numberOfGlacier;
            
            bfs(visited, g.x, g.y);
        }

        return numberOfGlacier;
    }

    private static void bfs(boolean[][] visited, int sx, int sy) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(sx, sy));
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            Point here = q.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = here.x + dx[i];
                int ny = here.y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] == 0)
                    continue;
                
                visited[ny][nx] = true;
                q.offer(new Point(nx, ny));
            }
        }
    }

    static class Glacier {
        public int x;
        public int y;
        public int amount;

        public Glacier(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}