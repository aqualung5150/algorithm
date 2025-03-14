import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[][] dp; //0: 끝에 세로가 있는 경우, 1: 없는 경우우

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[2][31];
        dp[0][2] = 2;
        dp[1][2] = 1;

        for (int i = 4; i <= N; i += 2) {
            int prev = dp[0][i - 2] + dp[1][i - 2]; 
            dp[0][i] = prev * 2 + dp[0][i - 2];
            dp[1][i] = prev;
        }

        System.out.print(dp[0][N] + dp[1][N]);

        br.close();
    }
}