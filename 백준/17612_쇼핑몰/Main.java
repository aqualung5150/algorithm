import java.util.*;
import java.io.*;

public class Main {

    private static int N, K;
    private static Queue<Customer> customers = new LinkedList<>();
    private static boolean[] inUse;
    private static PriorityQueue<Task> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1.finishTime == o2.finishTime) {
            return o2.allocatedNumber - o1.allocatedNumber;
        }
        return o1.finishTime - o2.finishTime;
    });

    public static void main(String[] args) throws IOException {

        init();

        long answer = 0;
        int time = 0;
        int finishedOrder = 1;
        while (finishedOrder <= N) {

            //결제 완료
            while (!pq.isEmpty() && pq.peek().finishTime == time) {
                Task cur = pq.poll();
                answer += (long) finishedOrder * cur.id;
                inUse[cur.allocatedNumber] = false;
                ++finishedOrder;
            }
            
            //새로운 고객
            for (int i = 0; i < K && !customers.isEmpty(); ++i) {
                if (!inUse[i]) {
                    Customer newCustomer = customers.poll();
                    pq.offer(new Task(newCustomer.id, time + newCustomer.w, i));
                    inUse[i] = true;
                }
            }

            ++time;
        }

        System.out.println(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        inUse = new boolean[K];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            customers.offer(new Customer(id, w));
        }

        br.close();
    }

    static class Customer {
        public int id;
        public int w;

        public Customer(int id, int w) {
            this.id = id;
            this.w = w;
        }
    }

    static class Task {
        
        public int id;
        public int finishTime;
        public int allocatedNumber;

        public Task(int id, int finishTime, int allocatedNumber) {
            this.id = id;
            this.finishTime = finishTime;
            this.allocatedNumber = allocatedNumber;
        }
    }
}