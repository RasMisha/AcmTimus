import java.io.*;
import java.util.*;

public class T1987 {
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

    static int LEFT = 0, RIGHT = 2, QUERY = 1;
    static class SegmentEnd implements Comparable<SegmentEnd> {
        SegmentEnd(int x, int type, int index) {
            this.x = x;
            this.type = type;
            this.index = index;
        }
        int x;
        int type;
        int index;
        int answer = -1;

        @Override
        public int compareTo(SegmentEnd o) {
            if (x!=o.x) return x-o.x;
            return type - o.type;
        }
    }

    static String typeToString(int type) {
        if (type == LEFT) return "LEFT";
        if (type == RIGHT) return "RIGHT";
        return "QUERY";
    }

    public static void doTask(){
        int n = sc.nextInt();

        List<SegmentEnd> p = new ArrayList<>();

        for (int i=0; i<n; i++) {
            p.add(new SegmentEnd(sc.nextInt(), LEFT, i+1));
            p.add(new SegmentEnd(sc.nextInt(), RIGHT, i+1));
        }

        int m = sc.nextInt();
        SegmentEnd[] result = new SegmentEnd[m];
        for (int i=0; i<m; i++) {
            result[i] = new SegmentEnd(sc.nextInt(), QUERY, i+1);
            p.add(result[i]);
        }

        Collections.sort(p); // important stable sort and conditions from task (order of segments) -
//        for (SegmentEnd pt : p) {
//            out.print("["+pt.x+", " + typeToString(pt.type)+", " + (pt.index+1) + "] ");
//        }
//        out.println();

        int balance = 0;
        int[] stack = new int[n];

        for (int i=0; i<p.size(); i++) {
            SegmentEnd pt = p.get(i);
            if (balance > 0 && i > 0 && pt.type == QUERY) {
                pt.answer = stack[balance-1];
            }
            if (pt.type == LEFT) stack[balance++] = pt.index;
            if (pt.type == RIGHT) balance--;
        }

        for (int i=0; i<m; i++) {
            out.println(result[i].answer);
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
