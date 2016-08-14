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
		int[] a = new int[n];
		int min = 101;
		int max = -101;
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
			min = Math.min(min, a[i]);
			max = Math.max(max, a[i]);
		}

		int ans = Integer.MAX_VALUE/2;
		for (int i=min; i<=max; i++) {
			int tmp = 0;
			for (int j=0; j<n; j++) {
				tmp += (a[j] - i)*(a[j] - i);
			}
			ans = Math.min(ans, tmp);
		}
		out.println(ans);
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