import java.io.*;
import java.util.*;

public class T1671 {
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

    static Map<Integer, Set<Integer>> g = new HashMap<>();
    static int[] p;

    public static void doTask(){
        int n = sc.nextInt();
        int m = sc.nextInt();

        p = new int[n+1];
        int[] u = new int[m+1];
        int[] v = new int[m+1];
        for (int i=1; i<=m; i++) {
            u[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        for (int i=1; i<=n; i++) {
            p[i] = i;
        }

        int q = sc.nextInt();
        Set<Integer> qValues = new HashSet<>();
        int[] qv = new int[q];

        for (int i=q-1; i>=0; i--) {
            qv[i] = sc.nextInt();
            qValues.add(qv[i]);
        }

        for (int i=1; i<=n; i++) {
            g.put(i, null);
        }

        for (int i=1; i<=m; i++) {
            if (!qValues.contains(i)) {
                int x = p[u[i]];
                int y = p[v[i]];
                union(x,y);
            }
        }

        int[] result = new int[q];
        int idx = q-1;
        result[idx--] = g.size();

        for (int i=0; i<q-1; i++) {
            union(p[u[qv[i]]], p[v[qv[i]]]);
            result[idx--] = g.size();
        }

        for (int i=0; i<q; i++) {
            out.print(result[i] + " ");
        }
    }

    static int getSize(int rep) {
        return g.get(rep) == null ? 1 : g.get(rep).size();
    }

    static void union(int x, int y) {
        if (x==y) return;

        int xSize = getSize(x);
        int ySize = getSize(y);

        if (xSize > ySize) {
            int t = x;
            x = y;
            y = t;

            t = xSize;
            xSize = ySize;
            ySize = t;
        }

        if (ySize == 1) {
            g.put(y, new HashSet<>());
            g.get(y).add(y);
        }

        Set<Integer> ySet = g.get(y);
        if (xSize == 1) {
            p[x] = y;
            ySet.add(x);
            g.remove(x);
        } else {
            Set<Integer> xSet = g.get(x);
            for (int idx : xSet) {
                p[idx] = y;
                ySet.add(idx);
            }
            g.remove(x);
        }
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


