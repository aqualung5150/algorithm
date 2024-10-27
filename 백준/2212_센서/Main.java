import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int K;
    private static int[] sensors;
    private static Integer[] diffs;

    public static void main(String[] args) throws IOException {
        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        sensors = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        //집중국 개수가 충분히 많을때
        if (K >= N) {
            System.out.print(0);
            return;
        }

        Arrays.sort(sensors);

        diffs = new Integer[N - 1];
        for (int i = 0; i < N - 1; ++i) {
            diffs[i] = sensors[i + 1] - sensors[i];
        }

        Arrays.sort(diffs, Collections.reverseOrder());

        int answer = sensors[N - 1] - sensors[0];
        for (int i = 0; i < K - 1; ++i) {
            answer -= diffs[i];
        }

        System.out.print(answer);

        br.close();
    }
}