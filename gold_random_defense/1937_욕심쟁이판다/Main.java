import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N;
    private static int[][] board;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                answer = Math.max(answer, dfs(j, i));
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
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dist = new int[N][N];

        br.close();
    }

    private static int dfs(int x, int y) {

        if (dist[y][x] != 0) {
            return dist[y][x];
        } 

        int curMax = 1;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] <= board[y][x]) {
                continue;
            }

            curMax = Math.max(curMax, dfs(nx, ny) + 1);
        }

        return dist[y][x] = curMax;
    }
}