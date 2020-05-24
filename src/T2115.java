import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class T2115 {
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
        int[] a = new int[n],b = new int[n];
        Integer[] c = new Integer[n];

        for (int i=0; i<n; i++) {
            a[i] = b[n-1-i] = c[i] = sc.nextInt();
        }
        Arrays.sort(c);

        int[] diffA = diffCount(a,c);
        int[] diffB = diffCount(b,c);

        if (diffA[2] > 2 && diffB[2] > 2) {
            out.println("No hope");
        } else if (diffA[2] == 0 || diffB[2] == 0) {
            out.println("Nothing to do here");
        } else {
            out.println("Yes");
            if (diffA[2] == 2) {
                out.println(diffA[0] + " " + diffA[1]);
            } else {
                out.println((n+1-diffB[0]) + " " + (n+1-diffB[1]));
            }
        }
    }

    static int[] diffCount(int[] a, Integer[] b) {
        int[] res = new int[3];
        int n = a.length;
        for (int i=0; i<n; i++) {
            if (a[i] != b[i]) {
                res[2]++;
                if (res[2] > 2) {
                    return res;
                } else {
                    res[res[2]-1] = i+1;
                }
            }
        }
        return res;
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

    }
}
