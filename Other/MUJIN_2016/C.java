import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<int[]> g = new ArrayList<>();

		for (int i=0; i<m; i++) {
			g.add(new int[]{in.nextInt()-1, in.nextInt()-1});
		}

		int ans = 0;

		for (int i=0; i<1<<n; i++) {
			DisjointSet ds = new DisjointSet(n);
			for (int[] e : g) {
				if ((i>>e[0]&1) != (i>>e[1]&1)) {
					ds.unite(e[0], e[1]);
				}
			}

			boolean f = true;
			for (int j=1; j<n; j++) {
				if (!ds.same(0, j)) f = false;
			}
			if (f) ans++;
		}

		out.println(ans/2);
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

class DisjointSet {
	int[] data;

	public DisjointSet(int n){
		data = new int[n];
		for (int i=0; i<n; i++) data[i] = i;
	}

	public int find(int x){
		if(data[x] == x) return x;
		return data[x] = find(data[x]);
	}

	public boolean same(int x,int y){
		return find(x) == find(y);
	}

	public void unite(int x,int y){
		if (find(x) == find(y)) return;
		data[find(x)] = find(y);
	}

	public String toString() {
		return Arrays.toString(data);
	}
}