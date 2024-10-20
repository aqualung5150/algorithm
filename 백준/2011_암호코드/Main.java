import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 1000000;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int size = str.length();

        arr = new int[size + 1];
        dp = new int[2][size + 1];


        for (int i = 0; i < size; ++i) {
            char c = str.charAt(i);
            arr[i + 1] = c - '0';
        }

        dp[0][0] = 1;
        for (int i = 1; i <= size; ++i) {
            if (arr[i] == 0) {
                //해석불가
                if (arr[i - 1] == 0 || arr[i - 1] > 2) {
                    System.out.print(0);
                    return;
                } else {
                    dp[0][i] = 0;
                    dp[1][i] = dp[0][i - 1];
                }
            } else if (arr[i - 1] == 0 || (arr[i - 1] * 10 + arr[i]) > 26) {
                //변형불가
                dp[0][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD;
                dp[1][i] = 0;
            } else {
                //변형가능
                dp[0][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD;
                dp[1][i] = dp[0][i - 1];
            }
        }

        System.out.print((dp[0][size] + dp[1][size]) % MOD);
    }
}