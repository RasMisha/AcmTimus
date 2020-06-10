import java.io.*;
import java.util.*;

public class T2070 {
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
        long l = sc.nextLong(), r = sc.nextLong();

        long result = 0;
        List<Long> primes = new ArrayList<>(10000);
        primes.add(2L);
        primes.add(3L);

        for (long i = 5; i*i<=r || primes.size() < 100; i+=2) {
            boolean isPrime = true;
            for (int j=0; primes.get(j)*primes.get(j)<=i; j++) {
                if (i%primes.get(j)==0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }

        }

        for (int i=0; i< primes.size() && primes.get(i)*primes.get(i) <= r; i++) {
            for (int j=1;;j++) {
                int pow = (int)(primes.get(j)-1);
                long number = fastPow(primes.get(i), pow);
                if (number > r) break;
                else if (number >= l) result++;
            }
        }
        out.println(r-l+1-result);
    }

    static long fastPow(long a, int pow) {
        if (pow == 1) return a;
        long half = fastPow(a, pow/2);
        long result = half*half;
        if (pow%2 == 1) result*=a;
        return result;
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

        long nextLong() {
            return Long.parseLong(next());
        }

    }
}
