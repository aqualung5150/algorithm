import java.util.*;
import java.io.*;

public class Main {

    private static Map<String, Integer> distance = new HashMap<>();
    private static String start;

    public static void main(String[] args) throws IOException {

        init();
        System.out.print(bfs());
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j) {
                sb.append(st.nextToken());
            }
        }

        start = sb.toString();

        br.close();
    }

    private static int bfs() {

        Queue<String> q = new LinkedList<>();
        q.offer(start);
        distance.put(start, 0);

        while (!q.isEmpty()) {

            String here = q.poll();
            int curDistance = distance.get(here);

            if (here.equals("123456780")) {
                return curDistance;
            }

            for (int i = 0; i < 4; ++i) {
                String there = getNextString(here, i);

                if (there == null || distance.containsKey(there)) {
                    continue;
                }

                q.offer(there);
                distance.put(there, curDistance + 1);
            }
        }

        return -1;
    }

    private static String getNextString(String str, int direction) {

        int zeroIdx = str.indexOf('0');
        int targetIdx = -1;

        if (direction == 0 && zeroIdx % 3 != 2) {
            targetIdx = zeroIdx + 1;
        } else if (direction == 1 && zeroIdx % 3 != 0) {
            targetIdx = zeroIdx - 1;
        } else if (direction == 3 && zeroIdx - 3 >= 0) {
            targetIdx = zeroIdx - 3;
        } else if (direction == 2 && zeroIdx + 3 < 9) {
            targetIdx = zeroIdx + 3;
        }

        if (targetIdx == -1) {
            return null;
        }

        return stringSwap(str, zeroIdx, targetIdx);
    }

    private static String stringSwap(String str, int idx1, int idx2) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(idx1, str.charAt(idx2));
        sb.setCharAt(idx2, str.charAt(idx1));
        return sb.toString();
    }
}