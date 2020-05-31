import java.io.*;
import java.util.*;

public class T1205 {
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

    static class Item implements Comparable<Item>{
        Item(int v, double time) {
            this.v = v;
            this.time = time;
        }
        int v;
        double time;

        static double eps = 0.00000001;
        @Override
        public int compareTo(Item o) {
            if (Math.abs(time-o.time) < eps) return 0;
            if (time > o.time) return 1;
            return -1;
        }
    }

    public static void doTask() {
        double v1 = sc.nextDouble(), v2 = sc.nextDouble();

        int n = sc.nextInt();

        double[][] xy = new double[n + 2][2];
        for (int i = 1; i <= n; i++) {
            xy[i][0] = sc.nextDouble();
            xy[i][1] = sc.nextDouble();
        }

        int[][] g = new int[n + 2][n + 2];
        int u=0, v=0;
        while (true) {
            u = sc.nextInt();
            v = sc.nextInt();
            if (u==0 || v==0) break;
            g[u][v] = 1;
            g[v][u] = 1;
        }

        g[0][n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            g[0][i] = 1;
            g[i][n + 1] = 1;
        }

        xy[0][0] = sc.nextDouble();
        xy[0][1] = sc.nextDouble();

        xy[n + 1][0] = sc.nextDouble();
        xy[n + 1][1] = sc.nextDouble();

        Integer[] from = new Integer[n + 2]; // 0 - stock, n+1 - finish
        double[] best = new double[n + 2]; // 0 - stock, n+1 - finish

        for (int i = 1; i <= n + 1; i++) {
            best[i] = -1;
        }

        PriorityQueue<Item> pq = new PriorityQueue<>();
        pq.add(new Item(0, 0));

        while (pq.size() > 0) {
            Item item = pq.poll();
            double x1 = xy[item.v][0];
            double y1 = xy[item.v][1];
            for (int i = 0; i <= n + 1; i++) {
                if (i != item.v) {
                    double vel = v2;
                    if ((item.v == 0 || item.v == n + 1)
                        || (i == 0 || i == n + 1)
                        || g[item.v][i] == 0) {
                        vel = v1;
                    }
                    double x2 = xy[i][0];
                    double y2 = xy[i][1];

                    double dx = x2-x1;
                    double dy = y2-y1;
                    double s = Math.sqrt(dx*dx + dy*dy);
                    double t = s/vel;

                    if (best[i] == -1 || item.time + t < best[i]) {
                        from[i] = item.v;
                        best[i] = item.time + t;
                        pq.add(new Item(i, best[i]));
                    }
                }
            }
        }
        out.println(best[n+1]);

        int[] way = new int[n+1];
        int size = 0;
        int last = n+1;
        while (last != 0) {
            way[size++] = from[last];
            last = from[last];
        }
        out.print((size-1) + " ");
        for (int i=size-2; i>=0; i--) {
            out.print(way[i] + " ");
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
