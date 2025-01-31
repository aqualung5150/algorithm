import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static boolean[] notPrime;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        notPrime = new boolean[N + 1];

        int n_B = 1;
        int n_S = 0;
        notPrime[1] = true;
        for (int i = 2; i <= N; ++i) {

            if (!notPrime[i]) {

                if (notPrime[i - 1]) {
                    --n_B;
                    n_S += 2;
                } else {
                    ++n_S;
                }
            } else {
                ++n_B;
                continue;
            }

            for (int j = i * 2; j <= N; j += i) {
                notPrime[j] = true;
            }
        }

        System.out.print(n_B + " " + n_S);

        br.close();
    }
}