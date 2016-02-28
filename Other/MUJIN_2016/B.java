import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		double a = in.nextDouble();
		double b = in.nextDouble();
		double c = in.nextDouble();
		double max = Math.max(a,Math.max(b,c));
		double d = a + b + c;

		if (max > d - max) {
			double l = Math.min(Math.min(Math.abs(a - b - c), Math.abs(a + b - c)) ,Math.abs(a - b + c));
			out.printf("%.10f\n",(d*d - l*l)*Math.PI);
		} else {
			out.printf("%.10f\n",d*d*Math.PI);
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