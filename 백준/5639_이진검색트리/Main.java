import java.util.*;
import java.io.*;

public class Main {

    private static Node root;
    private static List<Integer> preOrder = new ArrayList<>();
    private static int preOrderIdx = 0;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String read = br.readLine();
        while (read != null) {
            int n = Integer.parseInt(read);
            preOrder.add(n);

            read = br.readLine();
        }

        root = treeRestore(0, preOrder.size() - 1);

        appendPostOrder(root);
        System.out.print(sb);
    }

    private static Node treeRestore(int begin, int end) {

        if (begin > end) {
            return null;
        }

        Node newNode = new Node(preOrder.get(preOrderIdx));

        int rightBeginIdx = begin + 1;
        while (rightBeginIdx < preOrder.size() && preOrder.get(rightBeginIdx) < preOrder.get(preOrderIdx)) {
            ++rightBeginIdx;
        }

        ++preOrderIdx;

        newNode.left = treeRestore(begin + 1, rightBeginIdx - 1);
        newNode.right = treeRestore(rightBeginIdx, end);

        return newNode;
    }

    private static void appendPostOrder(Node root) {

        if (root == null) {
            return;
        }
        
        appendPostOrder(root.left);
        appendPostOrder(root.right);
        sb.append(root.n).append(" ");
    }

    static class Node {
        public int n;
        public Node left = null;
        public Node right = null;

        public Node(int n) {
            this.n = n;
        }
    }
}