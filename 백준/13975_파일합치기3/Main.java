import java.util.*;
import java.io.*;

public class Main {

    private static int T, K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; ++i) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            System.out.println(mergeFiles(pq));
        }
    }

    private static long mergeFiles(PriorityQueue<Long> pq) {
        long sum = 0;

        while (pq.size() > 1) {
            long a = pq.poll();
            long b = pq.poll();

            sum += a + b;
            pq.offer(a + b);
        }

        return sum;
    }
}