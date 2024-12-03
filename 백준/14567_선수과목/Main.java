import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static List<Integer>[] edges;
    private static int[] degree;

    public static void main(String[] args) throws IOException {

        init();
        solve();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new List[N];
        degree = new int[N];

        for (int i = 0; i < N; ++i) {
            edges[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edges[a].add(b);
            degree[b]++;
        }

        br.close();
    }

    private static void solve() {
        int[] answer = new int[N];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; ++i) {
            if (degree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int here = q.poll();

            for (int there : edges[here]) {
                --degree[there];
                answer[there] = answer[here] + 1;
                if (degree[there] == 0) {
                    q.offer(there);
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            System.out.print((answer[i] + 1) + " ");
        }
    }
}