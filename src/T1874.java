import java.io.*;
import java.util.*;

public class T1874 {
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
        double a = sc.nextDouble();
        double b = sc.nextDouble();


        double la = 0, ra = 180;
        double maxS = 0;
        for (double k = 0; k < 100; k++) {
            double diff = (ra-la)/3;
            double alpha1 = la+diff;
            double alpha2 = ra-diff;

            double s1 = betaTernary(a,b, alpha1);
            double s2 = betaTernary(a,b, alpha2);

            if (s1>s2) {
                ra = alpha2;
            } else {
                la = alpha1;
            }
            maxS = s2;
        }
        out.printf("%.6f", maxS);
    }

    static double betaTernary(double a, double b, double alpha) {
        double l = 0;
        double r = 180;
        for (int i=0; i<100; i++) {
            double diff = (r-l)/3;
            double beta1 = l+diff;
            double beta2 = r-diff;
            double s1 = s(a,b,alpha, beta1);
            double s2 = s(a,b,alpha, beta2);

            if (s1 > s2) {
                r = beta2;
            } else {
                l = beta1;
            }
        }
        return s(a,b,alpha, r);
    }

    public static double s(double a, double b, double alphaDeg, double betaDeg) {
        double alpha = Math.toRadians(alphaDeg), beta = Math.toRadians(betaDeg);
        double x = Math.sin(alpha)*a;
        double y = Math.sin(beta)*b;

        double s1 = a*x*Math.cos(alpha)/2;
        double s2 = b*y*Math.cos(beta)/2;

        return x*y + s1 + s2;
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
