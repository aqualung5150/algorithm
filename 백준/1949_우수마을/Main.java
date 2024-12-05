import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] score;
    private static Node[] edge;
    private static int[][] dp; //0: 우수, 1: 일반

    public static void main(String[] args) throws IOException {

        init();
        find(1);
        
        System.out.print(Math.max(dp[0][1], dp[1][1]));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        score = new int[N + 1];
        edge = new Node[N + 1];
        dp = new int[2][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edge[u] = new Node(v, edge[u]);
            edge[v] = new Node(u, edge[v]);
        }

        for (int i = 0; i <= N; ++i) {
            dp[0][i] = -1;
            dp[1][i] = -1;
        }

        br.close();
    }

    private static void find(int parent) {
        dp[0][parent] = score[parent];
        dp[1][parent] = 0;

        for (Node e = edge[parent]; e != null; e = e.next) {
            int child = e.v;

            if (dp[0][child] != -1) {
                continue;
            }

            find(child);

            dp[0][parent] += dp[1][child];
            dp[1][parent] += Math.max(dp[0][child], dp[1][child]);
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