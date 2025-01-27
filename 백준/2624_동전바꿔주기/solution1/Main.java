import java.util.*;
import java.io.*;

public class Main {

    private static int T, k;
    private static List<Coin> coins = new ArrayList<>();
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        init();

        dp[0] = 1;
        for (Coin coin : coins) {

            int p = coin.p;
            int n = coin.n;

            int[] temp = new int[T + 1];
            for (int i = 0; i <= T - p; ++i) {

                for (int j = 1; j <= n; ++j) {
                    int cur = p * j;
                    if (i + cur <= T) {
                        temp[i + cur] += dp[i];
                    }
                }
            }

            for (int i = 1; i <= T; ++i) {
                dp[i] += temp[i];
            }
        }

        System.out.print(dp[T]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            coins.add(new Coin(p, n));
        }

        dp = new int[T + 1];

        br.close();
    }

    static class Coin {

        int p;
        int n;

        public Coin(int p, int n) {
            this.p = p;
            this.n = n;
        }
    }
}