import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class T1133 {
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
        int i = sc.nextInt();
        long fi = sc.nextInt();
        int j = sc.nextInt();
        long fj = sc.nextInt();
        int n = sc.nextInt();
        if (i > j) {
            int t = i;
            i = j;
            j = t;

            long tv = fi;
            fi = fj;
            fj = tv;
        }

        long nextI = 0;
        if (j-i == 1) {
            nextI = fj;
        } else {
            BigInteger[] fb = new BigInteger[2001];
            fb[0] = BigInteger.ONE;
            fb[1] = BigInteger.ONE;
            for (int c = 2; c <= 2000; c++) {
                fb[c] = fb[c - 1].add(fb[c - 2]);
            }
            BigInteger nextBig = (BigInteger.valueOf(fj).subtract(BigInteger.valueOf(fi).multiply(fb[j - i - 2]))).divide(fb[j - i - 1]);
            nextI = nextBig.longValue();
        }


        long a = fi;
        long b = nextI;
        if (n < i) {
            int steps = i-n;
            while (steps-- >0) {
                long t = b-a;
                b = a;
                a = t;
            }
            out.println(a);
        } else {
            int steps = n-i;
            while (steps-- >0) {
                long t = a+b;
                a = b;
                b = t;
            }
            out.println(a);
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
    }
}
