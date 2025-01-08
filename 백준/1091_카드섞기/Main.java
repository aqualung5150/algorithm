import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] P;
    private static int[] S;
    private static Map<String, Integer> dist = new HashMap<>();
    private static String start;

    public static void main(String[] args) throws IOException {

        init();
        bfs();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        P = new int[N];
        S = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; ++i) {
            P[i] = Integer.parseInt(st1.nextToken());
            S[i] = Integer.parseInt(st2.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            // sb.append(i);
            sb.append((char) i);
        }

        start = sb.toString();

        br.close();
    }

    private static void bfs() {

        Queue<String> q = new LinkedList<>();
        q.offer(start);
        dist.put(start, 0);

        while (!q.isEmpty()) {

            String here = q.poll();
            int curDist = dist.get(here);

            if (isFinish(here)) {
                System.out.print(curDist);
                return;
            }

            String there = shuffle(here);

            if (dist.containsKey(there)) {
                continue;
            }

            q.offer(there);
            dist.put(there, curDist + 1);
        }

        System.out.print("-1");
    }

    private static boolean isFinish(String s) {

        char[] str = s.toCharArray();

        for (int i = 0; i < N; ++i) {
            int player = i % 3;
            int playerIdx = (int) str[i];
            if (P[playerIdx] != player) {
                return false;
            }
        }

        return true;
    }

    private static String shuffle(String s) {

        char[] result = new char[N];
        char[] str = s.toCharArray();

        for (int from = 0; from < N; ++from) {
            int to = S[from];

            result[to] = str[from];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            sb.append(result[i]);
        }

        return sb.toString();
    }
}