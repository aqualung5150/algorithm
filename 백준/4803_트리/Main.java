import java.util.*;
import java.io.*;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 1;
        while (true) {
            //init
            int n, m;
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }
            
            parent = new int[n + 1];
            
            for (int i = 1; i <= n; ++i) {
                parent[i] = i;
            }

            Set<Integer> cycleRoot = new HashSet<>();
            for (int i = 0; i < m; ++i) {
                int a, b;
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                int from = findRoot(a);
                int to = findRoot(b);
                
                if (from == to) {
                    cycleRoot.add(to);
                } else {
                    
                    if (cycleRoot.contains(from) || cycleRoot.contains(to)) {
                        cycleRoot.add(from);
                        cycleRoot.add(to);
                    }

                    unionRoot(a, b);
                }
            }

            int answer = 0;
            for (int i = 1; i <= n; ++i) {
                int root = findRoot(i);

                if (cycleRoot.contains(root)) {
                    continue;
                }
                answer++;
                cycleRoot.add(root);
            }

            if (answer == 0) {
                System.out.println("Case " + T + ": No trees.");
            } else if (answer == 1) {
                System.out.println("Case " + T + ": There is one tree.");
            } else {
                System.out.println("Case " + T + ": A forest of " + answer + " trees.");
            }

            ++T;
        }

        br.close();
    }

    private static int findRoot(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = findRoot(parent[x]);
    }

    private static void unionRoot(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    static class Edge {
        int a;
        int b;

        public Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}