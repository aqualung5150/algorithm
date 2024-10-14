import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static Matrix[] matrix;
    private static int[][] dp;
    private static Matrix[][] state;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new Matrix[N];
        dp = new int[N][N];
        state = new Matrix[N][N];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[i] = new Matrix(a, b);
        }

        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N - k; ++i) {
                if (k == 0) {
                    dp[i][i] = 0;
                    state[i][i] = matrix[i];
                    continue;
                }
                for (int j = i; j < i + k; ++j) {
                    int cur = dp[i][j] + dp[j + 1][i + k] + (state[i][j].a * state[j + 1][i + k].a * state[j + 1][i + k].b);
                    if (dp[i][i + k] > cur) {
                        dp[i][i + k] = cur;
                        state[i][i + k] = new Matrix(state[i][j].a, state[j + 1][i + k].b);
                    }
                }
            }
        }

        System.out.print(dp[0][N - 1]);

        br.close();
    }

    static class Matrix {
        public int a;
        public int b;

        public Matrix(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}