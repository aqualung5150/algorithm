import java.util.*;
import java.io.*;

public class Main {

    private static String a;
    private static String b;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();
        dp = new int[a.length() + 1][b.length() + 1];

        int answer = 0;
        for (int i = 1; i <= a.length(); ++i) {
            char ca = a.charAt(i - 1);
            for (int j = 1; j <= b.length(); ++j) {
                char cb = b.charAt(j - 1);
                if (ca == cb) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        System.out.print(answer);

        br.close();
    }
}