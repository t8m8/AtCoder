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
		char[] cs = s.toCharArray();
		int n = s.length();
		int k = in.nextInt();
		for (int i=0; i<n; i++) {
			if (cs[i] == 'a') continue;
			if ('z' - cs[i] + 1 <= k) {
				k -= 'z' - cs[i] + 1;
				cs[i] = 'a';
			}
		}

		if (k > 0) {
			k %= 26;
			cs[n-1] = (char)((cs[n-1] - 'a' + k)%26 + 'a');
		}

		for (int i=0; i<n; i++) {
			out.print(cs[i]);
		}
		out.println();
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