import java.util.*;
import java.io.*;

public class Main {

    private static int N, root, removed, answer = 0;
    private static List<Integer>[] edges;

    public static void main(String[] args) throws IOException {

        init();

        if (removed != root) {
            dfs(root);
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        edges = new List[N];
        for (int i = 0; i < N; ++i) {
            edges[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int to = 0; to < N; ++to) {
            int from = Integer.parseInt(st.nextToken());

            if (from == -1) {
                root = to;
            } else {
                edges[from].add(to);
            }
        }

        removed = Integer.parseInt(br.readLine());

        br.close();
    }

    private static void dfs(int idx) {

        boolean isLeaf = true;

        for (Integer child: edges[idx]) {

            if (child == removed) {
                continue;
            }

            isLeaf = false;

            dfs(child);
        }

        if (isLeaf) {
            ++answer;
        }
    }
}