import java.io.*;
import java.util.*;

public class T2149 {
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
        String s = sc.nextLine();

        int l = 0, r = 0, l1 = 0, r1 = 0;

        int i = 0;
        int half = n/2;
        while (l+r < n) {
            if (s.charAt(i) == '>') {
                if (l+r>=half) r++;
                else l++;

                if (((l1+r1)&1) == 1) l1++;
                else r1++;
            } else if (s.charAt(i) == '<'){
                if (l+r>=half) l++;
                else r++;

                if (((l1+r1)&1) == 1) r1++;
                else l1++;
            }
            i++;
        }
        out.println(Math.min(Math.min(l,r), Math.min(l1,r1)));
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        Long[] nextLongArr(int n) {
            Long[] r = new Long[n];
            for (int i=0; i<n; i++) {
                r[i] = sc.nextLong();
            }
            return r;
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
