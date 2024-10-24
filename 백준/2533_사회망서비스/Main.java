import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static Node[] edges;
    private static int dp[][];
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        edges = new Node[N + 1];

        dp = new int[2][N + 1]; // [0]얼리어답터, [1]일반인
        visited = new boolean[N + 1];

        for (int i = 1; i <= N - 1; ++i) {
            int u, v;
            StringTokenizer st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            edges[u] = new Node(v, edges[u]);
            edges[v] = new Node(u, edges[v]);
        }

        find(1);

        System.out.print(Math.min(dp[0][1], dp[1][1]));
    }

    private static void find(int parent) {
        visited[parent] = true;
        dp[0][parent] = 1;
        for (Node e = edges[parent]; e != null; e = e.next) {
            int child = e.v;
            if (visited[child])
                continue;

            find(child);

            dp[1][parent] += dp[0][child];
            dp[0][parent] += Math.min(dp[0][child], dp[1][child]);
        }
    }

    static class Node {
        int v;
        Node next;

        public Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }
    }
}