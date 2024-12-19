import java.util.*;
import java.io.*;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        
        int total = 0;
        int countTwo = 0;
        for (int i = 0; i < N; ++i) {
            int n = Integer.parseInt(st.nextToken());
            total += n;
            countTwo += n / 2;
        }

        boolean possible = total % 3 == 0 ? true : false;
        int totalTime = total / 3;
        if (possible && countTwo >= totalTime) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }
}