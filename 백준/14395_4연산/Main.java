import java.util.*;
import java.io.*;

public class Main {

    private static int s, t;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        br.close();

        bfs();
    }

    private static void bfs() {

        if (s == t) {
            System.out.print("0");
            return;
        }

        Queue<Node> q = new LinkedList<>();

        q.offer(new Node((long) s * s, "*"));
        q.offer(new Node(s * 2, "+"));
        q.offer(new Node(0, "-"));
        if (s != 0) {
            q.offer(new Node(1, "/"));
        }

        while (!q.isEmpty()) {

            Node here = q.poll();
            long n = here.n;
            String op = here.op;

            if (n == t) {
                System.out.print(op);
                return;
            }

            if (n > 1e9 || n == 0) {
                continue;
            }

            long nextN = n * n;
            if (nextN != 1) {
                q.offer(new Node(nextN, op + "*"));
            }

            nextN = n * 2;
            q.offer(new Node(nextN, op + "+"));
        }

        System.out.print("-1");
    }

    static class Node {

        public long n;
        public String op;

        public Node(long n, String op) {
            this.n = n;
            this.op = op;
        }
    }
}