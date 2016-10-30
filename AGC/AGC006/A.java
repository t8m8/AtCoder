import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		String s = in.next();
		String t = in.next();
		for (int i=0; i<n; i++) {
			boolean f = true;
			for (int j=i; j<n; j++) {
				if (s.charAt(j) != t.charAt(j-i)) {
					f = false;
				}
			}

			if (f == true) {
				out.println((n+i));

				return;
			}
		}

		out.println(2*n);
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