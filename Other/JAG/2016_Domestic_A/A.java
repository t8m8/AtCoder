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
		boolean f = true;

		int x = 0;
		for (int i=0; i<n; i++) {
			String s = in.next();
			if (s.equals("A")) {
				x++;
			} else if (s.equals("Un")) {
				x--;
				if (x < 0) {
					f = false;
				}
			}
		}

		if (x == 0 && f) {
			out.println("YES");
		} else {
			out.println("NO");
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