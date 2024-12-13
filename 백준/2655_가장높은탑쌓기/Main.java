import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static Block[] blocks;
    private static int[] heights;

    public static void main(String[] args) throws IOException {

        init();

        Arrays.sort(blocks, (o1, o2) -> o2.base - o1.base);

        int maxHeight = 0;
        int maxHeightIdx = -1;
        for (int i = 0; i < N; ++i) {
            heights[i] = blocks[i].height;
            for (int j = 0; j < i; ++j) {
                if (blocks[j].weight > blocks[i].weight) {
                    if (heights[i] < heights[j] + blocks[i].height) {
                        heights[i] = heights[j] + blocks[i].height;
                    }
                }
            }
            if (heights[i] > maxHeight) {
                maxHeight = heights[i];
                maxHeightIdx = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = maxHeightIdx; i >= 0; --i) {
            if (maxHeight == heights[i]) {
                maxHeight -= blocks[i].height;
                result.add(blocks[i].n);
            }
        }

        System.out.println(result.size());
        for (Integer b : result) {
            System.out.println(b);
        }
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        blocks = new Block[N];
        heights = new int[N];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            blocks[i] = new Block(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();
    }

    static class Block {
        int n;
        int base;
        int height;
        int weight;

        public Block(int n, int base, int height, int weight) {
            this.n = n;
            this.base = base;
            this.height = height;
            this.weight = weight;
        }
    }
}