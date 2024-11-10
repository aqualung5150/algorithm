import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        int answer = 0;
        int left = 1;
        int right = k;
        while (left <= right) {
            int mid = (left + right) / 2;

            int smallerDigitCount = getSmallerDigitCount(mid);

            if (smallerDigitCount >= k) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.print(answer);
    }

    private static int getSmallerDigitCount(int mid) {
        int sum = 0;

        for (int i = 1; i <= N; ++i) {
            sum += Math.min(mid / i, N);
        }

        return sum;
    }
}