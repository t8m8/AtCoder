import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static String[] ss = {"maerd", "remaerd", "esare", "resare"};

	static void solve() {
		String s = in.next();
		int n = s.length();
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		for (int i=0; i<n; ) {
			boolean f = false;
			for (int j=0; j<4; j++) {
				if (i+ss[j].length() <= n && sb.substring(i, i+ss[j].length()).toString().equals(ss[j])) {
					f = true;
					i += ss[j].length();
					break;
				}
			}

			if (!f) {
				out.println("NO");
				return;
			}
		}

		out.println("YES");
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