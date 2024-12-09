import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            System.out.print("A");
            return;
        }

        if (N == 2) {
            if (arr[0] == arr[1]) {
                System.out.print(arr[0]);
            } else {
                System.out.print("A");
            }
            return;
        }

        int a = 0;
        if (arr[1] - arr[0] != 0) {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
        }
        int b = arr[1] - arr[0] * a;

        int i = 1;
        while (i < N) {
            if (arr[i] != arr[i - 1] * a + b) {
                System.out.print("B");
                return;
            }
            ++i;
        }

        if (i == N) {
            System.out.print(arr[N - 1] * a + b);
        }
    }
}