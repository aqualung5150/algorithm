import java.util.*;
import java.io.*;

public class Main {

    private static final int MIN = -100000000;

    private static int N, M;
    private static int[][] board;
    private static int[][][] dp; //0:totalMax 1:fromLeftMax 2:fromRightMax

    public static void main(String[] args) throws IOException {
        
        init();

        // start line
        dp[0][1][1] = board[1][1];
        for (int i = 2; i <= M; ++i) {
            dp[0][1][i] = dp[0][1][i - 1] + board[1][i];
            dp[1][1][i] = dp[0][1][i];
        }

        for (int i = 2; i <= N; ++i) {
            for (int j = 1; j <= M; ++j) {
                dp[1][i][j] = Math.max(dp[0][i - 1][j], dp[1][i][j - 1]) + board[i][j];
                dp[0][i][j] = dp[1][i][j];
            }
            for (int j = M; j >= 1; --j) {
                dp[2][i][j] = Math.max(dp[0][i - 1][j], dp[2][i][j + 1]) + board[i][j];
                dp[0][i][j] = Math.max(dp[0][i][j], dp[2][i][j]);
            }
        }

        System.out.print(dp[0][N][M]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];
        dp = new int[3][N + 2][M + 2];
        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < 3; ++k) {
            for (int i = 0; i <= N + 1; ++i) {
                for (int j = 0; j <= M + 1; ++j) {
                    dp[k][i][j] = MIN;
                }
            }
        }

        br.close();
    }
}