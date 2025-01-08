import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] P;
    private static int[] S;
    private static int[] cur;

    public static void main(String[] args) throws IOException {

        init();
        solve();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        P = new int[N];
        S = new int[N];
        cur = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; ++i) {
            cur[i] = i;
            P[i] = Integer.parseInt(st1.nextToken());
            S[i] = Integer.parseInt(st2.nextToken());
        }

        br.close();
    }

    private static void solve() {

        int answer = 0;

        boolean invalid = false;
        while (!isFinished()) {

            shuffle();

            if (isInitalState()) {
                invalid = true;
                break;
            }

            ++answer;
        }

        if (invalid) {
            System.out.print("-1");
            return;
        }
        System.out.print(answer);
    }

    private static boolean isFinished() {

        for (int i = 0; i < N; ++i) {
            int player = i % 3;
            int playerIdx = cur[i];
            if (P[playerIdx] != player) {
                return false;
            }
        }

        return true;
    }

    private static void shuffle() {

        int[] result = new int[N];

        for (int from = 0; from < N; ++from) {
            int to = S[from];

            result[to] = cur[from];
        }

        cur = result;
    }

    private static boolean isInitalState() {
        
        for (int i = 0; i < N; ++i) {
            if (cur[i] != i) {
                return false;
            }
        }

        return true;
    }
}