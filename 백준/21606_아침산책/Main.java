import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static List<Integer>[] edges;
    private static boolean[] indoor;
    private static boolean[] visited;
    

    public static void main(String[] args) throws IOException {

        init();

        long answer = 0;

        for (int i = 0; i < N; ++i) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            if (indoor[i]) {
                for (int e : edges[i]) {
                    if (indoor[e]) {
                        ++answer;
                    }
                }
            } else {
                int count = countIndoor(i);
                answer += (long) count * (count - 1);
            }
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String read = br.readLine();
        edges = new List[N];
        indoor = new boolean[N];
        visited = new boolean[N];
        for (int i = 0; i < N; ++i) {
            edges[i] = new ArrayList<>();
            indoor[i] = read.charAt(i) == '1' ? true : false;
        }

        for (int i = 0; i < N - 1; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;

            edges[v1].add(v2);
            edges[v2].add(v1);
        }

        br.close();
    }

    private static int countIndoor(int idx) {

        int result = 0;
        for (int e : edges[idx]) {

            if (indoor[e] == true) {
                ++result;
                continue;
            }
            
            if (visited[e]) {
                continue;
            }

            visited[e] = true;
            result += countIndoor(e);
        }

        return result;
    }
}