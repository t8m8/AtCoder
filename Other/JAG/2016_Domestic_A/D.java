import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static final int INF = Integer.MIN_VALUE;

	static int n, m;
	static int[] a, b;
	static int[][][][][] dp;


	static int val(int i, int j, int k, int v) {
		int aval = 0, bval = 0;
		boolean af = true, bf = true;

		int ap = 1, bp = 1;

		for (int p=0; p<v; p++) {
			if (k == 1) {
				if (a[i-ap] == -1) {
					bf = false;
				} else if (af) {
					aval += a[i-ap];
				}
				ap++;
			} else {
				if (b[j-bp] == -1) {
					af = false;
				} else if (bf) {
					bval += b[j-bp];
				}
				bp++;
			}

			k = (k+1)%2;
		}

		return aval - bval;
	}
	
	static int rec(int i, int j, int k, int l, int v) {
		if (dp[i][j][k][l][v] != INF) return dp[i][j][k][l][v];

		int res;

		if (k == 0) {
			res = Integer.MIN_VALUE/2;

			if (i != n) {
				res = Math.max(res, rec(i+1, j, (k+1)%2, 0, v+1));
			}
			if (l == 0) {
				int val = val(i, j, k, v);
				int p = v == 0 ? 1 : 0;
				res = Math.max(res, rec(i, j, (k+1)%2, p, 0) + val);
			}
			if (l == 1) {
				res = Math.max(res, 0);
			}
		}

		else {
			res = Integer.MAX_VALUE/2;
			if (j != m) {
				res = Math.min(res, rec(i, j+1, (k+1)%2, 0, v+1));
			}
			if (l == 0) {
				int val = val(i, j, k, v);
				int p = v == 0 ? 1 : 0;
				res = Math.min(res, rec(i, j, (k+1)%2, p, 0) + val);
			}
			if (l == 1) {
				res = Math.min(res, 0);
			}
		}

		return dp[i][j][k][l][v] = res;
	}

	static void solve() {
		n = in.nextInt();
		m = in.nextInt();

		a = new int[n];
		b = new int[m];

		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		for (int i=0; i<m; i++) {
			b[i] = in.nextInt();
		}

		dp = new int[n+1][m+1][2][2][n+m+1];

		for (int i=0; i<=n; i++) {
			for (int j=0; j<=m; j++) {
				for (int k=0; k<2; k++) {
					for (int l=0; l<2; l++) {
						for (int x=0; x<=n+m; x++) {
							dp[i][j][k][l][x] = INF;
						}
					}
				}
			}
		}

		out.println(rec(0, 0, 0, 0, 0));
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		long start = System.currentTimeMillis();

		solve();
		out.flush();

		long end = System.currentTimeMillis();
		dump((end-start) + "ms");
		in.close();
		out.close();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}