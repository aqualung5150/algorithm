import java.util.*;
import java.io.*;

public class Main {

    private static String input;
    private static List<Bracket> brackets = new ArrayList<>();
    private static boolean[] visited; //true: include, false: exclude
    private static Set<String> results = new TreeSet<>();

    public static void main(String[] args) throws IOException {

        init();
        dfs(-1);
        for (String str : results) {
            System.out.println(str);
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        visited = new boolean[input.length()];
        int left = 0;
        int right = input.length() - 1;

        char[] str = input.toCharArray();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < str.length; ++i) {
            if (str[i] == '(') {
                stack.push(i);
            } else if (str[i] == ')') {
                brackets.add(new Bracket(stack.pop(), i));
            }
        }

        br.close();
    }

    private static void dfs(int idx) {
        if (idx == brackets.size() - 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length(); ++i) {
                if (!visited[i]) {
                    sb.append(input.charAt(i));
                }
            }
            if (sb.length() != input.length()) {
                results.add(sb.toString());
            }
        } else {

            int s = brackets.get(idx + 1).s;
            int e = brackets.get(idx + 1).e;
            for (int i = 0; i < 2; ++i) {
                visited[s] = !visited[s];
                visited[e] = !visited[e];
                dfs(idx + 1);
            }
        }
    }

    static class Bracket {
        int s;
        int e;

        public Bracket(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }
}