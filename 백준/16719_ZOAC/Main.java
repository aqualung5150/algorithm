import java.util.*;
import java.io.*;

public class Main {

    private static boolean[] checked;
    private static char[] str;
    
    public static void main(String[] args) throws IOException {
        
        init();
        recur(0, str.length - 1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        str = input.toCharArray();
        checked = new boolean[input.length()];

        br.close();
    }

    private static void recur(int left, int right) {

        int minIdx = 101;
        int minAlpha = 'Z' + 1;
        for (int i = left; i <= right; ++i) {
            if (str[i] < minAlpha) {
                minIdx = i;
                minAlpha = str[i];
            }
        }

        if (minIdx != 101) {
            checked[minIdx] = true;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < checked.length; ++i) {
                if (checked[i]) {
                    sb.append(str[i]);
                }
            }
            System.out.println(sb);

            recur(minIdx + 1, right);
            recur(left, minIdx - 1);
        }
    }

}