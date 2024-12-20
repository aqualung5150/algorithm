import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static Map<Integer, Integer> tests = new HashMap<>();
    private static PriorityQueue<Test> maxHeap;
    private static PriorityQueue<Test> minHeap;

    public static void main(String[] args) throws IOException {

        init();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; ++i) {

            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            if (op.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                
                addTest(P, L);
            } else if (op.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());

                tests.remove(P);
            } else {
                int x = Integer.parseInt(st.nextToken());

                if (x == 1) {
                    recommend(maxHeap);
                } else {
                    recommend(minHeap);
                }
            }
        }
    }

    private static void init() throws IOException {

        maxHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1.L == o2.L) {
                return o2.P - o1.P;
            }
            return o2.L - o1.L;
        });

        minHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1.L == o2.L) {
                return o1.P - o2.P;
            }
            return o1.L - o2.L;
        });

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            
            addTest(P, L);
        }
    }

    private static void addTest(int P, int L) {
        tests.put(P, L);
        maxHeap.offer(new Test(P, L));
        minHeap.offer(new Test(P, L));
    }

    private static void recommend(PriorityQueue<Test> heap) {
        while (true) {
            Test top = heap.peek();
            if (tests.containsKey(top.P) && tests.get(top.P) == (top.L)) {
                System.out.println(top.P);
                break;
            } else {
                heap.poll();
            }
        }
    }

    static class Test {
        public int P;
        public int L;

        public Test(int P, int L) {
            this.P = P;
            this.L = L;
        }
    }
}