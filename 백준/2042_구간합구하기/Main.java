import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M = 0;
    private static long[] tree;
    private static int leafStart = 1;
    private static int leafEnd;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M += Integer.parseInt(st.nextToken());
        M += Integer.parseInt(st.nextToken());

        initTree();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            if (a.equals("1")) {
                updateNode(
                    Integer.parseInt(st.nextToken()),
                    Long.parseLong(st.nextToken())
                );
            } else {
                printSum(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
                );
            }
        }

        br.close();
    }

    private static void initTree() throws IOException {
        while (leafStart < N) {
            leafStart *= 2;
        }
        leafEnd = leafStart + N - 1;

        tree = new long[leafStart * 2];

        for (int i = leafStart; i <= leafEnd; ++i) {
            tree[i] = Long.parseLong(br.readLine());
        }

        for (int i = leafEnd; i > 1; --i) {
            tree[i / 2] += tree[i];
        }
    }

    private static void updateNode(int idx, long n) {

        idx += leafStart - 1;
        long diff = n - tree[idx];
        while (idx >= 1) {
            tree[idx] += diff;
            idx /= 2;
        }
    }

    private static void printSum(int start, int end) {
        
        start += leafStart - 1;
        end += leafStart - 1;
        long sum = 0;

        while (start <= end) {

            if (start % 2 == 1) {
                sum += tree[start];
            }
            if (end % 2 == 0) {
                sum += tree[end];
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        System.out.println(sum);
    }
}