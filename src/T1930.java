import java.io.*;
import java.util.*;

public class T1930 {
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

    static Map<Integer, Map<Integer, Integer>> g = new HashMap<>();

    public static void doTask(){
        int n = sc.nextInt();
        int m = sc.nextInt();
        Map<Integer, Integer> edge;
        for (int i=0; i<m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            edge = g.getOrDefault(u, new HashMap<>());
            edge.put(v, 1);
            g.put(u, edge);

            edge = g.getOrDefault(v, new HashMap<>());
            edge.put(u, -1);
            g.put(v, edge);
        }
        int is = sc.nextInt(), st = sc.nextInt();

        int[] cost = new int[n+1];
        for (int i=0; i<=n; i++) cost[i] = Integer.MAX_VALUE;

        Queue<State> ways = new LinkedList<>();
        ways.add(new State(is, 0,0));
        while (ways.size() > 0) {
            State s = ways.poll();
            if (g.containsKey(s.v)) {
                for (int v : g.get(s.v).keySet()) {
                    int state = g.get(s.v).get(v);
                    int changes = s.changes;
                    if (s.state != 0 && s.state != state) {
                        changes++;
                    }
                    if (cost[v] > changes) {
                        cost[v] = changes;
                        ways.add(new State(v, state, changes));
                    }
                }
            }
        }

        out.println(cost[st]);
    }

    static class State {
        State(int v, int s, int c) {
            this.v = v;
            this.state = s;
            this.changes = c;
        }
        int v;
        int state;
        int changes;
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
