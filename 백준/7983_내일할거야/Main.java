import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static Task[] tasks;

    public static void main(String[] args) throws IOException {

        init();
        
        Arrays.sort(tasks, (o1, o2) -> o2.t - o1.t);

        int deadline = 1000000000;
        for (Task task : tasks) {
            deadline = Math.min(deadline, task.t) - task.d;
        }

        System.out.print(deadline);
    }

    private static void init() throws IOException  {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tasks = new Task[n];
        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            tasks[i] = new Task(d, t);
        }

        br.close();
    }

    static class Task {

        int d;
        int t;

        public Task(int d, int t) {
            this.d = d;
            this.t = t;
        }
    }
}