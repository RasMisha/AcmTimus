import java.io.*;
import java.util.*;

public class T1102 {
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


//    Одна сущность по имени "one" беседует со своим другом, сущностью "puton", и нас интересует их разговор.
//    "One" может говорить слова "out" и "output", кроме того, он может называть своего друга по имени. "Puton"
//    может говорить слова "in", "input" и "one". Они прекрасно понимают друг друга и даже пишут диалоги в строки
//    без пробелов между словами.

// one out output in input puton

    public static void doTask(){
        int q = sc.nextInt();
        while (q-->0) {
            out.println(solve());
        }
    }

    static String[] words = new String[]{"in", "one", "out", "input", "puton", "output"};
    static int[] heap = new int[100];
    static int heapSize = 0;

    static void addToHeap(int v) {
        heap[heapSize] = v;

        int idx = heapSize;
        while (idx > 0 && heap[(idx+1)/2] > v) {
            int nextIdx = (idx+1)/2;
            int t = heap[idx];
            heap[idx] = heap[nextIdx];
            heap[nextIdx] = heap[idx];
            idx = nextIdx;
        }

        heapSize++;
    }

    static int poll() {
        int result = heap[0];

        int v = heap[heapSize-1];
        int curIdx = 0;
        heap[curIdx] = v;
        heapSize--;

        while (curIdx < heapSize) {
            int left = curIdx*2 + 1;
            int right = curIdx*2 + 2;

            int j = left;
            if (right < heapSize && heap[right] < heap[left]) {
                j = right;
            }

            if (heap[curIdx] <= heap[j]) {
                break;
            }

            int t = heap[curIdx];
            heap[curIdx] = heap[j];
            heap[j] = t;

            curIdx = j;
        }

        return result;
    }

    static void clearHeap() {
        heapSize = 0;
    }

    static String solve() {
        String s = sc.nextLine();
        int n = s.length();

        clearHeap();
        addToHeap(-1);

        while (heapSize > 0) {
            int idx = poll() + 1;
            if (idx == n) return "YES";
            for (int k=0; k<words.length; k++) {
                String w = words[k];
                if (idx + w.length() <= n) {
                    boolean can = true;
                    for (int i=0; i<w.length(); i++) {
                        if (w.charAt(i) != s.charAt(idx+i)) {
                            can = false;
                            break;
                        }
                    }
                    if (can) {
                        addToHeap(idx+w.length()-1);
                    }
                }
            }
        }
        return "NO";
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

        char nextChar() {
            try {
                char next = (char) br.read();
                return next;
            } catch (IOException e) {
            }
            return '\000';
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
