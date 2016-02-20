import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static boolean rec(String s, int c, int m) {
		if (m < c) return true;
		if (c%3 == 1) {
			if (s.charAt(m-c) != 'a' || s.charAt(m+c) != 'c') return false;
		} else if (c%3 == 2) {
			if (s.charAt(m-c) != 'c' || s.charAt(m+c) != 'a') return false;
		} else {
			if (s.charAt(m-c) != 'b' || s.charAt(m+c) != 'b') return false;
		}
		return rec(s, c+1, m);
	}

	static void solve() {
		int n = in.nextInt();
		String s = in.next();
		if (n%2 == 0) {
			out.println("-1");
			return;
		}
		int m = n/2;
		if (s.charAt(m) != 'b') {
			out.println("-1");
		} else {
			out.println(rec(s, 1, m) ? n/2 : "-1");
		}
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