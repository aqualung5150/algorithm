import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M;
    private static int[][] tree; //tree[0]:최솟값, tree[1]:최댓값값
    private static int leafStart = 1;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        initTree();

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            query(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );
        }
    }

    private static void initTree() throws IOException {

        while (leafStart < N) {
            leafStart *= 2;
        }

        tree = new int[2][leafStart * 2];
        Arrays.fill(tree[0], 1000000000);

        // init leaf node
        for (int i = 0; i < N; ++i) {
            int read = Integer.parseInt(br.readLine());
            tree[0][i + leafStart] = read;
            tree[1][i + leafStart] = read;
        }

        // init node
        for (int i = leafStart + N - 1; i > 1; --i) {
            tree[0][i / 2] = Math.min(tree[0][i / 2], tree[0][i]);
            tree[1][i / 2] = Math.max(tree[1][i / 2], tree[1][i]);
        }
    }

    private static void query(int start, int end) {
        start += leafStart - 1;
        end += leafStart - 1;
        int min = 1000000000;
        int max = 0;

        while (start <= end) {
            //선택
            if (start % 2 == 1) {
                min = Math.min(min, tree[0][start]);
                max = Math.max(max, tree[1][start]);
            }
            if (end % 2 == 0) {
                min = Math.min(min, tree[0][end]);
                max = Math.max(max, tree[1][end]);
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        System.out.println(min + " " + max);
    }
}