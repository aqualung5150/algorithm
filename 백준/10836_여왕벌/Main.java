import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int M, N;
    private static int[][] board;

    private static int dx;
    private static int dy;
    private static int x;
    private static int y;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[M][M];
        for (int i = 0; i < M; ++i) {
            Arrays.fill(board[i], 1);
        }

        while (N-- > 0) {

            dx = 0;
            dy = -1;
            x = 0;
            y = M - 1;
            
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            grow(0, zero);
            grow(1, one);
            grow(2, two);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < M; ++j) {
                if (i != 0 && j != 0) {
                    board[i][j] = Math.max(board[i - 1][j], board[i][j - 1]);
                    board[i][j] = Math.max(board[i][j], board[i - 1][j - 1]);
                }
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);

        br.close();
    }

    private static void grow(int amount, int n) {

        while (n-- > 0) {

            board[y][x] += amount;

            if (x == 0 && y == 0) {
                dx = 1;
                dy = 0;
            }

            x += dx;
            y += dy;
        }
    }
}