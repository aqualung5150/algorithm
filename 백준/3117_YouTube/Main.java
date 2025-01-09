import java.util.*;
import java.io.*;

public class Main {

    private static int N, K, M;
    private static int[][] sparseTable;
    private static int[] start;

    public static void main(String[] args) throws IOException {

        init();

        for (int i = 2; i <= 30; ++i) {
            for (int j = 1; j <= K; ++j) {
                sparseTable[i][j] = sparseTable[i - 1][sparseTable[i - 1][j]];
            }
        }

        StringBuilder sb = new StringBuilder();

        --M;
        for (int j : start) {
            for (int i = 0; i < 30; ++i) {
                if ((M & (1 << i)) == 1 << i) {
                    j = sparseTable[i + 1][j];
                }
            }
            sb.append(j + " ");
        }
        System.out.print(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sparseTable = new int[31][K + 1];
        start = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            start[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; ++i) {
            sparseTable[1][i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }
}