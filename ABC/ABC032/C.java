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
		int k = in.nextInt();
		long[] a = new long[n];
		boolean f = false;
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
			if (a[i] == 0) f = true;
		}
		if (f) {
			out.println(n);
			return;
		}
		if (k == 0) {
			out.println("0");
			return;
		}
		long mul = 1;
		int l = 0;
		int ans = 0;
		for (int r=0; r<n; r++) {
			mul *= a[r];
			while (mul > k) {
				mul /= a[l];
				l++;
			}
			ans = Math.max(ans, r - l + 1);
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