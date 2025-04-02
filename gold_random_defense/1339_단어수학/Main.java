import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] alphaDigits = new int[26];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            char[] arr = br.readLine().toCharArray();

            int size = arr.length;
            for (int i = 0; i < size; ++i) {
                alphaDigits[arr[i] - 'A'] += Math.pow(10, size - 1 - i);
            }
        }

        Arrays.sort(alphaDigits);

        int answer = 0;
        int mult = 9;
        for (int i = 25; i >= 0; --i) {
            answer += alphaDigits[i] * mult--;
        }

        System.out.print(answer);

        br.close();
    }
}