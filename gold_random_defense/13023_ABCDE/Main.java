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
                System.out.print("1");
                return;
            }
            visited[i] = false;
        }

        System.out.print("0");
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new List[N];
        for (int i = 0; i < N; ++i) {
            edges[i] = new ArrayList<>();
        }
        
        visited = new boolean[N];

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a].add(b);
            edges[b].add(a);
        }

        br.close();
    }

    private static boolean dfs(int idx, int cur) {

        if (idx == 4) {
            return true;
        }

        for (Integer next: edges[cur]) {
            if (visited[next]) {
                continue;
            }

            visited[next] = true;
            if (dfs(idx + 1, next)) {
                return true;
            }
            visited[next] = false;
        }

        return false;
    }
}