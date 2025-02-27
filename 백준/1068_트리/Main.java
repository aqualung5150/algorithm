import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static List<Integer>[] edges;
    private static int root;
    private static int removed;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        edges = new List[N];
        for (int i = 0; i < N; ++i) {
            edges[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            int n = Integer.parseInt(st.nextToken());

            if (n != -1) {
                edges[n].add(i);
            } else {
                root = i;
            }
        }

        removed = Integer.parseInt(br.readLine());

        if (removed == root) {
            System.out.print(0);
            return;
        }

        dfs(root);

        System.out.print(answer);

        br.close();
    }

    private static void dfs(int cur) {

        boolean isLeaf = true;

        int size = edges[cur].size();
        for (int i = 0; i < size; ++i) {
            int next = edges[cur].get(i);
            if (next == removed) {
                continue;
            }

            isLeaf = false;

            dfs(next);
        }

        if (isLeaf) {
            ++answer;
        }
    }
}