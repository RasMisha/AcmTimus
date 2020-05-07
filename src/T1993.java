import java.io.*;
import java.util.*;

public class T1993 {
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

    public static void doTask() {
        String s = sc.nextLine().toLowerCase();
        int commaIdx = s.indexOf(',');
        String res;
        if (commaIdx >= 0) {
            res = reorder(s.substring(0, commaIdx))  + "," + reorder(s.substring(commaIdx+1));
        } else {
            res = reorder(s);
        }

        res = res.substring(0,1).toUpperCase() + res.substring(1);
        out.println(res);
    }

    static String reorder(String s) {
        int firstStart = s.indexOf('{');
        int firstEnd = s.indexOf('}');
        int secondStart = s.indexOf('(');
        int secondEnd = s.indexOf(')');
        int thirdStart = s.indexOf('[');
        int thirdEnd = s.indexOf(']');

        int prefixEnd = Math.min(firstStart, Math.min(secondStart, thirdStart));
        String prefix = "";
        if (prefixEnd > 0) {
            prefix = s.substring(0, prefixEnd);
        }

        String[] lines = {s.substring(firstStart+1, firstEnd), s.substring(secondStart+1, secondEnd), s.substring(thirdStart+1, thirdEnd)};
        return prefix + String.join(" ", lines);
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
