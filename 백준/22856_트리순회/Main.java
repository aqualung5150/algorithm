import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[][] edges;
    private static int count = 0;
    private static int target;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        edges = new int[N + 1][2];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a][0] = b;
            edges[a][1] = c;
        }

        target = getLastNode(1);

        dfs(1);
        
        System.out.print(count);

        br.close();
    }

    private static boolean dfs(int cur) {

        int next = edges[cur][0];
        if (next != -1) {
            ++count;
            if(dfs(next)) {
                return true;
            }
            ++count;
        }

        if (cur == target) {
            return true;
        }

        next = edges[cur][1];
        if (next != -1) {
            ++count;
            if(dfs(next)) {
                return true;
            }
            ++count;
        }

        return false;
    }

    private static int getLastNode(int cur) {

        if (edges[cur][1] != -1) {
            return getLastNode(edges[cur][1]);
        } else {
            return cur;
        }
    }
}