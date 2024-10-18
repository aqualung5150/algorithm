import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[][] board;
    private static int[][][] dp; //0: 오른쪽, 1: 대각선, 2: 아래

    public static void main(String[] args) throws IOException {
        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        dp = new int[3][N + 1][N + 1];
        for (int i = 1; i <= N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //solution
        dp[0][1][2] = 1;
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= N; ++j) {

                if (board[i][j] == 1)
                    continue;

                //오른쪽
                dp[0][i][j] += dp[1][i][j - 1] + dp[0][i][j - 1];
                //대각선
                if (board[i - 1][j] != 1 && board[i][j - 1] != 1) {
                    dp[1][i][j] += dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
                }
                //아래
                dp[2][i][j] += dp[1][i - 1][j] + dp[2][i - 1][j];
            }
        }

        System.out.print(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
    }
}