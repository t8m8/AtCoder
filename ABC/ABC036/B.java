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
		char[][] t = new char[n][n];
		for (int i=0; i<n; i++) {
			t[i] = in.next().toCharArray();
		}

		char[][] ans = new char[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				ans[j][n-i-1] = t[i][j];
			}
		}

		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				out.print(ans[i][j]);
			}
			out.println();
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