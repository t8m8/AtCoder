import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		String s = in.next();
		int n = s.length();
		boolean[] f = new boolean[4];
		for (int i=0; i<n; i++) {
			switch (s.charAt(i)) {
				case 'N': f[0] = true; break;
				case 'W': f[1] = true; break;
				case 'S': f[2] = true; break;
				case 'E': f[3] = true; break;
			}
		}

		out.println(f[0] == f[2] && f[1] == f[3] ? "Yes" : "No");
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