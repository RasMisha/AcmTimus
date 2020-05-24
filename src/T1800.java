import java.io.*;
import java.util.*;

public class T1800 {
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
        double l = sc.nextDouble()/100, h = sc.nextDouble()/100, w = sc.nextDouble();
        double g = 9.81;

        int BUTTER_STATE = 0;
        int BREAD_STATE = 1;

        int state = BUTTER_STATE;

        double halfRotationTime = 60.0/w/2.0;
        double quarterRotationTime = halfRotationTime/2.0;

        int seconds = 0;
        double first = g*quarterRotationTime*quarterRotationTime/2.0;
        double height = first;
        while (true) {
            if (h-height<l/2) break;
            state = (state+1)&1; // swap state
            seconds++;
            double t = halfRotationTime*seconds + (halfRotationTime/2.0);
            height = g*t*t/2.0;
        }

        out.println(state == BUTTER_STATE ? "butter" : "bread");
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

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
