import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    private static Integer[] cranes;
    private static Integer[] containers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //crane
        N = Integer.parseInt(br.readLine());
        cranes = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }
        //container
        M = Integer.parseInt(br.readLine());
        containers = new Integer[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            containers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cranes, Collections.reverseOrder());
        Arrays.sort(containers, Collections.reverseOrder());

        if (cranes[0] < containers[0]) {
            System.out.print(-1);
            return;
        }

        int answer = 0;
        boolean[] visited = new boolean[M];
        int checked = 0;
        while (checked < M) {
            ++answer;

            for (int i = 0; i < cranes.length; ++i) {
                int j = 0;
                while (j < M && (containers[j] > cranes[i] || visited[j])) {
                    ++j;
                }

                if (j < M) {
                    visited[j] = true;
                    ++checked;
                } else {
                    break;
                }
            }
        }

        System.out.print(answer);
    }
}