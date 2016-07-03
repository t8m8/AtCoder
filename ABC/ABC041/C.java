import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int[][] a = new int[n][2];
		for (int i=0; i<n; i++) {
			a[i][0] = in.nextInt();
			a[i][1] = i;
		}
		Arrays.sort(a, (x, y) -> y[0] - x[0]);
		for (int i=0; i<n; i++) {
			out.println((a[i][1]+1));
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