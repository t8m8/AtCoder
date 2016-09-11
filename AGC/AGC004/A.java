import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		long a = in.nextLong();
		long b = in.nextLong();
		long c = in.nextLong();
		out.println(Math.min(a*b*(c - c/2) - a*b*(c/2), Math.min(a*(b - b/2)*c - a*(b/2)*c, (a - a/2)*b*c - (a/2)*b*c)));
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