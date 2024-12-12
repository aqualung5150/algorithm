import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    private static int N;
    private static Set<Integer> alphaSet = new HashSet<>();
    private static List<Integer> alphaList;
    private static int alphaSize;
    private static int[] allocNumber = new int[26];
    private static boolean[] visited = new boolean[26];
    private static String[] words;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {

        init();
        dfs(0);
        System.out.print(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; ++i) {
            String input = br.readLine();
            words[i] = input;

            //init alphaSet
            char[] str = input.toCharArray();
            for (int j = 0; j < str.length; ++j) {
                alphaSet.add(str[j] - 'A');
            }
        }

        alphaList = alphaSet.stream().collect(Collectors.toList());
        alphaSize = alphaList.size();

        br.close();
    }

    private static void dfs(int idx) {
        if (idx == alphaList.size()) {
            getSum();
        } else {

            for (int i = 9; i > 9 - alphaSize; --i) {
                if (visited[i]) {
                    continue;
                }

                visited[i] = true;
                allocNumber[alphaList.get(idx)] = i;
                dfs(idx + 1);
                visited[i] = false;
            }
        }
    }

    private static void getSum() {

        int result = 0;
        for (String w : words) {
            int sum = 0;
            char[] str = w.toCharArray();
            for (char c : str) {
                sum *= 10;
                sum += allocNumber[c - 'A'];
            }
            result += sum;
        }

        answer = Math.max(answer, result);
    }
}