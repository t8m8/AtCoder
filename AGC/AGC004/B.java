import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		long x = in.nextLong();
		long[] a = new long[n];
		long[] b = new long[n];
		for (int i=0; i<n; i++) {
			a[i] = b[i] = in.nextLong();
		}

		long ans = Long.MAX_VALUE;
		for (int k=0; k<n; k++) {
			long sum = 0;
			for (int i=0; i<n; i++) {
				if (k != 0)
					b[i] = Math.min(b[i], a[(i-k+n)%n]);
				sum += b[i];
			}
			ans = Math.min(ans, k*x + sum);
		}

		out.println(ans);
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