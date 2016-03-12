import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static boolean f(double[][] a, double m, int k) {
		int cnt = 0;
		double y = 0;
		double x = 0;

		BitSet used = new BitSet();

		for (int i=0; i<k; i++) {
			int idx = -1;
			double max = 0;
			for (int j=0; j<a.length; j++) {
				if (used.get(j)) continue;
				if (max < (y+a[j][0])/(x+a[j][1])) {
					max = (y+a[j][0])/(x+a[j][1]);
					idx = j;
				}
			}

			y += a[idx][0];
			x += a[idx][1];
			used.set(idx);
		}

		return y/x*100 < m;
	}

	public static double binSearch(double[][] a, int k) {
		double l = 0, r = 100;
		for (int i=0; i<100; i++) {
			double m = (l + r)/2;
			if (f(a, m, k)) r = m;
			else l = m;
		}
		return r;
	}

	static void solve() {
		int n = in.nextInt();
		int k = in.nextInt();
		double[] w = new double[n];
		double[] p = new double[n];
		double[][] t = new double[n][2];
		for (int i=0; i<n; i++) {
			w[i] = in.nextInt();
			p[i] = in.nextInt();
			t[i][0] = p[i]*(w[i]/100);
			t[i][1] = w[i];
		}

		out.printf("%.10f\n",binSearch(t, k));
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