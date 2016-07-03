import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();

		int[][] g = new int[n][n];
		for (int i=0; i<m; i++) {
			int s = in.nextInt() - 1;
			int t = in.nextInt() - 1;
			g[t][s] = -1;
		}

		long[] dp = new long[1<<n];

		dp[0] = 1;

		for (int i=0; i<1<<n; i++) {
			for (int j=0; j<n; j++) {
				if ((i>>j&1) == 1) continue;
				boolean f = true;
				for (int k=0; k<n; k++) {
					if ((i>>k&1) == 1 && g[k][j] == -1) {
						f = false;
						break;
					}
				}
				if (f) {
					dp[i|(1<<j)] += dp[i];
				}
			}
		}

		dump(dp);

		out.println(dp[(1<<n)-1]);
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