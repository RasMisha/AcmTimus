import java.io.*;
import java.util.*;

public class C113B {
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
        String a = sc.nextLine();
        String b = sc.nextLine();

        String rb = revert(b);
        String rs = revert(s);

        int sl = s.length();
        int al = a.length();
        int bl = b.length();

        int[] minSizes = new int[sl];
        for (int i=1; i<=sl; i++) {
            minSizes[i-1] = max(z(rs.substring(sl-i),i)) + 1;
        }


        String sa = a+'#'+s;
        String bs = rb+'#'+rs;

        int[] za = z(sa);
        int[] zb = z(bs);

        int[] cntA = count(za, al);
        int[] cntB = count(zb, bl);

        int l = 0, r = sl-1;
        while (l<r) {
            int t = cntB[l];
            cntB[l] = cntB[r];
            cntB[r] = t;
            l++; r--;
        }

        int[] prefixA = new int[sl];
        prefixA[0] = cntA[0];
        for (int i=1; i<sl; i++) {
            prefixA[i] = prefixA[i-1]+cntA[i];
        }


        long result = 0;
        int minLen = Math.max(al, bl);

        for (int i=0; i<sl; i++) {
            int minSize = Math.max(minLen, minSizes[i]);
            if (i+1 >= minSize) {
                int first = prefixA[i-minSize+1];
                int second = cntB[i];
                result += first*second;
            }
        }

        out.println(result);
    }

    static int max(int[] z) {
        int res = 0;
        for (int i=0; i<z.length; i++) {
            if (res < z[i]) {
                res = z[i];
            }
        }
        return res;
    }

    static int[] count(int[] z, int size) {
        int[] res = new int[z.length - size - 1];
        for (int i=0; i<z.length - size - 1; i++) {
            res[i] = z[i+size+1]==size ? 1 : 0;
        }
        return res;
    }

    static String revert(String s) {
        int n = s.length();
        char[] c = new char[n];
        for (int i=0; i<n; i++) {
            c[i] = s.charAt(n-i-1);
        }
        return new String(c);
    }

    static int[] z(String s) {
        return z(s,null);
    }

    static int[] z(String s, Integer number) {
        int n = number == null ? s.length() : number;
        int[] z = new int[n];
        for (int i=1, l=0, r=0; i<n; ++i) {
            if (i <= r)
                z[i] = Math.min(r-i+1, z[i-l]);
            while (i+z[i] < n && s.charAt(z[i]) == s.charAt(i+z[i]))
                ++z[i];
            if (i+z[i]-1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
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