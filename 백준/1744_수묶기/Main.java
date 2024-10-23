import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int answer = 0;

        int left = 1;
        while (left <= N - 1 && arr[left] <= 0) {
            answer += arr[left - 1] * arr[left];
            left += 2;
        }

        int right = N - 2;
        while (right >= 0 && arr[right] > 1) {
            answer += arr[right] * arr[right + 1];
            right -= 2;
        }

        --left;
        ++right;
        while (left <= right) {
            answer += arr[left];
            ++left;
        }

        System.out.print(answer);
    }
}