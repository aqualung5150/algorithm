import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static char[] preOrder;
    private static char[] inOrder;
    private static int preOrderIdx;

    public static void main(String[] args) throws IOException {

        String read = br.readLine();
        while (read != null) {

            st = new StringTokenizer(read);

            preOrder = st.nextToken().toCharArray();
            inOrder = st.nextToken().toCharArray();
            preOrderIdx = 0;

            Node root = treeRestore(0, inOrder.length - 1);
            printPostOrder(root);
            System.out.println();

            read = br.readLine();
        }
    }

    private static Node treeRestore(int begin, int end) {

        if (begin > end) {
            return null;
        }

        Node curNode = new Node(preOrder[preOrderIdx++]);

        int inOrderIdx = getInOrderIdx(curNode.c, begin, end);

        curNode.left = treeRestore(begin, inOrderIdx - 1);
        curNode.right = treeRestore(inOrderIdx + 1, end);

        return curNode;
    }

    private static int getInOrderIdx(char c, int begin, int end) {

        while (begin <= end) {
            if (inOrder[begin] == c) {
                break;
            }
            ++begin;
        }

        return begin;
    }

    private static void printPostOrder(Node root) {
        if (root == null) {
            return;
        }

        printPostOrder(root.left);
        printPostOrder(root.right);

        System.out.print(root.c);
    }

    static class Node {
        public char c;
        public Node left = null;
        public Node right = null;

        public Node(char c) {
            this.c = c;
        }
    }
}