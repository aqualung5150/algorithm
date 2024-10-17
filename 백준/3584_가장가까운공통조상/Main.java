import java.util.*;
import java.io.*;

public class Main {

    private static int T;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; ++t) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];
            for (int i = 0; i < N - 1; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                parent[c] = p;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[N + 1];

            //bfs
            Queue<Integer> q = new LinkedList<>();
            q.offer(A);
            q.offer(B);
            visited[A] = true;
            visited[B] = true;

            while (!q.isEmpty()) {
                int here = q.poll();
                int curParent = parent[here];

                //root node
                if (curParent == 0)
                    continue;

                //Nearest Common Ancestor
                if (visited[curParent]) {
                    System.out.println(curParent);
                    break;
                }

                visited[curParent] = true;
                q.offer(curParent);
            }
        }
    }
}