import java.util.*;
import java.io.*;

public class Main {
    
    private static final int INF = 1000000007;

    private static int N, K;
    private static int[] arr;
    private static int[] LIS;
    private static int[] count;

    public static void main(String[] args) throws IOException {

        init();

        for (int i = N; i >= 1; --i) {
            LIS[i] = 1;
            count[i] = 1;
            for (int j = i + 1; j <= N; ++j) {
                if (arr[i] < arr[j]) {
                    if (LIS[i] < LIS[j] + 1) {
                        LIS[i] = LIS[j] + 1;
                        count[i] = 0;
                    }

                    if (LIS[i] == LIS[j] + 1) {
                        count[i] += count[j];
                        count[i] = Math.min(count[i], INF);
                    }
                }
            }
        }

        int maxLIS = Arrays.stream(LIS).max().getAsInt();
        List<Integer>[] LISList = new List[maxLIS + 1];
        for (int i = 0; i <= maxLIS; ++i) {
            LISList[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; ++i) {
            LISList[LIS[i]].add(i);
        }
        for (int i = 1; i <= maxLIS; ++i) {
            Collections.sort(LISList[i], (o1, o2) -> arr[o1] - arr[o2]);
        }

        int sum = 0;
        for (int e : LISList[maxLIS]) {
            sum += count[e];
            sum = Math.min(sum, INF);
        }
        if (sum < K) {
            System.out.print("-1");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int prevIdx = 0;
        for (int i = maxLIS; i >= 1; --i) {
            for (int j : LISList[i]) {
                if (prevIdx < j && arr[prevIdx] < arr[j]) {
                    if (K > count[j]) {
                        K -= count[j];
                    } else {
                        sb.append(arr[j]);
                        sb.append(" ");
                        prevIdx = j;
                        break;
                    }
                }
            }
        }

        System.out.print(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        LIS = new int[N + 1];
        count = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        br.close();
    }
}