import java.util.*;
import java.io.*;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; ++t) {
            int x, y;

            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            int distance = y - x;

            int base = (int) Math.sqrt(distance);
            int power = (int) Math.pow(base, 2);

            int answer = 0;
            if (power < distance) {
                answer += base * 2;
            } else {
                answer += base * 2 - 1;
            }

            if (power + base < distance) {
                ++answer;
            }

            System.out.println(answer);
        }
    }
}