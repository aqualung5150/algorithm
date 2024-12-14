import java.util.*;
import java.io.*;

public class Main {

    private static List<String> opSet;
    private static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //테스트케이스
        while (true) {

            String op = br.readLine();

            //공백
            if (op.length() == 0) {
                System.out.println();
                op = br.readLine();
            }

            //프로그램 종료
            if (op.equals("QUIT")) {
                br.close();
                return;
            }

            opSet = new ArrayList<>();

            //명령어
            while (!op.equals("END")) {
                opSet.add(op);
                op = br.readLine();
            }

            //명령어 실행 - TODO
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; ++i) {
                doProgram(Integer.parseInt(br.readLine()));
            }

            //TEST
            // for (String o : opSet) {
            //     System.out.print(o + " ");
            // }
            // System.out.println();
        }
    }

    /*
    NUM X: X를 스택의 가장 위에 저장한다. (0 ≤ X ≤ 109)
    POP: 스택 가장 위의 숫자를 제거한다.
    INV: 첫 번째 수의 부호를 바꾼다. (42 -> -42)
    DUP: 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
    SWP: 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
    ADD: 첫 번째 숫자와 두 번째 숫자를 더한다.
    SUB: 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
    MUL: 첫 번째 숫자와 두 번째 숫자를 곱한다.
    DIV: 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
    MOD: 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
    */
    private static void doProgram(int seed) {
        stack = new Stack<>();
        stack.push(seed);

        for (String op : opSet) {
        
            if (op.equals("POP")) {
                if (stack.isEmpty()) {
                    System.out.println("ERROR");
                    return;
                }
                stack.pop();

            } else if (op.equals("INV")) {
                if (stack.isEmpty()) {
                    System.out.println("ERROR");
                    return;
                }
                stack.push(-stack.pop());

            } else if (op.equals("DUP")) {
                if (stack.isEmpty()) {
                    System.out.println("ERROR");
                    return;
                }
                stack.push(stack.peek());

            } else if (op.equals("SWP")) {
                if (stack.size() < 2) {
                    System.out.println("ERROR");
                    return;
                }
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a);
                stack.push(b);

            } else if (op.equals("ADD")) {
                if (stack.size() < 2) {
                    System.out.println("ERROR");
                    return;
                }
                int a = stack.pop();
                int b = stack.pop();
                if (Math.abs(b + a) > 1000000000) {
                    System.out.println("ERROR");
                    return;
                }
                stack.push(b + a);

            } else if (op.equals("SUB")) {
                if (stack.size() < 2) {
                    System.out.println("ERROR");
                    return;
                }
                int a = stack.pop();
                int b = stack.pop();
                if (Math.abs(b - a) > 1000000000) {
                    System.out.println("ERROR");
                    return;
                }
                stack.push(b - a);

            } else if (op.equals("MUL")) {
                if (stack.size() < 2) {
                    System.out.println("ERROR");
                    return;
                }
                long a = stack.pop();
                long b = stack.pop();
                // System.out.println(b * a);
                if (Math.abs(b * a) > 1000000000) {
                    System.out.println("ERROR");
                    return;
                }
                stack.push((int) b *  (int) a);

            } else if (op.equals("DIV")) {
                if (stack.size() < 2) {
                    System.out.println("ERROR");
                    return;
                }
                int a = stack.pop();
                int b = stack.pop();
                if (a == 0) {
                    System.out.println("ERROR");
                    return;
                }

                stack.push(b / a);

                // int result = b / a;
                // if (b < 0 && a < 0) {
                //     result = -result;
                // }
                // stack.push(result);

            } else if (op.equals("MOD")) {
                if (stack.size() < 2) {
                    System.out.println("ERROR");
                    return;
                }
                int a = stack.pop();
                int b = stack.pop();
                if (a == 0) {
                    System.out.println("ERROR");
                    return;
                }

                int result = b % a;
                if (b < 0 && result > 0) {
                    result = -result;
                }

                stack.push(result);

            } else if (op.substring(0, 3).equals("NUM")) {
                stack.push(Integer.parseInt(op.substring(4)));

            }
        }

        if (stack.size() != 1) {
            System.out.println("ERROR");
            return;
        }

        System.out.println(stack.pop());
    }
}