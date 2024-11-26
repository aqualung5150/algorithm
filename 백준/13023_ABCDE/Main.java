import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static List<Integer>[] edges;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < N; ++i) {
            visited[i] = true;
            if (dfs(0, i)) {
                System.out.print(1);
                return;
            }
            visited[i] = false;
        }
        System.out.print(0);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];

        edges = new List[N];
        for (int i = 0; i < N; ++i) {
            edges[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            edges[v1].add(v2);
            edges[v2].add(v1);
        }

        br.close();
    }

    private static boolean dfs(int idx, int here) {
        if (idx == 4) {
            return true;
        }

        for (int i = 0; i < edges[here].size(); ++i) {

            int there = edges[here].get(i);
            if (visited[there]) {
                continue;
            }

            visited[there] = true;
            if (dfs(idx + 1, there)) {
                return true;
            }
            visited[there] = false;
        }

        return false;
    }
}