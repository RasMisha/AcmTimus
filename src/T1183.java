import java.io.*;
import java.util.*;

public class T1183 {
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
        char[] s = sc.nextLine().toCharArray();
        if (s.length == 0) {
            out.println();
            return;
        }
        int n = s.length;

        int[][] dp = new int[n+1][n+1];

        for (int len=1; len<=n; len++) {
            for (int i=0; i<n; i++) {
                if (len == 1) {
                    dp[i + 1][i + len] = 1;
                } else {
                    if (i+len <= n) {
                        char l = s[i];
                        char r = s[i + len - 1];

                        dp[i+1][i+len] = Integer.MAX_VALUE;
                        if (exact(l,r)) {
                            dp[i+1][i + len] = i+len-2 == 0 ? 0 : dp[i+2][i+len-1];
                        }

                        for (int left = 1; left < len; left++) {
                            int right = len - left;
                            dp[i+1][i+len] = Math.min(dp[i+1][i+len], dp[i+1][i+left] + dp[i+left+1][i+left+right]);
                        }
                    }
                }
            }
        }


        char[] result = null;
        if (dp[1][n]==0) {
            result = s;
        } else {
            result = new char[n+dp[1][n]];
            restoreAnswer(result, 0, n, 0, n+dp[1][n]-1, dp, s);
        }

        out.println(new String(result));
    }

    static void restoreAnswer(char[] result, int l, int r, int first, int last, int[][] dp, char[] s) {
        if (dp[l+1][r] == 0) {
            for (int i=l; i<r; i++) {
                result[first+(i-l)] = s[i];
            }
            return;
        }
        if (dp[l+1][r] ==  1 && r-l == 1) {
            if (s[l] == '(' || s[l] == '[') {
                result[first] = s[l];
                result[last] = s[l] == '(' ? ')' : ']';
            } else {
                result[first] = s[l] == ')' ? '(' : '[';
                result[last] = s[l];
            }
            return;
        }
        if (exact(s[l], s[r-1]) && (r-l == 1 || (l+2 <= dp.length && dp[l+1][r] == dp[l+2][r-1]))) {
            result[first] = s[l];
            result[last] = s[r-1];
            if (r-l>1) restoreAnswer(result, l+1,r-1, first+1, last-1, dp, s);
        } else {
            for (int left = 1; left < r-l; left++) {
                int right = r - l - left;
                if (dp[l+1][r] == dp[l+1][l+left] + dp[l+left+1][l+left+right]) {
                    restoreAnswer(result, l, l+left, first, first+left+dp[l+1][l+left]-1, dp, s);
                    restoreAnswer(result, l+left, l+left+right, first+left+dp[l+1][l+left], last, dp, s);
                    break;
                }
            }
        }
    }

    static boolean exact(char l, char r) {
        return (l == '(' && r == ')') || (l == '[' && r == ']');
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
