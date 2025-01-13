import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static Task[] tasks;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tasks = new Task[N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            tasks[i] = new Task(i + 1, T, S);
        }

        Arrays.sort(tasks);

        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.i).append(" ");
        }

        System.out.print(sb);

        br.close();
    }

    static class Task implements Comparable<Task> {

        public int i;
        public int T;
        public int S;

        public Task(int i, int T, int S) {
            this.i = i;
            this.T = T;
            this.S = S;
        }

        @Override
        public int compareTo(Task t) {
            if (this.T * t.S == t.T * this.S) {
                return this.i - t.i;
            }
            return this.T * t.S - t.T * this.S;
        }
    }
}