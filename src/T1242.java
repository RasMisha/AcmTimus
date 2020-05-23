import java.io.*;
import java.util.*;

public class T1242 {
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
        int n = sc.nextInt();
        Set<Integer> possible = new HashSet<>();

        Map<Integer, Set<Integer>> g = new HashMap<>();
        Map<Integer, Set<Integer>> ancestors = new HashMap<>();
        for (int i=1; i<=n; i++) {
            g.put(i, new HashSet<>());
            ancestors.put(i, new HashSet<>());
            possible.add(i);
        }

        while (true) {
            int u = sc.nextInt();
            if (u==-1) break;
            int v = sc.nextInt();
            g.get(u).add(v);
        }


        for (int i=1; i<=n; i++) {
            Queue<Integer> values = new LinkedList<>();

            values.add(i);
            while (values.size() > 0) {
                int cur = values.poll();

                for (int parent : g.get(cur)) {
                    if (!ancestors.get(i).contains(parent)) {
                        ancestors.get(i).add(parent);
                        values.add(parent);
                    }
                }
            }
        }

        try {
            while (true) {
                int victim = sc.nextInt();
                possible.remove(victim);
                possible.removeAll(ancestors.get(victim));

                Set<Integer> toRemove = new HashSet<>();
                for (int p : possible) {
                    if (ancestors.get(p).contains(victim)) {
                        toRemove.add(p);
                    }
                }
                possible.removeAll(toRemove);
            }
        } catch (Exception ex) {}
        if (possible.size() == 0) {
            out.println(0);
        } else {
            for (int i = 1; i <= n; i++) {
                if (possible.contains(i)) out.print(i + " ");
            }
            out.println();
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
            String st = next();
            if (!st.equals("BLOOD")) {
                return Integer.parseInt(st);
            } return -1;
        }

    }
}
