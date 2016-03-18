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
		int cnt1 = 0, cnt2 = 0;
		for (int i=0; i<n; i++) {
			String s = in.next();
			for (int j=0; j<n; j++) {
				if (s.charAt(j) == 'R') {
					cnt1++;
				} else if (s.charAt(j) == '.') {
					cnt2++;
				}
			}
		}

		int t = n*n - cnt2;

		out.println(cnt1 > (t - cnt1) ? "TAKAHASHI" : (cnt1 == t - cnt1 ? "DRAW" : "AOKI"));
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