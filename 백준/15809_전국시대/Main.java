import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static int[] power;
    private static int[] parent;
    private static History[] history;

    public static void main(String[] args) throws IOException {

        init();

        for (History h : history) {

            int status = h.status;
            int x = findRoot(h.x);
            int y = findRoot(h.y);

            if (status == 1) {
                power[x] += power[y];
                power[y] = 0;
                unionRoot(x, y);
            } else if (status == 2) {
                if (power[x] > power[y]) {
                    power[x] -= power[y];
                    power[y] = 0;
                    unionRoot(x, y);
                } else {
                    power[y] -= power[x];
                    power[x] = 0;
                    unionRoot(y, x);
                }
            }
        }

        int remain = 0;
        List<Integer> remainPower = new ArrayList<>();
        for (int i = 1; i <= N; ++i) {
            if (power[i] != 0) {
                ++remain;
                remainPower.add(power[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(remainPower);
        for (int p : remainPower) {
            sb.append(p);
            sb.append(" ");
        }

        System.out.println(remain);
        System.out.print(sb);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        power = new int[N + 1];
        parent = new int[N + 1];
        history = new History[M];

        for (int i = 1; i <= N; ++i) {
            power[i] = Integer.parseInt(br.readLine());
            parent[i] = i;
        }

        for (int i = 0 ; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int status = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            history[i] = new History(status, x, y);
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

    static class History {

        public int status;
        public int x;
        public int y;

        public History(int status, int x, int y) {
            this.status = status;
            this.x = x;
            this.y = y;
        }
    }
}