import java.util.*;
import java.io.*;

public class Main {

    private static int N, M, H, answer = 100;
    private static boolean[] ladders;

    public static void main(String[] args) throws IOException {

        init();

        dfs(0, 0);

        if (answer == 100) {
            System.out.print("-1");
        } else {
            System.out.print(answer);
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladders = new boolean[N * H];

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            --a;
            --b;
            ladders[a * N + b] = true;
        }

        br.close();
    }

    private static void dfs(int idx, int lastLadderIdx) {

        if (validate()) {
            answer = Math.min(answer, idx);
            return;
        }

        if (idx == 3) {
            return;
        }

        int nextLadderIdx = lastLadderIdx;
        while (true) {

            if (nextLadderIdx >= N * H) {
                break;
            }

            if (nextLadderIdx % N == N - 1) {
                ++nextLadderIdx;
            } else if (ladders[nextLadderIdx]) {
                nextLadderIdx += 2;
            } else if (nextLadderIdx + 1 < N * H && ladders[nextLadderIdx + 1]) {
                nextLadderIdx += 3;
            } else {
                ladders[nextLadderIdx] = true;
                dfs(idx + 1, nextLadderIdx);
                ladders[nextLadderIdx] = false;

                ++nextLadderIdx;
            }
        }
    }

    private static boolean validate() {

        for (int i = 0; i < N; ++i) {
            int x = i;
            for (int j = 0; j < H; ++j) {

                if (ladders[j * N + x]) {
                    ++x;
                } else if (j * N + x > 0 && ladders[j * N + x - 1]) {
                    --x;
                }
            }

            if (x != i) {
                return false;
            }
        }

        return true;
    }
}