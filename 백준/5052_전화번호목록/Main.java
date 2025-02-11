import java.util.*;
import java.io.*;

public class Main {

    private static int t, n;
    private static String[] arr;
    private static Node root;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            n = Integer.parseInt(br.readLine());
            arr = new String[n];
            root = new Node();

            for (int i = 0; i < n; ++i) {
                arr[i] = br.readLine();
            }

            boolean valid = true;
            for (String read : arr) {
                
                if (!insert(read)) {
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
    }

    private static boolean insert(String str) {

        Node curNode = root;
        int length = str.length();
        for (int i = 0; i < length; ++i) {

            char c = str.charAt(i);

            if (curNode.child == null) {
                curNode.child = new HashMap<Character, Node>();
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

    public static class Node {

        public boolean hasWord = false;
        public Map<Character, Node> child = null;
    }
}