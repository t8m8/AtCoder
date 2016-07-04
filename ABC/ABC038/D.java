import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int lowerBound(int[] a, int k) {
		int l = -1, r = a.length;
		while (r - l > 1) {
			int m = (l + r)/2;
			if (k <= a[m]) r = m;
			else l = m;
		}
		return r;
	}

	static int lis(int[] a) {
		int n = a.length;
		int len = 1;
		int[] dp = new int[n];
		Arrays.fill(dp, 1<<29);
		dp[0] = a[0];

		for (int i=1; i<n; i++) {
			if (dp[len-1] < a[i]) {
				dp[len++] = a[i];
			}else {
				int idx = lowerBound(dp, a[i]);
				dp[idx] = a[i];
			}
		}
		return len;
	}

	static void solve() {
		int n = in.nextInt();
		int[][] a = new int[n][2];
		for (int i=0; i<n; i++) {
			for (int j=0; j<2; j++) {
				a[i][j] = in.nextInt();
			}
		}

		Arrays.sort(a, (x, y) -> x[0] != y[0] ? (x[0] - y[0]) : (y[1] - x[1]));

		int[] b = new int[n];
		for (int i=0; i<n; i++) {
			b[i] = a[i][1];
		}

		out.println(lis(b));
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