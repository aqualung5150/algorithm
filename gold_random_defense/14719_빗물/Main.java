import java.util.*;
import java.io.*;

public class Main {

    private static int W, answer = 0;
    private static int[] heights;

    public static void main(String[] args) throws IOException {

        init();

        int left = 0;
        while (true) {
            int right = getRightWall(left);
            if (right >= W) {
                break;
            }
            addWater(left, right);
            left = right;
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        W = Integer.parseInt(st.nextToken());
        heights = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; ++i) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }

    private static int getRightWall(int left) {

        int right = left + 1;

        if (right >= W) {
            return W;
        }

        int max = heights[right];
        int maxIdx = right;
        while (right < W) {

            if (heights[right] >= heights[left]) {
                return right;
            }

            if (heights[right] >= max) {
                max = heights[right];
                maxIdx = right;
            }

            ++right;
        }

        return maxIdx;
    }

    private static void addWater(int left, int right) {
        int limit = Math.min(heights[left], heights[right]);

        for (int i = left + 1; i < right; ++i) {
            answer += limit - heights[i];
        }
    }
}