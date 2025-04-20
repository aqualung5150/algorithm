import java.util.*;
import java.io.*;

public class Main {

    private static int t, n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {

            n = Integer.parseInt(br.readLine());
            List<String> words = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                words.add(br.readLine());
            }

            boolean valid = true;
            Node root = new Node();
            for (String word : words) {
                if (!makeTree(root, word)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        br.close();
    }

    private static boolean makeTree(Node curNode, String word) {

        char[] charArray = word.toCharArray();
        for (char c : charArray) {

            if (curNode.child == null) {
                curNode.child = new HashMap<>();
            }

            curNode.child.putIfAbsent(c, new Node());
            curNode = curNode.child.get(c);

            if (curNode.hasWord) {
                return false;
            }
        }

        curNode.hasWord = true;
        return curNode.child == null;
    }

    static class Node {

        public Map<Character, Node> child = null;
        public boolean hasWord = false;
    }
}