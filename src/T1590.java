import java.io.*;
import java.util.*;

public class T1590 {
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
        String s = sc.nextLine();
        int n = s.length();
        int result = 0;
        int[] z = new int[n]; // z-function for reverted string

        for (int len = 1; len<=n; len++) {

            z[len-1] = 0;
            int zmax = 0;
            int r = len-1, l = len-1;
            for (int i=len-2; i>=0; i--) {
                z[i] = 0;
                if (l <= i) {
                    if (z[i+(len-r-1)] <  i - l + 1) {
                        z[i] = z[i+(len-r-1)];
                    } else {
                        z[i] = i - l +1;
                    }
                }

                while (i-z[i] >= 0 && s.charAt(len-z[i]-1) == s.charAt(i-z[i])) {
                    z[i]++;
                }

                if (l > i+1-z[i]) {
                    l = i+1-z[i];
                    r = i;
                }

                zmax = Math.max(zmax, z[i]);
            }

            result += (len-zmax);
        }


        out.println(result);
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

    static class Pair {
        Pair(int cx, int cy) {
            x = cx; y = cy;
        }
        long x,y;
    }
}