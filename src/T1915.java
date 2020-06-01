import java.io.*;
import java.util.*;

public class T1915 {
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
        int[] a = new int[n*4];
        int x, leftQueries;
        int cnt = 0;
        for (int i=0; i<n; i++) {
            leftQueries = n-i-1;
            x = sc.nextInt();
            if (x > 0) {
                a[cnt++] = x;
            } else if (x==0) {
                if (leftQueries > cnt) {
                    if (cnt >= 0) System.arraycopy(a, 0, a, cnt, cnt);
                    cnt *= 2;
                }
            } else if (x==-1) {
                out.println(a[--cnt]);
            }
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
