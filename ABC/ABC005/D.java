import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	public static int lowerBound(ArrayList<int[]> a, int k) {
		int n = a.size();
		int l = -1, r = n;
		while (r - l > 1) {
			int m = (l + r)/2;
			if (k <= a.get(m)[0]) r = m;
			else l = m;
		}
		return r;
	}

	static void solve() {
		int n = in.nextInt();
		int[][] d = new int[n+2][n+2];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				d[i][j] += in.nextInt();
				d[i+1][j] += d[i][j];
				d[i][j+1] += d[i][j];
				d[i+1][j+1] -= d[i][j];
			}
		}

		ArrayList<int[]> val = new ArrayList<>();

		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				for (int k=0; i+k<=n; k++) {
					for (int l=0; j+l<=n; l++) {
						int s = d[i+k][j+l] - d[i+k][j-1] - d[i-1][j+l] + d[i-1][j-1];
						val.add(new int[]{(k+1)*(l+1), s});
					}
				}
			}
		}

		Collections.sort(val, new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				if (a[0] != b[0]) return a[0] - b[0];
				return a[1] - b[1];
			}
		});

		ArrayList<int[]> val2 = new ArrayList<>();
		val2.add(val.get(0));
		for (int i=1; i<val.size(); i++) {
			val2.add(new int[]{val.get(i)[0], Math.max(val.get(i)[1], val2.get(i-1)[1])});
		}

		int q = in.nextInt();
		while (q-- > 0) {
			int p = in.nextInt();
			int i = lowerBound(val2, p+1) - 1;
			out.println(val2.get(i)[1]);
		}
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