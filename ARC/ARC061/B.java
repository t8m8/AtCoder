import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		String[] s = new String[3];
		for (int i=0; i<3; i++) {
			s[i] = in.next();
		}

		int t = 0;
		int[] i = new int[3];
		String ans = "ABC";

		while (true) {
			if (i[t] == s[t].length()) {
				out.println(ans.charAt(t));
				break;
			}
			int nt = s[t].charAt(i[t]) - 'a';
			i[t]++;
			t = nt;
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