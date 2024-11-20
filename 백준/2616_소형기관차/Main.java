import java.util.*;
import java.io.*;

public class Main {

    private static int N, K;
    private static int[] arr;
    private static int[] dp1;
    private static int[][] dp2;

    public static void main(String[] args) throws IOException {

        init();

        dp1();

        dp2[0][0] = dp1[0];
        for (int i = 1; i < N; ++i) {
            dp2[0][i] = Math.max(dp1[i], dp2[0][i - 1]);
        }

        for (int k = 1; k < 3; ++k) {
            for (int i = 0; i < N; ++i) {
                if (i < K) {
                    dp2[k][i] = dp2[k - 1][i];
                } else {
                    dp2[k][i] = Math.max(dp2[k - 1][i - K] + dp1[i], dp2[k][i - 1]); 
                }
            }
        }
        
        System.out.print(dp2[2][N - 1]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp1 = new int[N];
        dp2 = new int[3][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        K = Integer.parseInt(br.readLine());
        br.close();
    }

    private static void dp1() {

        dp1[0] = arr[0];

        for (int i = 1; i < N; ++i) {
            dp1[i] = dp1[i - 1] + arr[i];

            if (i >= K) {
                dp1[i] -= arr[i - K];
            }
        }
    }
}