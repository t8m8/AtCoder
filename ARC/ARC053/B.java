import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		String s = in.next();
		int n = s.length();
		int[] cnt = new int[26];
		for (int i=0; i<n; i++) {
			cnt[s.charAt(i) - 'a']++;
		}

		int a = 0, b = 0;

		for (int i=0; i<26; i++) {
			while (cnt[i] >= 2) {
				cnt[i] -= 2;
				a++;
			}
			if (cnt[i] >= 1) {
				cnt[i] -= 1;
				b++;
			}
		}

		if (b <= 1) {
			out.println(n);
			return;
		}

		int[] x = new int[b];
		int pos = 0;
		for (int i=0; i<a; i++) {
			x[pos] += 2;
			pos = (pos+1)%b;
		}

		Arrays.sort(x);
		out.println((x[0]+1));
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