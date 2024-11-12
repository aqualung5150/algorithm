import java.util.*;
import java.io.*;

public class Main {

    private static int N, M, K;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][M + 1];
        
        //dp
        for (int i = 0; i <= N; ++i) {
            for (int j = 0; j <= M; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (dp[i][j] > 1000000000) {
                    dp[i][j] = 1000000000;
                }
            }
        }

        if (K > dp[N][M]) {
            System.out.print("-1");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (N > 0 && M > 0) {
            long left = dp[N - 1][M];
            if (K <= left) {
                sb.append('a');
                --N;
            } else {
                sb.append('z');
                --M;
                K -= left;
            }
        
        }

        while (N-- > 0) {
            sb.append('a');
        }
        while (M-- > 0) {
            sb.append('z');
        }

        System.out.print(sb);
    
        br.close();
    }
}
