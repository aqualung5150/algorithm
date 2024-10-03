import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    private static int[][] board;
    private static int[][] dist;
    private static int maxDist = 0;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; ++i) {
            String line = br.readLine();
            for (int j = 0; j < M; ++j) {
                char c = line.charAt(j);
                if (c == 'W')
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] == 1)
                    continue;
                bfs(j, i);
            }
        }

        System.out.println(maxDist);
    }

    private static void bfs(int sx, int sy) {
        dist = new int[N][M];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], -1);
        }

        int curMax = 0;

        Queue<Node> q = new LinkedList<Node>();
        q.offer(new Node(sx, sy));
        dist[sy][sx] = 0;

        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            curMax = Math.max(curMax, dist[y][x]);

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || dist[ny][nx] != -1 || board[ny][nx] == 1)
                    continue;
                
                q.offer(new Node(nx, ny));
                dist[ny][nx] = dist[y][x] + 1;
            }
        }
        
        maxDist = Math.max(maxDist, curMax);
    }
}

class Node {
    public int x;
    public int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}