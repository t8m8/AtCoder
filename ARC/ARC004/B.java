import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int[] d = new int[n];
		long max = 0;
		for (int i=0; i<n; i++) {
			d[i] = in.nextInt();
			max += d[i];
		}

		out.println(max);
		Arrays.sort(d);
		if (max - d[n-1] < d[n-1]) {
			out.println(d[n-1] - (max - d[n-1]));
		} else {
			out.println(0);
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