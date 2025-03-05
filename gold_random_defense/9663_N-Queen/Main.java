import java.util.*;
import java.io.*;

public class Main {
    
    private static int N;
    private static int[] board;
    private static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        
        init();
        
        dfs(0);
        
        System.out.print(answer);
    }
    
    private static void init() throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1];
        
        br.close();  
    }
    
    private static void dfs(int idx) {
        
        if (idx == N) {
            ++answer;
            return;
        }
        
        for (int i = 1; i <= N; ++i) {
            board[idx + 1] = i;
            if (validate(idx + 1)) {
                dfs(idx + 1);
            }
        }
    }
    
    private static boolean validate(int idx) {
        
        for (int i = 1; i < idx; ++i) {
            if (board[i] == board[idx] || Math.abs(i - idx) == Math.abs(board[i] - board[idx])) {
                return false;
            }
        }
        return true;
    }
}