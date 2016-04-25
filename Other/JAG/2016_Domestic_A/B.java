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
		int m = in.nextInt();
		int t = in.nextInt();
		int[] time = new int[10010];
		for (int i=0; i<n; i++) {
			int a = in.nextInt();
			for (int j = a-m; j < Math.min(t, a+m); j++) {
				time[j]++;
			}
		}
		int cnt = 0;
		for (int i=0; i<t; i++) {
			if (time[i] == 0) {
				cnt++;
			}
		}
		dump(Arrays.copyOfRange(time,0,t+1));
		out.println(cnt);
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