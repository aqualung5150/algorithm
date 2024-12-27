import java.util.*;
import java.io.*;

public class Main {

    private static long N, P, Q, X, Y;
    private static Map<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());
        br.close();

        dp.put(0L, 1L);
        dp(N);

        System.out.print(dp.get(N));
    }

    private static long dp(long n) {
        
        if (n < 0L) {
            return 1L;
        }

        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        dp.put(n, dp(n/P - X) + dp(n/Q - Y));

        return dp.get(n);
    }
}