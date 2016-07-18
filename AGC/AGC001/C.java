import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;
	static int[][] g;

	static int[][] undirectedGraph(int n, int[] v1, int[] v2) {
		int[] cnt = new int[n];
		for (int i : v1) cnt[i]++;
		for (int i : v2) cnt[i]++;

		int[][] g = new int[n][];
		for (int i=0; i<n; i++) g[i] = new int[cnt[i]];
		for (int i=0; i<v1.length; i++) {
			int s = v1[i];
			int t = v2[i];
			g[s][--cnt[s]] = t;
			g[t][--cnt[t]] = s;
		}

		return g;
	}

	static int n, k;

	static int dfs(int cur, int prev, int depth) {
		if (depth > k/2) return 0;
		int res = 0;
		for (int to : g[cur]) {
			if (to == prev) continue;

			res += dfs(to, cur, depth+1);
		}
		return res + 1;
	}

	static int dfs2(int cur, int prev, int depth, int x) {
		if (depth > k/2) return 0;
		int res = 0;
		for (int to : g[cur]) {
			if (to == prev || to == x) continue;

			res += dfs2(to, cur, depth+1, x);
		}
		return res + 1;
	}

	static void solve() {
		n = in.nextInt();
		k = in.nextInt();
		int[] s = new int[n-1];
		int[] t = new int[n-1];
		for (int i=0; i<n-1; i++) {
			s[i] = in.nextInt() - 1;
			t[i] = in.nextInt() - 1;
		}
		g = undirectedGraph(n, s, t);
		int ans = n;
		if (k%2 == 0) {
			for (int i=0; i<n; i++) {
				ans = Math.min(ans, n - dfs(i, -1, 0));
			}
		} else {
			for (int i=0; i<n-1; i++) {
				ans = Math.min(ans, n - dfs2(s[i], -1, 0, t[i]) - dfs2(t[i], -1, 0, s[i]));
			}
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