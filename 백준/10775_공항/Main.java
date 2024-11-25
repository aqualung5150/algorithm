import java.util.*;
import java.io.*;

public class Main {

    private static int G, P;
    private static int[] nextGate;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        nextGate = new int[G + 1];
        for (int i = 1; i <= G; ++i) {
            nextGate[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < P; ++i) {
            int n = Integer.parseInt(br.readLine());

            n = findRoot(n);

            if (n == 0) {
                //flush
                for (++i; i < P; ++i) {
                    br.readLine();
                }
                break;
            }

            nextGate[n] = findRoot(n - 1);
            ++answer;
        }

        System.out.print(answer);
    }

    private static int findRoot(int x) {
        if (x == nextGate[x]) {
            return x;
        }

        return nextGate[x] = findRoot(nextGate[x]);
    }
}