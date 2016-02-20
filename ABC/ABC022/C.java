import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	public static int[][] floydWarshall(int[][] g) {
		int n = g.length;
		int[][] d = new int[n][];
		for (int i=0; i<n; i++) d[i] = Arrays.copyOf(g[i],n);
		for (int k=1; k<n; k++)
			for (int i=1; i<n; i++)
				for (int j=1; j<n; j++)
					d[i][j] = Math.min(d[i][j],d[i][k] + d[k][j]);
		return d;
	}

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] g = new int[n][n];
		for (int i=0; i<n; i++) {
			Arrays.fill(g[i], Integer.MAX_VALUE/2);
			g[i][i] = 0;
		}
		for (int i=0; i<m; i++) {
			int s = in.nextInt() - 1;
			int t = in.nextInt() - 1;
			int c = in.nextInt();
			g[s][t] = g[t][s] = c;
		}
		dump(g);
		int[][] d = floydWarshall(g);
		dump(d);
		int min = Integer.MAX_VALUE/2;
		for (int i=1; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				if (g[0][i] != Integer.MAX_VALUE/2 && g[0][j] != Integer.MAX_VALUE/2) {
					min = Math.min(min, d[i][j] + g[0][i] + g[0][j]);
				}
			}
		}

		out.println(min > Integer.MAX_VALUE/4 ? "-1" : min);
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