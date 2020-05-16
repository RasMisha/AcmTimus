import java.io.*;
import java.util.*;

public class T1878 {
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static MyScanner sc;

    static {
        try {
            sc = new MyScanner();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        doTask();
        out.flush();
    }

    public static void doTask(){
        int[][] cube = new int[4][4];

        for (int i=0; i<4; i++) {
            for (int j=0; j<4;j++) {
                cube[i][j] = sc.nextInt();
            }
        }

        int min = Integer.MAX_VALUE;
        for (int c=1; c<=4; c++) { // color in top left square

            int current = 0;
            for (int i=0; i<2; i++) {
                for (int j = 0; j<2; j++) {
                    current += steps(cube[i][j], c);
                }
            }

            min = Math.min(min, current);
        }
        out.println(min);
    }

    static int steps(int from, int to) {
        if (from == to) return 0;
        if (Math.abs(from-to) == 2) return 2;
        return 1;
    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() throws FileNotFoundException {
            br = System.getProperty("ONLINE_JUDGE") != null
                    ? new BufferedReader(new InputStreamReader(System.in))
                    : new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt"))));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}
