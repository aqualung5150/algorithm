import java.util.*;
import java.io.*;

public class Main {

    private static int N, K;
    private static char[] startLine;
    private static Map<String, Integer> distance = new HashMap<>();

    public static void main(String[] args) throws IOException {

        init();
        bfs();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        startLine = new char[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            startLine[i] = st.nextToken().charAt(0);
        }

        br.close();
    }

    private static void bfs() {

        Queue<String> q = new LinkedList<>();
        String start = new String(startLine);
        q.offer(start);
        distance.put(start, 0);

        while (!q.isEmpty()) {
            String here = q.poll();
            int curDistance = distance.get(here);

            if (isSorted(here)) {
                System.out.print(curDistance);
                return;
            }

            for (int i = 0; i <= N - K; ++i) {
                String there = reverse(here, i);
                
                if (distance.containsKey(there)) {
                    continue;
                }

                distance.put(there, curDistance + 1);
                q.offer(there);
            }
        }

        System.out.print(-1);
    }

    private static boolean isSorted(String s) {
        char[] str = s.toCharArray();

        for (int i = 1; i < str.length; ++i) {
            if (str[i] <= str[i - 1]) {
                return false;
            }
        }

        return true;
    }

    private static String reverse(String s, int start) {
        char[] str = s.toCharArray();
        char[] buffer = new char[K];

        for (int i = 0; i < K; ++i) {
            char c = str[start + K - 1 - i];

            buffer[i] = c;
        }

        for (int i = 0; i < K; ++i) {
            str[start + i] = buffer[i];
        }

        return new String(str);
    }
}