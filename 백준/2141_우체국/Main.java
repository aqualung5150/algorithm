import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static long total = 0;
    private static Town[] towns;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        towns = new Town[N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            total += A;
            towns[i] = new Town(X, A);
        }

        br.close();

        Arrays.sort(towns, (o1, o2) -> o1.location - o2.location);

        long mid = (total + 1) / 2;
        long sum = 0;
        for (Town t : towns) {
            sum += t.population;
            if (sum >= mid) {
                System.out.print(t.location);
                return;
            }
        }
    }

    static class Town {
        public int location;
        public int population;

        public Town(int location, int population) {
            this.location = location;
            this.population = population;
        }
    }
}