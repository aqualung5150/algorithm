import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb;
    private static int T, F;
    private static Map<String, String> parents;
    private static Map<String, Integer> counts;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            init();
            System.out.print(sb);
        }
    }

    private static void init() throws IOException {

        sb = new StringBuilder();

        parents = new HashMap<>();
        counts = new HashMap<>();

        F = Integer.parseInt(br.readLine());
        for (int i = 0; i < F; ++i) {
            st = new StringTokenizer(br.readLine());

            String x = st.nextToken();
            String y = st.nextToken();

            parents.putIfAbsent(x, x);
            parents.putIfAbsent(y, y);
            counts.putIfAbsent(x, 1);
            counts.putIfAbsent(y, 1);

            unionRoot(x, y);
        }
    }

    private static String findRoot(String x) {

        String parent = parents.get(x);

        if (parent.equals(x)) {
            return x;
        }

        String root = findRoot(parent);
        parents.put(x, root);
        return root;
    }

    private static void unionRoot(String x, String y) {
        x = findRoot(x);
        y = findRoot(y);

        if (!x.equals(y)) {
            counts.put(x, counts.get(x) + counts.get(y));
            parents.put(y, x);
        }

        sb.append(counts.get(x)).append("\n");
    }
}