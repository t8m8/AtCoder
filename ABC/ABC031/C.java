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
			a[i] += in.nextInt();
			// if (i+2 < n) a[i+2] += a[i];
		}
		dump(a);
		int max = Integer.MIN_VALUE/2;
		for (int i=0; i<n; i++) {
			int x = Integer.MIN_VALUE/2, y = Integer.MIN_VALUE/2;
			for (int j=0; j<n; j++) {
				if (i == j) continue;
				int tx = 0, ty = 0;
				boolean f = true;
				for (int k=Math.min(i,j); k<=Math.max(i,j); k++, f=!f) {
					if (f) tx += a[k];
					else ty += a[k];
				}
				if (y < ty) {
					x = tx;
					y = ty;
				}
			}
			max = Math.max(max, x);
		}

		out.println(max);
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