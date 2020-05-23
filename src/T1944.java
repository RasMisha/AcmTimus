import java.io.*;
import java.util.*;

public class T1944 {
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
        int minX = 0, maxX = 0, minY = 0, maxY = 0;
        Map<Integer, Set<Integer>> coords = new HashMap<>();

        for (int i=0; i<n; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            minX = Math.min(x,minX); maxX = Math.max(x, maxX);
            minY = Math.min(y,minY); maxY = Math.max(y, maxY);
            if (!coords.containsKey(x)) {
                coords.put(x, new HashSet<>());
            }
            coords.get(x).add(y);
        }

        for (int i = maxY; i>=minY; i--) {
            for (int j=minX; j<=maxX; j++) {
                if (coords.containsKey(j) && coords.get(j).contains(i)) {
                    out.print("*");
                } else {
                    if (i==0 && j==0) {
                        out.print("+");
                    } else if (i==0) {
                        out.print("-");
                    } else if (j==0) {
                        out.print("|");
                    } else {
                        out.print(".");
                    }
                }
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
