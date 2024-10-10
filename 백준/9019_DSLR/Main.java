import java.util.*;
import java.io.*;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; ++i) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[10000];
            Queue<Point> q = new LinkedList<>();
            q.offer(new Point(A, ""));
            visited[A] = true;

            while (!q.isEmpty()) {
                Point here = q.poll();
                int cur = here.n;
                String op = here.op;

                // answer
                if (cur == B) {
                    System.out.println(op);
                    break;
                }

                // D
                int next = (cur * 2) % 10000;
                if (!visited[next]) {
                    q.offer(new Point(next, op + "D"));
                    visited[next] = true;
                }

                // S
                next = cur - 1 < 0 ? 9999 : cur - 1;
                if (!visited[next]) {
                    q.offer(new Point(next, op + "S"));
                    visited[next] = true;
                }

                // L
                int mod = cur % 1000;
                int div = cur / 1000;
                next = mod * 10 + div;
                if (!visited[next]) {
                    q.offer(new Point(next, op + "L"));
                    visited[next] = true;
                }

                // R
                mod = cur % 10;
                div = cur / 10;
                next = mod * 1000 + div;
                if (!visited[next]) {
                    q.offer(new Point(next, op + "R"));
                    visited[next] = true;
                }
            }
        }
    }

    static class Point {
        public int n;
        public String op;

        public Point(int n, String op) {
            this.n = n;
            this.op = op;
        }
    }
}