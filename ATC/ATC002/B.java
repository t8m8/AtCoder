import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static long modPow(long x, long n, long mod) {
		long res = 1;
		while (n > 0) {
			if ((n&1) == 1) res = res * x % mod;
			x = x * x % mod;
			n >>= 1;
		}
		return res;
	}

	static void solve() {
		long n = in.nextLong();
		long m = in.nextLong();
		long p = in.nextLong();
		out.println(modPow(n,p,m));
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