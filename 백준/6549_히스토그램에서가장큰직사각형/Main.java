import java.util.*;
import java.io.*;

public class Main {

    private static int[] h;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            String readLine = br.readLine();

            if (readLine.equals("0")) {
                break;
            }

            st = new StringTokenizer(readLine);

            int n = Integer.parseInt(st.nextToken());
            h = new int[n];
            for (int i = 0; i < n; ++i) {
                h[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(getMax(0, n - 1));
        }

        br.close();
    }

    private static long getMax(int left, int right) {

        if (left == right) {
            return h[left];
        }

        int mid = (left + right) / 2;

        long leftMax = getMax(left, mid);
        long rightMax = getMax(mid + 1, right);

        long max = Math.max(leftMax, rightMax);

        return Math.max(max, getMaxFromMid(mid, left, right));
    }

    private static long getMaxFromMid(int mid, int lo, int hi) {

        long result = h[mid];
        long minH = h[mid];
        int left = mid - 1;
        int right = mid + 1;

        while (left >= lo && right <= hi) {

            if (h[left] > h[right]) {
                minH = Math.min(minH, h[left]);
                result = Math.max(result, minH * (right - left));
                --left;
            } else {
                minH = Math.min(minH, h[right]);
                result = Math.max(result, minH * (right - left));
                ++right;
            }
        }

        while (left >= lo) {
            minH = Math.min(minH, h[left]);
            result = Math.max(result, minH * (right - left));
            --left;
        }

        while (right <= hi) {
            minH = Math.min(minH, h[right]);
            result = Math.max(result, minH * (right - left));
            ++right;
        }

        return result;
    }
}