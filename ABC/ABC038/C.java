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
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}

		int l = 0, r = 0;
		long ans = 0;
		while (l < n) {
			long v = 1;
			while (r+1 < n && a[r] < a[r+1]) {
				r++;
				v += r - l + 1;
			}
			ans += v;
			l = r + 1;
			r = l;
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