import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		long[] a = new long[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		long[] dp = new long[n];
		Arrays.fill(dp, Long.MAX_VALUE);
		dp[0] = 0;
		for (int i=0; i<n; i++) {
			if (i+1 < n) dp[i+1] = Math.min(dp[i+1], dp[i] + Math.abs(a[i] - a[i+1]));
			if (i+2 < n) dp[i+2] = Math.min(dp[i+2], dp[i] + Math.abs(a[i] - a[i+2]));
		}
		out.println(dp[n-1]);
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