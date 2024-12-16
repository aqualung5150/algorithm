import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 100000;
    private static final int[] dx = {-1, 0, 1};
    private static final int[] dy = {1, 1, 1};

    private static int N, M;
    private static int[][] board;
    private static int[][][] dp; //0 좌하, 1 하, 2 우하

    public static void main(String[] args) throws IOException {
        
        init();

        //init first line
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < 3; ++j) {
                dp[j][0][i] = board[0][i];
            }
        }

        for (int y = 0; y < N - 1; ++y) {
            for (int x = 0; x < M; ++x) {
                
                for (int i = 0; i < 3; ++i) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || nx >= M) {
                        continue;
                    }

                    int nextDist;
                    for (int prevDir = 0; prevDir < 3; ++prevDir) {
                        if (prevDir == i) {
                            continue;
                        }

                        nextDist = dp[prevDir][y][x] + board[ny][nx];
                        dp[i][ny][nx] = Math.min(dp[i][ny][nx], nextDist);
                    }
                }
            }
        }

        //TEST
        // for (int i = 0; i < N; ++i) {
        //     for (int j = 0; j < M; ++j) {
        //         for (int k = 0; k < 3; ++k) {
        //             System.out.print(dp[k][i][j] + ",");
        //         }
        //         System.out.print(" ");
        //     }
        //     System.out.println();
        // }

        int answer = INF;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < 3; ++j) {
                answer = Math.min(answer, dp[j][N - 1][i]);
            }
        }
        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dp = new int[3][N][M];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 3; ++k) {
                    dp[k][i][j] = INF;
                }
            }
        }

        br.close();
    }
}