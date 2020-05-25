import java.io.*;
import java.util.*;

public class T1613 {
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

    static Map<Integer, List<Integer>> info = new HashMap<>();

    public static void doTask(){
        int n = sc.nextInt();
        for (int i=0; i<n;i++) {
            int p = sc.nextInt();
            if (!info.containsKey(p)) {
                info.put(p, new ArrayList<>());
            }
            info.get(p).add(i+1);
        }

        int q = sc.nextInt();

        int l,r,qp;
        while (q-->0) {
            l = sc.nextInt();
            r = sc.nextInt();
            qp = sc.nextInt();

            if (info.containsKey(qp)) {
                int nearest = find(info.get(qp), l);
                out.print(nearest < info.get(qp).size() && info.get(qp).get(nearest) <= r ? 1 : 0);
            } else {
                out.print(0);
            }
        }
    }

    static int find(List<Integer> a, int x) {
        int l = -1;
        int r = a.size();

        while (r-l>1) {
            int m = l + (r-l)/2;
            if (a.get(m) < x) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(System.in));
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

    }
}
