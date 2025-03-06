import java.util.*;
import java.io.*;

public class Main {

    private static final int dx[] = {1, 0, -1, 0};
    private static final int dy[] = {0, 1, 0, -1};

    private static int N, L, R;
    private static int[][] board;
    private static boolean[][] visited;
    private static boolean isMoved;

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;
        while (true) {

            isMoved = false;
            for (int i = 0; i < N; ++i) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (!visited[i][j]) {
                        bfs(j, i);
                    }
                }
            }

            if (!isMoved) {
                break;
            }

            ++answer;
        }


        System.out.print(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }

    private static void bfs(int sx, int sy) {

        Queue<Pos> q = new LinkedList<>();
        Stack<Pos> targets = new Stack<>();
        q.offer(new Pos(sx, sy));
        visited[sy][sx] = true;

        int count = 0;
        int totalCost = 0;
        while (!q.isEmpty()) {
            
            Pos here = q.poll();
            int x = here.x;
            int y = here.y;
            int cost = board[y][x];

            ++count;
            totalCost += cost;
            targets.push(here);

            for (int i = 0; i < 4; ++i) {
                
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx]) {
                    continue;
                }
                
                int diff = Math.abs(cost - board[ny][nx]);
                if (diff < L || diff > R) {
                    continue;
                }

                visited[ny][nx] = true;
                q.offer(new Pos(nx, ny));
            }
        }

        int updatedCost = totalCost / count;
        while (!targets.isEmpty()) {
            Pos here = targets.pop();
            board[here.y][here.x] = updatedCost;
        }

        if (count > 1) {
            isMoved = true;
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