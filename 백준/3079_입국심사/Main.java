import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static long[] cost;

    public static void main(String[] args) throws IOException {

        init();

        // binary search
        long left = 0;
        long right = cost[0] * M;

        while (left < right) {

            long mid = (left + right) / 2;

            long count = 0;
            for (long c : cost) {
                count += mid / c;

                /*
                최악의 경우 
                N = 1e5
                M = 1e9
                cost[0] = 1
                일 때

                mid = (0 + 1e9 * 1e9) / 2 이므로
                count가 5e17이 N번 더해져 오버프로우가 날 수 있다.
                */
                if (count > M) {
                    break;
                }
            }

            if (count < M) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.print(right);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new long[N];
        for (int i = 0; i < N; ++i) {
            cost[i] = Long.parseLong(br.readLine());
        }
        br.close();
    }
}