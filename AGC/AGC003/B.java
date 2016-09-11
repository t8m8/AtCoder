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
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}
		long ans = 0;
		long carry = 0;
		for (int i=0; i<n; i++) {
			ans += (a[i]+carry)/2;
			carry = a[i] == 0 ? 0 : (a[i]+carry)%2;
		}

		long ans2 = 0;
		long carry2 = 0;
		for (int i=n-1; i>=0; i--) {
			ans2 += (a[i]+carry2)/2;
			carry2 = a[i] == 0 ? 0 : (a[i]+carry2)%2;
		}

		out.println(Math.max(ans, ans2));
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