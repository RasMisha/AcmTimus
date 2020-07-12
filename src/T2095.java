import java.io.*;
import java.util.*;

public class T2095 {
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static MyScanner sc;
    static double EPS = 0.00000001;

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
        int l = sc.nextInt();
        int r = sc.nextInt();
        out.println(vacations(r) - vacations(l-1));
    }

    static int vacations(int r) {
        int interval = 2;
        while (r/interval > 0) {
            r = r - r/interval;
            interval++;
        }
        return r;
    }

    static long pow(long a, int p) { if (p == 1) return a;long h = pow(a, p/2);long r = h*h;if (p%2 == 1) r*=a;return r; }
    static long gcd(long a, long b) { if (b==0) return a;return gcd(b, a%b); }

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
