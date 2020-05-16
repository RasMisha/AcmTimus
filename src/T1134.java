import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class T1134 {
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
        out.println(solve());
    }

    static String solve() {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] vs = new int[n+1];
        int[] numbers = new int[m];
        boolean[] used = new boolean[n+1];

        for (int i=0; i<m; i++) {
            numbers[i] = sc.nextInt();
            if (numbers[i]>n) return "NO";
            vs[numbers[i]]++;
        }

        Arrays.sort(numbers);

        if (n==1) {
            if (m==0) {
                return "YES";
            } else if (m==1 && numbers[0] < 2) {
                return "YES";
            } else {
                return "NO";
            }
        }

        if (vs[0] > 1 || vs[n] > 1) {
            return "NO";
        }

        for (int i=0; i<m; i++) {
            if (!used[numbers[i]]) {
                if (numbers[i] == 0) {
                    used[0] = used[1] = true;
                } else if (numbers[i] == n) {
                    used[n] = used[n-1] = true;
                } else {
                    used[numbers[i]] = true;
                }
            } else {
                if (numbers[i] == n || used[numbers[i]+1]) {
                    return "NO";
                } else {
                    used[numbers[i]+1] = true;
                }
            }
        }
        return "YES";
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
