import java.io.*;
import java.util.*;

public class C1352A {
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

    public static void doTask() {
        int q = sc.nextInt();
        while (q-->0) {
            solve();
        }
    }

    static int cnt;
    static int[] values = new int[5];
    static void solve() {
        int n = sc.nextInt();
        int pow = 1;
        cnt = 0;
        while (n>0) {
            int v = n%10;
            if (v!=0) {
                values[cnt++] = v * pow;
            }
            n /= 10;
            pow *= 10;
        }
        out.println(cnt);
        for (int i=0; i<cnt; i++) {
            out.print(values[i] + " ");
        }
        out.println();
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

        String nextLine() {
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

