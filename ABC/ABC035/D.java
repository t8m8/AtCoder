import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int[][][] directedGraph(int n, int[] s, int[] t, int[] cost) {
		int[] cnt = new int[n];
		for (int i : s) cnt[i]++;

		int[][][] g = new int[n][][];
		for (int i=0; i<n; i++) g[i] = new int[cnt[i]][2];
		for (int i=0; i<s.length; i++) {
			int from = s[i];
			int to = t[i];

			g[from][--cnt[from]][0] = to;
			g[from][cnt[from]][1] = cost[i];
		}

		return g;
	}

	static long[] dijkstra(int[][][] g, int source) {
		int n = g.length;

		final long[] d = new long[n];
		Arrays.fill(d,1L<<35);
		d[source] = 0;

		TreeSet<Integer> pQ = new TreeSet<Integer>(new Comparator<Integer>(){
			public int compare(Integer a, Integer b) {
				if (d[a] != d[b]) return d[a] - d[b] > 0 ? 1 : -1;
				return a - b > 0 ? 1 : -1;
			}
		});
		pQ.add(source);

		while (!pQ.isEmpty()) {
			int cur = pQ.pollFirst();

			for (int i=0; i<g[cur].length; i++) {
				int next = g[cur][i][0];
				long dist = d[cur] + g[cur][i][1];
				if (dist < d[next]) {
					pQ.remove(next);
					d[next] = dist;
					pQ.add(next);
				}
			}
		}

		return d;
	}

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int t = in.nextInt();
		int[] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
		}

		int[] from = new int[m];
		int[] to = new int[m];
		int[] cost = new int[m];

		for (int i=0; i<m; i++) {
			from[i] = in.nextInt()-1;
			to[i] = in.nextInt()-1;
			cost[i] = in.nextInt();
		}

		int[][][] g = directedGraph(n, from, to, cost);
		int[][][] gi = directedGraph(n, to, from, cost);

		long[] d = dijkstra(g, 0);
		long[] di = dijkstra(gi, 0);

		long ans = (long)a[0]*t;

		for (int i=1; i<n; i++) {
			long c = d[i] + di[i];
			if (c > t) continue;
			ans = Math.max(ans, (t-c)*a[i]);
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