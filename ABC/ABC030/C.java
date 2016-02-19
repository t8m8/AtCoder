import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

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

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int x = in.nextInt();
		int y = in.nextInt();

		int[][] a = new int[2][];
		a[0] = new int[n];
		a[1] = new int[m];

		for (int i=0; i<n; i++) {
			a[0][i] = in.nextInt();
		}
		for (int j=0; j<m; j++) {
			a[1][j] = in.nextInt();
		}

		int t = 0, ans = 0, cur = 0;
		while (true) {
			int i = lowerBound(a[t], cur);
			if (i == a[t].length) break;

			cur = a[t][i] + (t == 0 ? x : y);

			t = (t+1)%2;
			if (t == 0) ans++;
		}

		out.println(ans);
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