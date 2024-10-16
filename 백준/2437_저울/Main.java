import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());    
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int nextCoverage = 1;
        for (int i = 0; i < n; ++i) {
            if (arr[i] > nextCoverage)
                break;
            nextCoverage += arr[i];
        }

        System.out.print(nextCoverage);
    }

}