import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; ++i) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (pq.size() > 1) {

            int a = pq.poll();
            int b = pq.poll();

            answer += a + b;
            pq.offer(a + b);
        }

        System.out.print(answer);

        br.close();
    }
}