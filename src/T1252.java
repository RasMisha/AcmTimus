import java.io.*;
import java.util.*;

public class T1252 {
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

    static boolean reverse = false;
    static class Stone implements Comparable<Stone> {
        Stone(int w, int i) {
            weight = w;
            index = i;
        }
        int weight, index;
        @Override
        public int compareTo(Stone o) {
            return reverse
                    ? o.weight - weight
                    : weight - o.weight;
        }
    }

    static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b, a%b);
    }

    public static void doTask(){
        int n = sc.nextInt();
        Stone[] stones = new Stone[n];

        for (int i=0; i<n; i++) {
            stones[i] = new Stone(sc.nextInt(), i);
        }
        Arrays.sort(stones);

        int cnt = 0;
        int[] v = new int[n];
        for (int i=0; i<n; i++) {
            if (i != stones[i].index) {
                v[cnt++] = Math.abs(i - stones[i].index);
            }
        }

        reverse = true;
        Arrays.sort(stones);

        int cnt2 = 0;
        int[] v2 = new int[n];
        for (int i=0; i<n; i++) {
            if (i != stones[i].index) {
                v2[cnt2++] = Math.abs(i - stones[i].index);
            }
        }

        if (cnt < 2 || cnt2 < 2) {
            out.println(n-1);
        } else {
            int result = maxK(v, cnt);
            int result2= maxK(v2, cnt2);
            out.println(Math.max(result, result2));
        }
    }

    static int maxK(int[] v, int cnt) {
        int result = 0;
        for (int i = 0; i < cnt; i++) {
            result = gcd(v[i], result);
        }
        return result-1;
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
