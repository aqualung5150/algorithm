import java.util.*;
import java.io.*;

public class Main {

    private static int T;
    private static final boolean[] prime = new boolean[10000];

    public static void main(String[] args) throws IOException {

        //prime
        initPrimeNubmer();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            int from, to;
            StringTokenizer st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            //bfs
            bfs(from, to);
        }
    }

    private static void initPrimeNubmer() {

        Arrays.fill(prime, true);

        for (int i = 2; i * i < 10000; ++i) {
            if (prime[i] == false) {
                continue;
            }

            for (int j = i * i; j < 10000; j += i) {
                prime[j] = false;
            }
        }
    }

    private static void bfs(int from, int to) {

        int[] dist = new int[10000];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(from);
        dist[from] = 0;

        while (!q.isEmpty()) {
            int here = q.poll();
            int curDist = dist[here];

            //answer
            if (here == to) {
                System.out.println(dist[here]);
                return;
            }

            //next prime
            for (int digit = 1; digit <= 1000; digit *= 10) {
                int target = (here / digit) % 10;
                int base = here - (target * digit);

                for (int i = 1; i <= 9; ++i) {
                    int nextTarget = (target + i) % 10;
                    int nextPrime = base + nextTarget * digit;

                    if (nextPrime < 1000 || prime[nextPrime] == false || dist[nextPrime] != -1)
                        continue;
                    
                    q.offer(nextPrime);
                    dist[nextPrime] = curDist + 1;
                }
            }
        }

        System.out.println(0);
    }
}