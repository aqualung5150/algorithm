import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] allocNumber = new int[26];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {

            char[] str = br.readLine().toCharArray();
            for (int j = 0; j < str.length; ++j) {

                allocNumber[str[j] - 'A'] += Math.pow(10, str.length - 1 - j);
            }
        }

        br.close();

        Arrays.sort(allocNumber);

        int answer = 0;
        int i = 25;
        int mult1 = 9;
        int mult2 = allocNumber[i];
        while (mult2 != 0) {

            answer += mult1 * mult2;
            --i;
            --mult1;
            mult2 = allocNumber[i];            
        }

        System.out.print(answer);
    }
}