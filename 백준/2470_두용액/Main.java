import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        init();

        int minAbs = 2000000000;
        int[] answer = new int[2];
        Arrays.sort(arr);
        int left = 0;
        int right = N - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            int abs = Math.abs(sum);
            if (abs < minAbs) {
                answer[0] = arr[left];
                answer[1] = arr[right];
                minAbs = abs;
            }

            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                break;
            }
        }

        System.out.print(answer[0] + " " + answer[1]);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }
}