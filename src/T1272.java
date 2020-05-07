import java.io.*;
import java.util.*;

public class T1272 {
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

    static int[] p;
    static int[] size;

    public static void doTask(){
        int n = sc.nextInt();

        p = new int[n+1];
        size = new int[n+1];

        for (int i=1; i<=n; i++) {
            p[i] = i;
            size[i] = 1;
        }

        int k = sc.nextInt(), m = sc.nextInt();

        int x,y, u,v;
        for (int i=0; i<k; i++) {
            u = sc.nextInt();
            v = sc.nextInt();

            x = find(u);
            y = find(v);

            if (x!=y) {
                union(x, y);
            }
        }


        int result = m;
        for (int i=0; i<m; i++) {
            u = sc.nextInt();
            v = sc.nextInt();

            x = find(u);
            y = find(v);

            if (x==y) {
                result--;
            } else {
                union(x,y);
            }
        }

        out.print(result);
    }

    static void union(int x, int y) {
        if (size[x] > size[y]) {
            int t = x;
            x = y;
            y = t;
        }

        p[x] = y;
        size[y] += size[x];
    }

    static int find(int x) {
        while (p[x] != x) {
            x = p[x];
        }
        return x;
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
