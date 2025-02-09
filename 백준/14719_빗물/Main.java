import java.util.*;
import java.io.*;

public class Main {

    private static int H, W;
    private static int[] arr;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        while (left < W - 1) {

            int right = left + 1;
            int rightHigh = arr[right];
            int rightHighIdx = right;
            while (right < W) {
                if (arr[right] > rightHigh) {
                    rightHighIdx = right;
                    rightHigh = arr[right];
                }

                if (rightHigh >= arr[left]) {
                    break;
                }

                ++right;
            }

            fillWater(left, rightHighIdx);

            left = rightHighIdx;
        }

        System.out.print(answer);

        br.close();
    }

    private static void fillWater(int left, int right) {

        int height = Math.min(arr[left], arr[right]);

        while (left <= right) {

            if (height >= arr[left]) {
                answer += height - arr[left];
            }

            ++left;
        }
    }
}