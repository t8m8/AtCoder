import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		long a = in.nextInt();
		long b = in.nextInt();
		long k = in.nextInt();
		long l = in.nextInt();
		if ((double)b/l > a) {
			out.println(a*k);
		} else {
			long c = k/l;
			out.println(Math.min((c*b+a*(k-c*l)), (c+1)*b));
		}
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