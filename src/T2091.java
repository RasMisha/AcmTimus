import java.io.*;
import java.util.*;

public class T2091 {
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
        int n = sc.nextInt(), m = sc.nextInt();
        long[][] values = new long[m][2];

        for (int i=0; i<n; i++) {
            int idx = 0;
            int shift = i;
            if (i >= 50) {
                shift -= 50;
                idx = 1;
            }
            for (int j=0; j<m; j++) {
                long v = sc.nextLong();
                values[j][idx] = values[j][idx] | (v << shift);
            }
        }

        int max = Integer.MAX_VALUE;
        int ri = -1, rj = -1;

        int curMax = 0;
        for (int i=0; i<m; i++) {
            for (int j=i+1; j<m; j++) {
                curMax = Math.max(Math.max(aAndB(values[i], values[j]), aNotB(values[i], values[j])), Math.max(NotAB(values[i], values[j]), NotANotB(values[i], values[j], n)));
                if (curMax < max) {
                    max = curMax;
                    ri = i+1;
                    rj = j+1;
                }
            }
        }

        out.println(max);
        out.println(ri + " " + rj);
    }

    static int aAndB(long[] a, long[] b) {
        return Long.bitCount(a[0]&b[0]) + Long.bitCount(a[1]&b[1]);
    }

    static int aNotB(long[] a, long[] b) {
        return Long.bitCount((a[0]&b[0])^a[0]) + Long.bitCount((a[1]&b[1])^a[1]);
    }

    static int NotAB(long[] a, long[] b) {
        return Long.bitCount((a[0]&b[0])^b[0]) + Long.bitCount((a[1]&b[1])^b[1]);
    }

    static int NotANotB(long[] a, long[] b, int n) {
        return n - Long.bitCount(a[0] | b[0]) - Long.bitCount(a[1] | b[1]);
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

    }
}
