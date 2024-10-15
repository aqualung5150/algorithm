import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < N; ++i) {
            int cur = arr[i];

            int left = 0;
            int right = N - 1;
            while (left < right) {
                int sum = arr[left] + arr[right];

                if (sum == cur) {
                    if (left == i) {
                        ++left;
                    } else if (right == i) {
                        --right;
                    } else {
                        ++ans;
                        break;
                    }
                } else if (sum < cur) {
                    ++left;
                } else {
                    --right;
                }
            }
        }

        System.out.print(ans);
    }
}