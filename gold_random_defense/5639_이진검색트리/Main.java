import java.util.*;
import java.io.*;

public class Main {

    private static List<Integer> preOrder = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        init();

        Node root = restoreTree(0, preOrder.size() - 1);

        appendPostOrder(root);

        System.out.print(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String read = br.readLine();
        while (read != null) {
            preOrder.add(Integer.parseInt(read));
            read = br.readLine();
        }

        br.close();
    }

    private static Node restoreTree(int begin, int end) {

        if (begin > end) {
            return null;
        }

        int cur = preOrder.get(begin);
        Node curNode = new Node(cur);

        int rightBegin = begin + 1;
        while (rightBegin < preOrder.size() && preOrder.get(rightBegin) < cur) {
            ++rightBegin;
        }

        curNode.left = restoreTree(begin + 1, rightBegin - 1);
        curNode.right = restoreTree(rightBegin, end);

        return curNode;
    }

    private static void appendPostOrder(Node node) {

        if (node == null) {
            return;
        }

        appendPostOrder(node.left);
        appendPostOrder(node.right);
        sb.append(node.n).append("\n");
    }

    static class Node {
        public int n;
        public Node left;
        public Node right;

        public Node(int n) {
            this.n = n;
        }
    }
}