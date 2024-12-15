import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static boolean[] visited = new boolean[10001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> pq = new PriorityQueue<>((o1, o2) -> o2.p - o1.p);
        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.offer(new Lecture(d, p));
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            Lecture here = pq.poll();

            while (visited[here.d] && here.d > 0) {
                --here.d;
            }

            if (here.d == 0) {
                continue;
            }

            visited[here.d] = true;
            answer += here.p;
        }

        System.out.print(answer);

        br.close();
    }

    static class Lecture {
        public int d;
        public int p;

        public Lecture(int d, int p) {
            this.d = d;
            this.p = p;
        }
    }
}