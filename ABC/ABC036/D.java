import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int MOD = 1_000_000_007;

	static int[][] g;
	static long[][] dp;

	static int[][] undirectedGraph(int n, int[] v1, int[] v2) {
		int[] cnt = new int[n];
		for (int i : v1) cnt[i]++;
		for (int i : v2) cnt[i]++;

		int[][] g = new int[n][];
		for (int i=0; i<n; i++) g[i] = new int[cnt[i]];
		for (int i=0; i<v1.length; i++) {
			int from = v1[i];
			int to = v2[i];
			g[from][--cnt[from]] = to;
			g[to][--cnt[to]] = from;
		}

		return g;
	}

	static void dfs(int cur, int prev) {

		long tmp0 = 1, tmp1 = 1;
		for (int to : g[cur]) {
			if (to == prev) continue;
			dfs(to, cur);
			tmp0 = (dp[to][1]*tmp0)%MOD;
			tmp1 = ((dp[to][0] + dp[to][1])%MOD*tmp1)%MOD;
		}
		dp[cur][0] = tmp0;
		dp[cur][1] = tmp1;
	}

	static void solve() {
		int n = in.nextInt();
		int[] v1 = new int[n-1];
		int[] v2 = new int[n-1];
		for (int i=0; i<n-1; i++) {
			v1[i] = in.nextInt() - 1;
			v2[i] = in.nextInt() - 1;
		}
		g = undirectedGraph(n, v1, v2);
		dp = new long[n][2];

		dfs(0, -1);
		dump(dp);
		out.println((dp[0][0] + dp[0][1])%MOD);
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