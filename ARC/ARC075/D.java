import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

    static boolean f(int[] h, int a, int b, int k) {
        int n = h.length;
        int d = a - b;
        long p = 0, q = 0;
        for (int i=0; i<n; i++) {
            long x = h[i] - (long)b*k;
            if (x > 0) {
                p += x;
                q += ((x-1) / d + 1)*d - x;
            }
        }
        return p <= (long)k*d - q;
    }

	static void solve() {
		int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] h = new int[n];
        for (int i=0; i<n; i++) {
            h[i] = in.nextInt();
        }
        Arrays.sort(h);
        int l = -1, r = 1_000_000_001;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (f(h, a, b, m)) r = m;
            else l = m;
        }

        for (int i=Math.max(0, l-3); i<Math.min(1_000_000_001, r+3); i++) {
            if (f(h, a, b, i)) {
                out.println(i);
                return;
            }
        }
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		long start = System.nanoTime();

		solve();
		out.flush();

		long end = System.nanoTime();
		dump((end - start) / 1000000 + " ms");
		in.close();
		out.close();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}