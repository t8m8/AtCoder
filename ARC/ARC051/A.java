import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int x1 = in.nextInt() + 100;
		int y1 = in.nextInt() + 100;
		int r = in.nextInt();
		int x2 = in.nextInt() + 100;
		int y2 = in.nextInt() + 100;
		int x3 = in.nextInt() + 100;
		int y3 = in.nextInt() + 100;

		if (x2 <= x1 - r && x1 + r <= x3 && y2 <= y1 - r && y1 + r <= y3) {
			out.println("NO");
		} else {
			out.println("YES");
		}

		// x2, y2
		int d1 = (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2);

		// x2, y3
		int d2 = (x1 - x2)*(x1 - x2) + (y1 - y3)*(y1 - y3);

		// x3, y2
		int d3 = (x1 - x3)*(x1 - x3) + (y1 - y2)*(y1 - y2);

		// x3, y3
		int d4 = (x1 - x3)*(x1 - x3) + (y1 - y3)*(y1 - y3);

		if (r*r >= d1 && r*r >= d2 && r*r >= d3 && r*r >= d4) {
			out.println("NO");
		} else {
			out.println("YES");
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