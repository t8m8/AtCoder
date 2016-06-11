import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		String s = in.next();
		String t = "WBWBWWBWBWBW";

		String[] ans = {"Do", "Re", "Mi", "Fa", "So", "La", "Si"};

		int cnt = 0;

		for (int i=0; i<t.length(); i++) {
			if (t.charAt(i) == 'B') continue;
			boolean f = true;
			for (int j=0; j<20; j++) {
				if (s.charAt(j) != t.charAt((i+j)%t.length())) {
					f = false;
					break;
				}
			}
			if (f) {
				out.println(ans[cnt]);
				return;
			}
			cnt++;
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