import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		String s = in.next() + "+";
		int n = s.length();

		int ans = 0;

		for (int i=0, j=1; j<n; j+=2) {
			while (s.charAt(j) != '+') {
				j += 2;
			}

			boolean f = true;

			while (i < j) {
				if (s.charAt(i) == '0') f = false;
				i += 2;
			}

			if (f) {
				ans++;
			}
		}

		out.println(ans);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		solve();
		out.flush();

		long end = System.currentTimeMillis();
		//trace(end-start + "ms");
		in.close();
		out.close();
	}

	static void trace(Object... o) { System.out.println(Arrays.deepToString(o));}
}