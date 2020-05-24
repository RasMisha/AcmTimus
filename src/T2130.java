import java.io.*;
import java.util.*;

public class T2130 {
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

    static long gcd(long a,long b) {
        if (b==0) return a;
        return gcd(b, a%b);
    }

    public static void doTask(){
        int n = sc.nextInt();
        int response;
        long a;

        long lcm = 1;

        boolean canBe = true;
        Set<Long> before = new HashSet<>();
        for (int i=0; i<n; i++) {
            if (canBe) {
                a = sc.nextLong();
                response = sc.nextInt();
                if (response == 1) {
                    long prev_lcm = lcm;
                    lcm = (a / gcd(a, lcm) * lcm);
                    boolean ok = true;
                    if (lcm!=prev_lcm) {
                        for (long b : before) {
                            if (lcm%b==0) {
                                ok = false;
                                break;
                            }
                        }
                    }
                    if (lcm <= 1_000_000_000_000L && ok) {
                        out.println(lcm);
                    } else {
                        out.println(-1);
                        canBe = false;
                    }
                } else {
                    if (lcm%a==0) {
                        out.println(-1);
                        canBe = false;
                    } else {
                        before.add(a);
                        out.println(lcm);
                    }
                }
            } else {
                out.println(-1);
            }
        }
    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() throws FileNotFoundException {
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\Dell\\Downloads\\t1007.in"))));
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
