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
		int r = in.nextInt();
		String s = in.next();
		int[] cnt = new int[n+1];
		for (int i=0; i<n; i++) {
			if (s.charAt(i) == '.') {
				cnt[i+1]++;
			}
			cnt[i+1] += cnt[i];
		}

		int sum = cnt[n];
		if (sum == 0) {
			out.println(0);
			return;
		}
		int ans = 0, cur = 0;
		BitSet b = new BitSet();
		while (true) {
			ans++;
			if (cnt[Math.min(cur+r, n)] == sum) {
				break;
			} else if (s.charAt(cur) == '.' && !b.get(cur)) {
				b.set(cur, cur+r, true);
			} else {
				cur++;
			}
		}

		out.println(ans);
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