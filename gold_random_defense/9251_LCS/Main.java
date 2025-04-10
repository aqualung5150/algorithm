import java.util.*;
import java.io.*;

public class Main {

    private static String A, B;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = br.readLine();
        B = br.readLine();

        int sizeA = A.length();
        int sizeB = B.length();
        dp = new int[sizeA + 1][sizeB + 1];
        for (int i = 1; i <= sizeA; ++i) {
            for (int j = 1; j <= sizeB; ++j) {
                char a = A.charAt(i - 1);
                char b = B.charAt(j - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.print(dp[sizeA][sizeB]);

        br.close();
    }
}