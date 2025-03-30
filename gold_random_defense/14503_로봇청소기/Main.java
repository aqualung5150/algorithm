import java.util.*;
import java.io.*;

public class Main {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};

    private static int N, M, x, y, d;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        
        init();

        board[y][x] = 2;
        int answer = 1;
        while (true) {
            boolean isMoved = false;
            for (int i = 0; i < 4; ++i) {
                d = (d + 3) % 4;

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (board[ny][nx] == 0) {
                    board[ny][nx] = 2;
                    ++answer;
                    
                    x = nx;
                    y = ny;
                    isMoved = true;
                    break;
                }
            }

            if (!isMoved) {
                int backward = (d + 2) % 4;
                int nx = x + dx[backward];
                int ny = y + dy[backward];

                if (board[ny][nx] == 1) {
                    System.out.print(answer);
                    return;
                }

                x = nx;
                y = ny;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }
}